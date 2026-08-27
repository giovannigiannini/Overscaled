#  📌  OVERSCALED

OVERSCALED è un RPG tattico 2D a turni in Java/JavaFX con estetica retro dark,
dotato di guida integrata e salvataggio dei punteggi in JSON.

Il giocatore personalizza il proprio eroe scegliendo l'arma ed esplora la mappa per affrontare nemici in combattimenti strategici (stile Pokemon sul Nintendo). Lo scopo del gioco è sopravvivere il più a lungo possibile, sconfiggendo gli avversari per recuperare salute e guadagnare potenziamenti progressivi.

---

## 🚀 Come eseguire il progetto 

### Prerequisiti
* **JDK 25** (LTS)
* **Gradle**

### Istruzioni
1. **Clona la repository**:
   ```bash
   git clone https://github.com/giovannigiannini/Overscaled.git
   cd Overscaled
2. **Build del progetto**
   ```bash
   ./gradlew build
3. **Esecuzione**
    ```bash
   ./gradlew run
---
## 🤖 Uso di strumenti di AI
1. **Gemini**, utilizzato per:  
   **a. Chiarire errori di compilazione** (gestione import, dipendenze Gradle, errori di javaFX).  
   **b. Suggerimenti su struttura del codice e separazione delle responsabilità** (SOC).  
   **c.** Idee per la **gestione della logica dello scaling** per rendere il gioco bilanciato (sia per le armi che per i nemici) e una **palette per avere l'estetica di gioco uniforme**  
   **d. Debugging generale** (esempio: JSON non creava il file per un'errore di percorso individuato con gemini).
2. **GitHub Copilot**: usato per velocizzare la scrittura dei metodi semplici e del codice in generale ad  esempio i metodi ereditati in alcune classi, getter e setter.


L'AI è stata usata solo come supporto e per chiarire dubbi. Ho studiato, testato e integrato manualmente ogni suggerimento dopo averne compreso la logica mantenendo il controllo completo sul funzionamento del codice.

---
---

📌 Per una descrizione più dettagliata dell’uso dell’AI, utilizzare la **[Wiki del repository](https://github.com/giovannigiannini/Overscaled/wiki/Dichiarazione-Uso-di-Strumenti-AI)**.  
