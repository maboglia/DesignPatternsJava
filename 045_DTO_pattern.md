# DTO Pattern

Il DTO (Data Transfer Object) è un design pattern strutturale che fornisce un oggetto semplice per trasferire dati tra componenti del sistema. Il suo scopo principale è quello di ottimizzare le prestazioni, riducendo il numero di chiamate remote in un'applicazione distribuita.

---

### Caratteristiche chiave del DTO

1. **Trasferimento di Dati:**
   - Il DTO è progettato principalmente per trasferire dati tra il livello di presentazione (o altri componenti) e il livello di persistenza (o altri componenti).

2. **Oggetti Semplici:**
   - I DTO sono spesso oggetti semplici che contengono solo dati e non hanno logica aziendale.

3. **Ottimizzazione delle Prestazioni:**
   - Utilizzato per ridurre il numero di chiamate remote in un'applicazione distribuita, poiché può raggruppare più dati in un'unica richiesta.

4. **Indipendenza dai Dettagli Implementativi:**
   - Un DTO è indipendente dai dettagli implementativi della sorgente dati o del consumatore dei dati.

5. **Serializzazione:**
   - Può essere facilmente serializzato per il trasferimento su una rete o attraverso confini di sistema.

---

### Esempio di DTO

Consideriamo un esempio semplificato di DTO per rappresentare i dati di un utente:

```java
public class UserDTO {
    private Long userId;
    private String username;
    private String email;

    // Costruttore, getter e setter

    // Altri metodi, se necessario
}
```

In questo esempio, `UserDTO` è un oggetto contenente solo i dati relativi a un utente senza alcuna logica aziendale associata. Può essere utilizzato per trasferire i dati dell'utente tra il livello di presentazione e il livello di persistenza.

---

### Utilizzo del DTO

1. **Riduzione del Traffico di Rete:**
   - In un'applicazione distribuita, quando si trasferiscono dati tra il client e il server, l'utilizzo di DTO può ridurre il traffico di rete raggruppando più dati in un'unica richiesta o risposta.

2. **Separazione tra Frontend e Backend:**
   - Nei sistemi con un frontend separato da un backend (come un'architettura a microservizi), i DTO possono essere utilizzati per passare dati in modo efficiente tra i due.

3. **Evitare Eccessive Query:**
   - In sistemi basati su database, l'utilizzo di DTO può evitare eccessive query restituendo solo i dati necessari invece di intere entità.

4. **Migliorare la Leggibilità del Codice:**
   - Facilita la comprensione del codice, specialmente quando si tratta di trasferire dati complessi tra diversi strati dell'applicazione.

5. **Aumentare le Prestazioni:**
   - Ottimizza le prestazioni riducendo il numero di dati trasferiti, specialmente in scenari in cui le risorse di rete sono limitate.

In conclusione, il DTO è uno strumento utile per semplificare il trasferimento di dati tra diversi componenti di un sistema, riducendo la complessità e migliorando le prestazioni in contesti distribuiti.

---

### Quando andrai in stage

Caro studente in formazione, vedrai che qualcuno in azienda (tutor, CTO, collega) con dieci anni di esperienza e una formazione accademica ti dirà che non basta aver imparato in sei mesi le basi di Java, la connessione al database, altre tecnologie web, Spring, MVC, ...

 "Eh, ma il DTO..."

![DTO](https://github.com/maboglia/ProgrammingResources/blob/master/images/funny/DTO2.png)
[Spring boot learning](https://www.youtube.com/watch?v=mRHSCQ9PYso)

---

#### Sebbene i DTO abbiano i loro vantaggi, presentano anche diversi svantaggi

- **Bloatware**: i DTO possono aggiungere bloatware (software aggiuntivo a volte inutile, come quello preinstallato su alcuni dispostivi) non necessari a un codice che dovrebbe essere pulito e semplice.
- **Complessità**: l'utilizzo dei DTO può introdurre ulteriore complessità, che può rendere difficile spiegare la struttura del sistema o quale modello utilizzare ai nuovi membri del team.
- **Manutenzione**: nel corso del tempo, le DTO possono introdurre un livello aziendale che non dovrebbe esistere, il che può portare a problemi di test e modelli di dati anemici.
- **Disaccoppiamento**: l'utilizzo di DTO per scopi di disaccoppiamento in un'architettura di microservizi non ha senso se rispecchia solo i cambiamenti qua e là senza una ragione rilevante.
- **Mappatura**: la mappatura dei DTO può essere noiosa e richiedere molto tempo, soprattutto se deve essere eseguita per più livelli o servizi.

[Articolo su DTO](https://www.linkedin.com/pulse/disadvantages-using-data-transfer-objects-dtos-how-uzc%C3%A1tegui-pescozo/)
