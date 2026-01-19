# STProject - Panoramica

Questo progetto utilizza le Github Actions per effettuare, dopo un push o una pull request, un'elaborazione dei risultati dei test di Jacoco, includendo anche i test smells trovati tramite TSDetect.

## Introduzione

### Struttura del Progetto

* `src/main/java/` → classi sorgenti
* `src/test/java/` → classi di test JUnit (devono terminare con Test.java)
* `tools/tsdetect/` → strumento tsDetect per l’analisi dei test smells
* `reports/` → report generati da Surefire
* `coverage/` → report di copertura JaCoCo
* `pom.xml` → file di configurazione Maven
* .github/workflows/ci.yml -> workflow di "Continous Deployment" configurato con Maven


### Lettura dei Risultati dei Test

Quando il workflow di github termina, nel README del progetto stesso verranno visualizzati in ordine: Coverage (come percentuale di righe coperte rispetto alle righe totali della classe sorgente), Test Smells
e presenza di failures (in questo caso jacoco non produrrà il file jacoco.xml utilizzato per il calcolo della coverage che quindi sarà a 0, anche per le classi senza failures).

---

## POM.xml

Il progetto utilizza Maven con dipendenze e plugin principali:

* JUnit → test unitari
* Surefire → esegue i test e genera report XML
* JaCoCo → genera report di copertura per classe

---

## ci.yml 

La pipeline configurata con Maven automatizza:

1. Checkout del repository
2. Setup di Java 21
3. Build Maven ed esecuzione dei test
4. Copia dei report Surefire nella repository
5. Estrazione del numero di test falliti per classe
6. Calcolo della copertura per classe
7. Analisi dei test smells per classe con tsDetect
8. Aggiornamento di README.md con i risultati

---

## tsDetect

tsDetect rileva i test smells (possibili problemi nei test unitari).

Presente nella cartella tools/tsdetect.

I suoi risultati sono salvati in tools/tsdetect/output.csv.

---

## JaCoCo

JaCoCo calcola la copertura del codice.

I suoi risultati sono salvati in coverage/jacoco/jacoco.csv

---

## Surefire

Surefire esegue i test JUnit e genera report XML.

I reports sono salvati in reports/surefire sia in formato txt che xml.

---

## Utilizzo del Progetto

1. Salvare le classi sorgenti in `src/main/java/`
2. Salvare i test in `src/test/java/` con suffisso Test.java
3. Fare push o una pull request.

> La CI aggiorna automaticamente il README.md con i risultati per ogni classe.

---

