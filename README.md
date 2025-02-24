# Java client-server applications

This repository contains **client-server applications** from my university exams. Each exam folder includes the Java source code for the client, a compiled JAR server, and a PDF with exam instructions.

## How to compile and run
### Prerequisites
- Install **Java**
- (Optional) Use **Eclipse IDE** to open the projects.

### Running the client
1. Navigate to an exam directory:
   ```sh
   cd exam/16-07-2020
   ```
2. Compile the source files:
   ```sh
   javac -d bin *.java
   ```
3. Run the client:
   ```sh
   java -cp bin ClientMain
   ```
4. Run the server, if using the pre-built JAR:
   ```sh
   java -jar server.jar
   ```

## Notes
- These are my own **exam solutions**, so they may not be optimized but **all of them work!** 😊  
- Feel free to explore, modify, and learn from them.  