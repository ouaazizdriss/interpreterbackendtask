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

### Runing the endpoint

The Note Book endpoint is '/execute'
You can test it by sending a POST request to 'http://localhost:8080/execute'

Examples:
This example will use your IP as a session identifier.
```
{
  "code": "%python print(1+1)"
}
```

This example will use the session Id 'session-01'
```
{
  "code": "%python print(1+1)",
  "sessionId": "session-01"
}
```

PS: don't forget to add this header to your request: 
```
Content-type: application/json

```
