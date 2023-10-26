# spring-boot-demo

Small demo application for Java Spring Boot.


## Setup Spring Boot Web App
- `start.spring.io` - Initialize app here. Then download the zip, unpack it, and open it with IntelliJ
- `localhost:8080` - UI of your WebApp

### File Structure
ALl the code should be inside `src/main/java/{PACKAGE}`. Then, divide code into following logical sections:
```
- src/main/java/{PACKAGE}
  - controller
  - model
  - service
  - Application.java
```

## Spring Boot CLI (Commands)
*Hint: If `maven` is installed, type `mvn` instead of `./mvnw`, e.g., `mvn spring-boot:run`.*

In Powershell/CMD you can run:

| Command                                   | Description                                                     |
|-------------------------------------------|-----------------------------------------------------------------|
| `spring init`                             | Create a new Spring Boot project.                               |
| `./mvnw spring-boot:run`                  | Run a Spring Boot application.                                  |
| `./mvnw package`                          | Package a Spring Boot application as a JAR file.                |
| `java -jar target/my-spring-boot-app.jar` | Run the packaged Spring Boot application.                       |
| `spring generate project`                 | Generate a basic project skeleton.                              |
| `spring jar list`                         | List available dependencies for your project.                   |
| `spring --version`                        | Display Spring Boot CLI version.                                |
| `spring --info`                           | Display information about the installed Spring Boot CLI.        |
| `spring shell`                            | Open an interactive shell for Spring Boot application tasks.    |
| `spring help`                             | Get a list of available commands and their descriptions.        |
| Custom Commands                           | Create your own custom commands and plugins for specific tasks. |

## TODO: Learnings
- Beans
- Components

## Test Paragraph 4 Git
Hello this is a test