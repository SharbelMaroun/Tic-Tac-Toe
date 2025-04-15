[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/_awnA8HA)

# OCSF Mediator Example - X-O Game (Tic-Tac-Toe)

This project is a Java-based implementation of a simple X-O (Tic-Tac-Toe) game that demonstrates the use of a **client-server architecture** using **OCSF (Object Client-Server Framework)** and the **Mediator Design Pattern** via EventBus.

It includes a graphical user interface (JavaFX) and serves as an educational example of modular Java programming and design patterns.

---

## 🧩 Structure
This project contains three main modules:

1. **client** - A JavaFX client that interacts with the server and uses EventBus (Mediator Pattern) for internal communication between components like `SimpleClient` and `PrimaryController`.

2. **server** - A simple server built using the OCSF framework to manage and process client requests.

3. **entities** - A shared module containing common data structures and classes used by both the client and the server.

---

## 🚀 Running the Project
