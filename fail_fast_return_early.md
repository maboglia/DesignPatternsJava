# Fail fast - Return early

Spesso qunado scriviamo una funzione la logica è validare i requisiti corrispondenti fino a raggiungere il risultato.

Cosa si può osservare in questo approccio?

- Il flusso non lineare del codice è difficile da seguire a causa delle condizioni annidate.
- È difficile capire il corrispondente “else” per ogni “if”, il che rende la gestione degli errori confusa da leggere, specialmente quando il blocco “if” è grande.
- È necessario seguire il flusso del codice, navigando tra gli “if” annidati, per trovare il risultato positivo atteso.
- Per amore di esempio, viene lanciata un'eccezione nel “else”. Se il “else” non terminasse l'esecuzione, eseguirebbe il resto del codice. Questo può portare a errori inutili.

Include anche un paio di anti-pattern:

- **Else** è considerato non positivo. Quando la condizione è complicata, l'“else” è doppio, perché il lettore deve invertirlo; quando il blocco “if” è grande, è facile dimenticare la condizione; gli “if” e “else” annidati confondono il lettore.

- **Arrow anti-pattern** si verifica quando il codice inizia a diventare a forma di freccia a causa di condizioni e cicli annidati.

---

### Return Early

Proviamo a refactorizzare il codice con una mentalità diversa.  

Il pattern “return early” è un modo di scrivere funzioni o metodi in modo che il risultato positivo atteso venga restituito alla fine della funzione e il resto del codice termini l'esecuzione (ritornando o lanciando un'eccezione) quando le condizioni non sono soddisfatte.

Questo viene realizzato invertendo le condizioni “if”, gestendo gli errori necessari e restituendo o lanciando un'eccezione adeguata, terminando l'esecuzione della funzione.

Alcune osservazioni possono essere fatte:

- Il codice ha solo un livello di indentazione. È possibile leggerlo linearmente.
- Il risultato positivo atteso è rapidamente individuabile alla fine della funzione.
- Utilizzando questo processo di pensiero, c'è un maggiore focus sul trovare prima gli errori e implementare in sicurezza la logica di business successivamente, evitando bug inutili.
- La mentalità fail-fast utilizzata è simile allo sviluppo guidato dai test (TDD), il che rende il codice più facile da testare.
- La funzione termina immediatamente in caso di errori, evitando la possibilità che altro codice venga eseguito senza intenzione.

---

### Design Patterns

Mentre si utilizza la mentalità “return early” vengono seguiti i seguenti design pattern.

**Fail Fast**  
Jim Shore e Martin Fowler hanno creato il concetto di Fail Fast nel 2004. Questo concetto è la base della regola “return early”. Mentre si fallisce velocemente, il codice è più robusto a causa del focus iniziale nel trovare le condizioni in cui l'esecuzione del codice può terminare. Con questo approccio, i bug sono più facili da trovare e risolvere.

