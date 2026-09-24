# Il pattern "Return Early"

Semplifica il tuo codice per migliorare la leggibilità e le prestazioni


---

**Problema**  
Osserviamo questo esempio:
```java
getMeSomething(arg1: SomeObject, arg2: AnotherObject) {
    if (arg1.isTrue()) {
        if (arg2.isTrue()) {
            const obj1 = this.doSomething(arg1);
            if (obj1.isTrue()) {
                const obj2 = this.doSomething(arg2);
                if (obj2.isTrue()) {
                    // ... e così via

                    return whateverObject; // Questo è il ritorno principale della nostra funzione :)
                } else {
                    throw new Exception('qualcosa è andato storto');
                }
            // ... e così via
```

Il codice sopra dimostra una struttura caratterizzata da una cascata di condizioni annidate, riflettendo un processo di validazione passo-passo. 

Sebbene l'intenzione possa essere quella di garantire la validità di vari oggetti a ogni livello prima di procedere, il codice risultante è complesso, difficile da leggere e pone difficoltà nella manutenzione — un vero labirinto di istruzioni if.

Questo pattern di codifica, spesso chiamato “codice a freccia”.

Il pattern presenta i seguenti elementi 'brutti' o 'fastidiosi':

- **Problemi di leggibilità:** La leggibilità complessiva del codice è compromessa, introducendo difficoltà per gli sviluppatori nella comprensione della logica e nella risoluzione dei problemi.
- **Annidamento profondo:** I livelli eccessivi di indentazione dovuti a istruzioni if annidate creano una struttura di codice convoluta e visivamente complessa.
- **Codice a freccia:** La struttura assomiglia a una freccia, dove le istruzioni if annidate contribuiscono a una base di codice meno leggibile e mantenibile.
- **Codice spaghetti:** L'annidamento estensivo risulta in una struttura di codice simile agli spaghetti, rendendo difficile districare e comprendere.
- **Istruzioni else:** Quando la condizione è complicata, l'“else” è due volte più complicato perché il lettore deve invertirla. Quando il blocco “if” è grande, è facile dimenticare la condizione; gli “if” e “else” annidati confondono il lettore.

La souzione proposta è: **fallire il prima possibile** — perché a volte, fallire velocemente è la strada più rapida verso il successo!

---

**Return Early Pattern**  
Il pattern “return early”, noto anche come “fail fast” o “bail out early” è una pratica di codifica in cui una funzione esce non appena una certa condizione non è soddisfatta, piuttosto che permettere al codice di continuare ad essere eseguito. In breve, invece di avvolgere l'intera logica della funzione in strutture if-else annidate, questo pattern prevede di verificare le condizioni che porterebbero a fallimenti o risultati indesiderati e di tornare immediatamente dalla funzione, come mostrato di seguito:

```java
if (condition1-not-met)
    throw new Exception('qualcosa è andato storto');

if (condition2-not-met)
    throw new Exception('un'altra cosa è andata storto');

// ... continua con quello che il tuo metodo dovrebbe fornire
```

Diverse osservazioni possono essere fatte:

- Il codice mantiene un singolo livello di indentazione, promuovendo la leggibilità lineare.
- L'esito positivo previsto è identificabile alla fine della funzione.
- Questo processo promuove la rilevazione precoce degli errori, consentendo l'implementazione sicura della logica di business e riducendo al minimo i bug inutili.
- La mentalità adottata di fail-fast è in linea con i principi dello sviluppo guidato dai test, contribuendo a una maggiore testabilità del codice.
- La funzione termina immediatamente in caso di errori, evitando la possibilità che più codice venga eseguito senza intenzione.

---

**Conclusione**  

Il pattern “return early” serve come strumento prezioso per migliorare la chiarezza del codice prevenendo che le funzioni diventino intricate. Sebbene questo approccio sia efficace in molti casi, ci sono istanze in logiche di business complesse dove l'uso di “if” annidati potrebbe essere inevitabile, anche considerando l'opzione di estrarre il codice in funzioni separate.
