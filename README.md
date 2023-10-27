# spring-boot-demo

Small demo application for Java Spring Boot.


# About Spring
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

### Application Properties
The file `src/main/resources/application.properties` contains settings / environment variables. 

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


# Spring Boot's Vocabulary

## Controller
In Spring Framework, a controller, such as a **RestController**, is a key component responsible for handling incoming **HTTP requests**, processing them, and returning an **HTTP response**. 

**RestControllers**, in particular, are commonly used in Spring to create **RESTful web services**. These controllers typically define methods that map to specific endpoints or URLs and are responsible for handling HTTP methods like GET, POST, PUT, or DELETE. They process the incoming data, perform business logic, and return data in a format such as JSON or XML. The `@RestController` annotation is used to define a class as a REST controller, while @RequestMapping is used to map specific endpoints to controller methods.

**Related syntax and annotations:**

| Syntax                             | Description                                        |
|------------------------------------|----------------------------------------------------|
| `@RestController`                  | Annotates a class as a REST controller.            |
| `@RequestMapping(String path)`     | Maps a method to a specific URL path.              |
| `@GetMapping(String path)`        | Maps a method to handle HTTP GET requests.         |
| `@PostMapping(String path)`       | Maps a method to handle HTTP POST requests.        |
| `@PutMapping(String path)`        | Maps a method to handle HTTP PUT requests.         |
| `@DeleteMapping(String path)`     | Maps a method to handle HTTP DELETE requests.      |
| `@PatchMapping(String path)`      | Maps a method to handle HTTP PATCH requests.       |


## Repository
A repository is a critical component used for **data access and management**. It simplifies the interaction with databases, file systems, or other data sources by providing a high-level, **abstracted interface**.

Repositories are primarily used for performing **CRUD** (Create, Read, Update, Delete) operations on data entities. They often extend Spring Data interfaces like `CrudRepository` and `JpaRepository` to gain access to common data access methods, enabling developers to write less boilerplate code when working with databases. By using annotations like `@Repository` and configuring data sources, repositories make it easy to integrate and manage data within Spring applications.

**Related syntax:**

| Syntax                              | Description                                                                      |
|-------------------------------------|----------------------------------------------------------------------------------|
| `@Repository`                       | Annotates a class as a Spring Data repository.                                   |
| `CrudRepository<T, ID>`             | Interface for basic CRUD operations on entities.                                 |
| `PagingAndSortingRepository<T, ID>` | Extends `CrudRepository` with sorting and paging.                                |
| `JpaRepository<T, ID>`              | Extends `PagingAndSortingRepository` with additional JPA-specific functionality. |
| `@Query(String jpql)`               | Defines a JPQL (Java Persistence Query Language) query for a repository method.  |
| `@Param("paramName")`               | Binds a method parameter to a named query parameter.                             |
| `findBy{Property}`                  | Generates a query method to find entities by a specific property.                |
| `@Transactional`                    | Annotates a method or class as transactional.                                    |

You can use this table to explain the concept of repositories in Spring and Spring Boot, along with the associated syntax for creating and using repositories for data access.

## TODO: Learnings
- Beans
- Components
- Auto Configuration
- Dependency Injection