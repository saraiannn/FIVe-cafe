# FIVe Cafè Management System

A **Java-based management system** for a café, developed using **Maven** and **JavaFX**.  
The project simulates a **self-service ordering totem** with a **separate order display**, following the **Boundary – Control – Entity (BCE)** architectural pattern and applying key **Design Patterns**.

---

## Main features

- Order creation via totem
- Beverage selection using Factory pattern
- Dynamic ingredient addition using Decorator pattern
- Order status management
- Order display updates using Observer pattern
- JavaFX GUI

---

## Architecture

The project follows the **BCE (Boundary – Control – Entity)** pattern:

- **Boundary**: user interaction and GUI
- **Control**: application logic
- **Entity**: domain model

Design Patterns used:
- Factory
- Decorator
- Observer

---

## Project structure
src/main/it.fiv.FIVecafe
├── boundary
| ├── BarmanDisplayFX.java
│ ├── MainFX.java
│ ├── OrderDisplay.java
│ └── TotemInterface.java
│
├── control
│ ├── Barman.java
│ ├── BeverageFactory.java 
│ └── OrderManager.java
│
├── entity
│ ├── BasicBeverage.java 
│ ├── Beverage.java 
│ ├── BeverageDecorator.java
│ ├── CaramelDecorator.java
| ├── CocoaDecorator.java
| ├── MilkDecorator.java
| ├── Order.java
| ├── OrderStatus.java
│ └── SugarDecorator.java
│
├── observer
│ └── OrderObserver.java
|
├── Main.java

---

## Requirements

Before running the project, make sure you have:

- **Java JDK 21**
- **Maven 3.8+**
- **IntelliJ IDEA** (recommended)

Check your Java version:
```bash
java -version
```

---

## How to run the project
### 1. Clone the repository
git clone https://github.com/<username>/FIVe-cafe.git
cd FIVe-cafe

### 2. Import the project into IntelliJ
- File -> Open
- Select the project folder
IntelliJ will automatically detect the Mavem configuration

### 3. Run the JavaFX application
#### Do NOT use the IntelliJ 'Run' button
Instead, open the integrated terminal (second icon in the left corner of the IDE) and run the following command:
```bash
mvn clean javafx:run 
```
This command will download JavaFX dependencies, configure the JavaFX runtime and launch the application.

---

## Common issues
### Error: JavaFX runtime components are missing
- Make sure you are using Java 21
- Always run the app using mvn javafx:run
### Error: ClassNotFoundException: MainFX
- Ensure the package name of MainFX matches the one defined in pom.xml
```xml
<mainClass>it.fiv.FIVecafe.boundary.MainFX</mainClass>
```

---

A console-based version of the application is included for educational purposes.
The JavaFX GUI is the current version under development.

---

## Author
Project developed for educational purposes as part of a university course.
