# pc-stock

Util that helps you manage certain files in your personal computer.
My main use case for it is to manage my videos and music files.
Sample filter files is provided in the main `resources` directory.

# Pre-requisites

* Java 21
* Maven

# Build
This is a simple java desktop application.
To build it, you need to have maven installed.
To build the project, run the following command in the root directory:

```bash
mvn clean package
```
Then look for the fat jar in the `target` directory, which will be named something like `pc-stock-fat.jar`.

# Run
To run the application, you can use the following command:

```bash 
java -jar target/pc-stock-fat.jar
```