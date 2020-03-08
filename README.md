# Java / Spring Boot Notebook Server
# Installation 
You need to install these tools to test this project:

### GraalVM 
You can download graalVM from the [GraalVM page](https://www.graalvm.org/)

Add GraalVM directory to your PATH.
```
$ export PATH="GRAALVM_PATH/bin:$PATH"
```

Install python for GraalVM using the graal updater
```
$ gu install python
```
### Maven
Install the last version of maven.
In the root of the project execute the command for compiling the projet:

```
$ mvn clean install -DskipTests
```

The jar is in the target directory. You can execute it using 'java -jar' command.
