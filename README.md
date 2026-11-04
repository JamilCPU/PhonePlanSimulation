# Phone Plan Simulation

A console application that simulates a small phone-carrier business. You generate fake customers, sign them up for plans from six US carriers, and delete customers or contracts, all from an interactive text menu. It was written as a Java practice project around object modelling, JSON data loading and menu-driven input handling.

## Stack

- Java 17, built with Maven
- Gson for reading `carrierplans.json`
- JavaFaker for generating random customer names, addresses and phone numbers
- jsoup and json-simple are declared in `pom.xml` but are not used by the current code

## Running

The program reads `carrierplans.json` from the working directory, so run it from the repository root.

```
mvn compile
mvn dependency:build-classpath -Dmdep.outputFile=cp.txt
java -cp "target/classes:$(cat cp.txt)" main
```

On Windows use `;` instead of `:` in the classpath. Alternatively, open the project in an IDE with Maven support and run the `main` class.

The menu offers four actions:

1. Create a new customer (randomly generated)
2. Create a contract between a customer and a carrier, choosing one of the carrier's plans
3. Delete a customer (their contracts are removed too)
4. Delete a contract

## Project layout

- `src/main/java/main.java` - menu loop and carrier loading
- `src/main/java/Carrier.java` - carrier and plan data, account and phone number generation
- `src/main/java/Customer.java` - randomly generated customer
- `src/main/java/Contract.java` - links a customer, carrier and plan
- `carrierplans.json` - carriers, plan names and monthly prices (illustrative figures)

## Status

Hobby project, not actively developed. Input handling is basic (non-numeric input is not fully handled) and there are no automated tests.
