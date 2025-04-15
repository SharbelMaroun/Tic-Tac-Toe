[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/_awnA8HA)

# OCSF Mediator Example - X-O Game (Tic-Tac-Toe)

This project is a Java-based implementation of a simple X-O (Tic-Tac-Toe) game that demonstrates the use of a **client-server architecture** using **OCSF (Object Client-Server Framework)** and the **Mediator Design Pattern** via EventBus.

It includes a graphical user interface (JavaFX) and serves as an educational example of modular Java programming and design patterns.

---

## Structure
Pay attention to the three modules:
1. **client** - a simple client built using JavaFX and OCSF. We use EventBus (which implements the mediator pattern) in order to pass events between classes (in this case: between SimpleClient and PrimaryController.
2. **server** - a simple server built using OCSF.
3. **entities** - a shared module where all the entities of the project live.

## Running
1. Run Maven install **in the parent project**.
2. Run the server using the exec:java goal in the server module.
3. Run the client using the javafx:run goal in the client module.
4. Press the button and see what happens!