**Guard Clause**  
Una guard clause è semplicemente un controllo (l'“if” invertito) che esce immediatamente dalla funzione, sia con una dichiarazione “return” che con un'eccezione. Utilizzando le guard clause, i possibili casi di errore sono identificati e gestiti restituendo o lanciando un'eccezione adeguata.

**Happy Path**  
Il percorso felice per una funzione sarebbe quello in cui nessuna delle regole di validazione solleva un errore, consentendo quindi l'esecuzione continua con successo fino alla fine, generando una risposta positiva. Utilizzando l'approccio “return early”, il codice viene letto linearmente, esponendo così il percorso felice. Utilizzando questo pattern, non è necessario perdere tempo a seguire il flusso del codice per arrivare all'obiettivo. È possibile utilizzare la memoria muscolare per trovarlo.

**Bouncer Pattern**  
Il bouncer pattern è un metodo per validare determinate condizioni restituendo o lanciando un'eccezione. È particolarmente utile quando il codice di validazione è complesso e può essere utilizzato in più scenari. Completa il pattern “return early”.

---

### Svantaggi

Sebbene l'approccio “return early” abbia punti positivi, ha anche alcune criticità.

**Le funzioni dovrebbero avere un solo punto di uscita**  
Questa regola di codifica risale alla programmazione strutturata di Dijkstra. Questa nozione di Single Entry, Single Exit (SESE) proviene da linguaggi con gestione esplicita delle risorse, come C e Assembly. Nei linguaggi in cui le risorse non sono o non dovrebbero essere gestite manualmente, c'è poco o nessun valore nell'aderire alla vecchia convenzione. SESE spesso rende il codice più complesso. È un dinosauro che (eccetto per il C) non si adatta bene alla maggior parte dei linguaggi odierni. Invece di aiutare la comprensibilità del codice, la ostacola.

**Pulizia delle risorse**  
I linguaggi di alto livello come Java e C# hanno la garbage collection, ma a volte è ancora necessario gestire alcune risorse manualmente. Fortunatamente, i linguaggi più recenti hanno i seguenti concetti:

- Le dichiarazioni “try, catch e finally” consentono l'uso di una risorsa, catturando eventuali eccezioni possibili e poi rilasciando la risorsa nel blocco finale, assicurandosi che non ci siano perdite di memoria.
- La dichiarazione “using” consente l'uso di una risorsa all'interno di un blocco e la dispone automaticamente dopo il suo utilizzo, anche se la terminazione è causata prematuramente.
Questi concetti consentono di utilizzare la regola “return early” pur disponendo delle risorse utilizzate quando si termina l'esecuzione della funzione.

**Log e Debugging**  
Un argomento è che un singolo “return” è più facile da *debuggare* perché è necessario aggiungere un solo punto di interruzione per catturare tutte le uscite da una funzione, ed è più facile da registrare perché richiede solo un log alla fine. Questo non è necessariamente vero. Utilizzando l'approccio “return early” è possibile lanciare subito eccezioni. Il debugging è molto più semplice se il motivo per cui il codice sta fallendo è ovvio. Un log può anche essere aggiunto prima di ogni terminazione per informare meglio lo sviluppatore. Se è necessario registrare tutte le uscite, in caso di più dichiarazioni “return”, è possibile registrare dopo aver ottenuto il valore dalla rispettiva funzione.

**Molteplici punti di uscita influenzano la leggibilità**  
Una funzione di 200 righe con varie dichiarazioni “return” sparse casualmente su di essa non è un ottimo stile di programmazione e non è leggibile. Ma una tale funzione non sarebbe facile da capire nemmeno senza quei return. Il bouncer pattern e il pattern del metodo estratto dovrebbero essere utilizzati per mantenere la dimensione della funzione entro limiti ragionevoli.

**Lo stile di codice è soggettivo**  
Un design pattern è una soluzione ripetibile generale a un problema comunemente ricorrente nella progettazione del software. Queste sono convenzioni che gli sviluppatori hanno trovato nel tempo che aiutano a facilitare il loro lavoro e dovrebbero essere utilizzate nei casi appropriati. Tuttavia, alcuni aspetti della programmazione sono soggettivi. Diamo un'occhiata al seguente esempio:
Nel primo approccio, c'è più codice scritto e complessità rispetto al secondo approccio. Tuttavia, il codice è preparato, utilizzando la mentalità “return early”, per miglioramenti futuri della funzione. Tuttavia, questo modo di pensare infrange le regole KISS e YAGNI. Keep It Simple Stupid e You Aren’t Gonna Need It. È facile cambiare il codice al pattern “return early” se necessario in futuro. 
Il secondo approccio è molto più semplice e leggibile. Va dritto al punto e sarebbe la mia scelta personale in questo caso.
Tuttavia, è comprensibile perché qualcuno userebbe il primo approccio. In questo caso, discutere sulla “corretta via” per farlo è una perdita di tempo importante.

---

### Non sempre si possono evitare gli if-annidati

Il pattern “return early” è un ottimo modo per evitare che le funzioni diventino confuse. Tuttavia, questo non significa che possa essere applicato sempre. Occasionalmente, durante la logica di business complessa, è inevitabile avere alcuni “if” annidati, anche con l'opzione di estrarre il codice in altre funzioni.
