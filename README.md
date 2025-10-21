# Java client-server applications exercises

This repository contains client-server applications exercises from my university exams. Each exam folder includes the Java source code for the client, a compiled JAR server, and a PDF with exam instructions.

## How to compile and run
### Prerequisites
- Install Java
- (Optional) Use Eclipse to open the projects.

Clone the repository:
```sh
git clone https://github.com/chitvs/pds.git
cd pds
```

### Running the client
1. Navigate to an exam directory:
   ```sh
   cd src/<year>/<month>/
   ```
2. Compile the source files:
   ```sh
   javac -d bin *.java
   ```
3. Run the client and server:
   ```sh
   java -cp bin ClientMain & java -jar server.jar
   ```
