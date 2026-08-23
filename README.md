# 🎮 OVERSCALED

OVERSCALED è un RPG tattico 2D a turni sviluppato in Java e JavaFX con un'estetica retro dark. Il gioco gestisce la registrazione dei punteggi su file JSON e include una guida integrata all'avvio.

L'esperienza di gioco inizia con l'inserimento del nome del giocatore, seguito dalla scelta della propria arma tra le tre opzioni disponibili.  
Una volta sceso in campo, il giocatore esplora la mappa alla ricerca dei nemici per affrontarli in combattimenti a turni. L'obiettivo principale è sopravvivere il più a lungo possibile sconfiggendo quanti più avversari possibile: ogni vittoria ricompensa il giocatore ripristinando una parte della sua salute e assegnando bonus attributo proporzionati alla forza del nemico sconfitto.

---

## 🚀 Istruzioni per l'Esecuzione

### Prerequisiti
* **JDK 25**
* Sistema di build **Gradle**

### Passaggi
1. **Clona la repository**:
   ```bash
   git clone https://github.com/giovannigiannini/Overscaled.git
   cd Overscaled
2. **Build e run del progetto**
   ```bash
   ./gradlew build
   ./gradlew run
---
## 🤖 Dichiarazione sull'Uso di Strumenti di AI
1. **Gemini**, utilizzato per:  
   **a. Chiarire errori di compilazione** (gestione import, dipendenze Gradle, errori di javaFX).  
   **b. Suggerimenti su struttura del codice e separazione delle responsabilità** (SOC).  
   **c.** Idee per la **gestione della logica dello scaling** per rendere il gioco bilanciato (sia per le armi che per i nemici) e una **palette per avere l'estetica di gioco uniforme**  
   **d. Debugging generale** (esempio: JSON non creava il file per un'errore di percorso individuato con gemini).
2. **GitHub Copilot**: niente di specifico, solo per velocizzare la scrittura del codice esempio i metodi ereditati in alcune classi, getter e setter.

