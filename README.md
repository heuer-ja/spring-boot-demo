# spring-boot-demo

Small demo application for Java Spring Boot.




<br>
<br>


# Table of Contents
- [1. About Spring](#1.-About-Spring) 
  - [1.2. Spring Boot CLI (Commands)](#todo)
    - [File Structure](#todo)
    - [Application Properties](#todo)

- [2. How Spring (Boot) Works Under The Hood](#2.-How-Spring-Boot-Works-Under-The-Hood)
  - [2.1. Inversion of Control (IoC)](#todo)
    - [Dependency Injection (DI)](#todo)
    - [Beans](#todo)
    - [Spring IoC Container](#todo)
  - [2.2. Implementation of IoC](#todo)
     - [XML configuration files (deprecated)](#todo)
     - [Spring Annotation](#todo)
     - [Java configuration files](#todo)
  - [2.3. Spring Annotations (Details)](#todo)
    - [Bean Annotations](#todo)
    - [Autowiring Annotations (deprecated)](#todo)
    - [Configuration File Annotations](#todo)
    
- [3. Working with Data](#3.-Working-with-Data)



<br>
<br>

# 1. About Spring
## 1.1. Setup Spring Boot Web App
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



## 1.2. Spring Boot CLI (Commands)
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










<br>
<br>

# 2. How Spring (Boot) Works Under The Hood

## 2.1. Inversion of Control (IoC)
*IoC* is a **design principle** that shifts the control of **object creation and management** from the developer/application code to a container/framework. Instead of manually creating and managing objects/instances, IoC suggests to define components (in `.xml (deprecated) or .java configuration file`) and **let a Framework (Spring) automatically take care** of their instantiation and lifecycle management. This results in less coupling between classes, increases modularity and testability.  

IoC is achieved through different mechanisms:
- **Dependency Injection (DI)**
- **Factory Pattern**
- *Strategy design pattern*
- *Service Locator pattern*

### Dependency Injection (DI)
*DI* is a key part of *IoC*. It's a pattern where the **required dependencies** of a class are **injected from external sources**, typically by the Spring framework. Spring manages these dependencies as `Beans`, which are specified in a configuration file. 

Let's consider an example of a Car class that depends on an Engine:

1. **Without IoC / DI:** 
   
   In this case, the Car class tightly couples itself to the Engine, making it challenging to change the engine type or perform unit testing without creating an actual engine.
   ```java 
   public class Car {
       private Engine engine;
   
       public Car() {
           this.engine = new Engine();  // DEPENDENCY
       }
   }
   ```
2. **With IoC / DI:**

   Car class relies on an Engine instance passed to it, and the Engine implementation can be updated easily.
   
   ```java
   public class Car {
       private Engine engine;
   
       public Car(Engine engine) { // DEPENDENCY INJECTED
           this.engine = engine;
       }
   }
   ```


### Beans
The concept of *Beans* goes along with *IoC*. A *Bean* represents an object managed by the `Spring IoC Container`, so their object creation shifts from developer/application code to the Spring framework. 

- **Defining Beans:** *Beans* are defined through Java classes or `.xml (deprecated) configuration files`. By annotating a Java class with `@Component`, `@Service`, `@Repository`, or `@Controller` this class becomes a *Bean*.  This annotation indicates to Spring that it should manage the lifecycle and dependencies of these components.


- **Singleton Scope (Default):** By default, a *Bean* is a *Singleton*. This means that only one single instance of the *Bean* is created and shared across the application context - this is ensured by the `Spring IoC Container`. 
 

- **Bean Scopes:** Next to *Singleton Scope*, there exist few other scopes. The choice of scope is an essential part of IoC, as it determines how beans are created, shared, and managed within the application context.
   
   | Scope         | Description                                                                                 | Class Annotation               |
   |---------------|---------------------------------------------------------------------------------------------|--------------------------------|
   | Singleton     | single instance is created and shared across the application context.                       | Default - No annotation needed |
   | Prototype     | new instance is created whenever requested, offering control and modularity.                | `@Scope("prototype")`          |
   | Request       | new instance is created for each HTTP request, ensuring isolation for web applications.     | `@Scope("request")`            |
   | Session       | new instance is created for each user session, vital for maintaining state across sessions. | `@Scope("session")`.           |
   | Custom Scopes | define custom scopes to accommodate specific requirements, demonstrating IoC flexibility.   | needs to be defined            |

### Spring IoC Container
The **Spring IoC Container** is the core of the Inversion of Control (IoC) pattern in the Spring framework. It is **responsible for managing the lifecycle of Java objects (Beans)** and handling their dependencies. The container is configured to create, initialize, and assemble objects, ensuring that the application components are loosely coupled. There are two main types of IoC Containers in Spring: **BeanFactory** and **ApplicationContext (recommended)**.

1. **BeanFactory:** The **BeanFactory** is the simplest IoC container and is part of the core of the Spring framework. It manages objects created based on the configuration metadata that you supply in the form of XML files (deprecated), Java annotations, or Java code. BeanFactory provides the basic support for Dependency Injection (DI) and the ability to manage Bean Scopes. However, it is recommended for simpler applications.

   ```java
   // Example of using BeanFactory
   BeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
   MyBean myBean = (MyBean) factory.getBean("myBean");
   ```

2. **ApplicationContext (recommended):** The ApplicationContext*is an advanced container that builds upon the capabilities of the BeanFactory. It is suitable for most applications and provides additional features such as message resource handling, event propagation, and transparent integration with various enterprise services. ApplicationContext is a more feature-rich container and is commonly used in Spring applications.

   ```java
   // Example of using ApplicationContext
   ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
   MyBean myBean = (MyBean) context.getBean("myBean");
   ```
<br>

**BeanFactory vs. ApplicationContext**:

| Aspect               | BeanFactory                  | ApplicationContext (recommended) |
|----------------------|------------------------------|----------------------------------|
| **Use Case**         | Simpler applications         | Most applications                |
| **Features**         | Basic IoC container features | Advanced features, AOP, events   |
| **Performance**      | Lightweight                  | Slightly heavier                 |
| **Resource Loading** | Lazy loading                 | Lazy or eager loading            |
| **Dependency Check** | Manual check required        | Automatic dependency checking    |
| **Pros**             | Lightweight and efficient    | Comprehensive features           |
| **Cons**             | Limited for advanced apps    | Slightly heavier                 |



## 2.2. Implementation of IoC
To realize IoC in the terms of code, Spring offers three ways to accomplish it:
1. **XML configuration files (deprecated)**
2. **Spring Annotations**
3. **Java configuration files**

All of these ways handle how `Beans` are defined, created, and managed.


### XML configuration files (deprecated)
This configuration is a traditional/old-school approach for configuring Spring applications. This provides **clear separation** between application logic and configuration details, making it suitable for externalizing configuration. However, as an application scales, XML files can become **verbose and challenging to maintain** due to their inherent verbosity and **limited type safety**. They remain a viable option for configuration but are **less favored** in modern Spring applications.


Example XML-Configuration File:
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- DEFINITION OF BEANS & DEPENDENCIES -->
    <bean id="userService" class="com.example.UserService">
        <property name="userRepository" ref="userRepository" />
    </bean>

    <bean id="userRepository" class="com.example.UserRepository" />
</beans>
```

Loading XML Configuration through `ApplicationContext`:
```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApplication {

 public static void main(String[] args) {
     // Load XML-based configuration
     ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

     // Retrieve beans and work with them
     UserService userService = context.getBean(UserService.class);
     // ... perform actions with the userService or other beans
 }
}
```

### Spring Annotation
To tackle the problems of `.xml files` (verbosity, maintainability, no type-safety), Spring introduced *Annotations*. They are metadata tags that provide configuration information to the `Spring IoC Container` for classes, methods, and instance variables. Details are in another section.

### Java configuration files
Java-based configuration strikes a balance between XML and annotation-based configuration.It is an approach where Spring configuration is defined using Java classes annotated with `@Configuration`. These classes contain methods annotated with `@Bean`, where individual beans and their dependencies are defined.

   ```java
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   
   @Configuration
   public class AppConfig {
   
        @Bean
        public UserService userService(UserRepository userRepository) {
            return new UserService(userRepository);
        }
   
        @Bean
        public UserRepository userRepository() {
            return new UserRepository();
        }
   }
   ```



## 2.3. Spring Annotations (Details)
To tackle the problems of `.xml files` (verbosity, maintainability, no type-safety), Spring introduced *Annotations*. They are metadata tags that provide configuration information to the `Spring IoC Container` for classes, methods, and instance variables.

**Overview**

| Annotation          | Tag                     | Description                                                                                              |
|---------------------|-------------------------|----------------------------------------------------------------------------------------------------------|
| `@Component`        | Bean Annotation         | Marks a class as a general Spring-managed component.                                                     |
| `@Repository`       | Bean Annotation         | Specialization of `@Component` for data access classes.                                                  |
| `@Service`          | Bean Annotation         | Specialization of `@Component` for service/business logic classes.                                       |
| `@Controller`       | Bean Annotation         | Specialization of `@Component` for handling HTTP requests in Spring MVC.                                 |
| `@Autowired`        | Dependency Injection    | Used to inject dependencies into Spring beans, supporting constructor, setter, and field injection.      |
| `@Configuration`    | Configuration           | Marks a class as a source of bean definitions using Java-based configuration.                            |
| `@Bean`             | Configuration           | Used within `@Configuration` classes to define individual beans and their dependencies.                  |


### Bean Annotations
Classes can be defined as **Beans**, using one of these annotations:

| Annotation  | Purpose                                                                                                |
|-------------|--------------------------------------------------------------------------------------------------------|
| @Component  | Default annotation that indicates a class should be managed as a Spring bean.                          |
| @Controller | Used for handling HTTP requests and routing them to appropriate controller methods in Spring MVC.      |
| @Service    | Used for marking business logic or service layer classes.                                              |
| @Repository | Used for marking data access classes, particularly those that interact with a database or data source. |

<br>

**Example Code**
```java
@Component / @Controller / @Service / @Repository
public class ClassName {
   ...
}
```
<br>

**Details:**

- **@Controller**: 
   In Spring Framework, a controller, such as a **RestController**, is a key component responsible for handling incoming **HTTP requests**, processing them, and returning an **HTTP response**.
   <br>
   <br>
   **RestControllers**, in particular, are commonly used in Spring to create **RESTful web services**. These controllers typically define methods that map to specific endpoints or URLs and are responsible for handling HTTP methods like GET, POST, PUT, or DELETE. They process the incoming data, perform business logic, and return data in a format such as JSON or XML. The `@RestController` annotation is used to define a class as a REST controller, while @RequestMapping is used to map specific endpoints to controller methods.
   <br>
   <br>
   *Related syntax:*
   
   | Syntax                         | Description                                   |
   |--------------------------------|-----------------------------------------------|
   | `@RestController`              | Annotates a class as a REST controller.       |
   | `@RequestMapping(String path)` | Maps a method to a specific URL path.         |
   | `@GetMapping(String path)`     | Maps a method to handle HTTP GET requests.    |
   | `@PostMapping(String path)`    | Maps a method to handle HTTP POST requests.   |
   | `@PutMapping(String path)`     | Maps a method to handle HTTP PUT requests.    |
   | `@DeleteMapping(String path)`  | Maps a method to handle HTTP DELETE requests. |
   | `@PatchMapping(String path)`   | Maps a method to handle HTTP PATCH requests.  |


<br>

- **@Repository**: 
  A repository is a critical component used for **data access and management**. It simplifies the interaction with databases, file systems, or other data sources by providing a high-level, **abstracted interface**.
 <br>
 <br>
  Repositories are primarily used for performing **CRUD** (Create, Read, Update, Delete) operations on data entities. They often extend Spring Data interfaces like `CrudRepository` and `JpaRepository` to gain access to common data access methods, enabling developers to write less boilerplate code when working with databases. By using annotations like `@Repository` and configuring data sources, repositories make it easy to integrate and manage data within Spring applications.
  <br>
  <br>
   *Related syntax:*
   
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


### Autowiring Annotations (deprecated):
This annotation is used to **automatically inject dependencies** into a  Bean (*Dependency Injection*). Nowadays, it is **not recommended** to use `@Autowired`. They are different kinds of types for this annotation:

- **Constructor-based (recommended):** In case a Bean has multiple constructors, constructor-based autowiring helps to specify which one should be called automatically by the `Spring IoC Container`. If multiple constructors are annotated, it takes the constructor with **the longest signature**, in case all dependencies can be resolved, i.e., they exist as Beans inside the `Spring IoC Container`.
  
   ```java
   @Component // bean class
      public class MyComponent {
      private final MyDependency dependency;  // FINAL member :)
      
       // constructor
       @Autowired
       public MyComponent(MyDependency dependency) {
           this.dependency = dependency;
       }
      }
   ```
   <br>
- **Setter-based:** Sometimes, it is not possible to inject all dependencies at once to the constructor (e.g., classes need to be created). Instead, Spring can use setter-based autowiring to dynamically inject dependencies. 

   ```java
   @Component
   public class MyComponent {
       private MyDependency dependency; 
   
       // setter
       @Autowired
       public void setDependency(MyDependency dependency) {
           this.dependency = dependency;
       }
   }
   ```
   <br>
   
- **Field-based:** Here, a class instance is annotated. Should be omitted. 
   ```java
   @Component
   public class MyComponent {
      // field or instance variable
      @Autowired
      private MyDependency dependency;
   }
   ```
   <br>
   


### Configuration File Annotations
To define **Java configuration files** there are specific annotations:


**Example Code:**
```java
@Configuration
public class MyConfiguration {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```







<br>
<br>

# 3. Working with Data
For this demo project it is too complicated to work with PostgreSQL or Co. Instead, `H2` is used. 

## H2 Database
To use this **embedded** database, do the following steps:

1. **Dependencies:** In pom.xml add
    ```xml
   <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
    
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
   <dependencies>
    ```

2. **Install dependencies:** run mvn or build app 
3. **Configure h2-console:** add following environment variables in file `application.properties`
    ```properties
    spring.h2.console.enabled=true
    spring.datasource.generate-unique-name=false 
    spring.datasource.name={name, e.g., content}
    ```
4. **Connect to Database:** open `localhost:{port}/h2-console` and add the input given by Spring Boot's console output
    ```json
   JDBC URL : jbdc:h2:mem:{name}
   User Name: {user name}
   Password : {pw}
   ```
      
    Press `Test Connection` and `Connect`.


## QueryDSL
**Problem to Solve -:**
How to improve the querying capabilities in a (Java) application, allowing for more dynamic and complex queries without using `SQL` directly.

**Solution - Integrated Querying-support:** Introduce an abstracted query language (e.g., SQL abstraction).  

### QueryDSL
**Definition:**
QueryDSL is a library that provides a ***Java-based domain-specific language (DSL)*** for constructing type-safe and dynamic SQL, JPQL (Java Persistence Query Language), and other ***queries***. Queries are expressed as code, enhancing the readability, maintainability, and flexibility of query logic in their applications.

**Concepts:**

- **Abstraction:** QueryDSL can abstract `SQL`, `JPQL`, `MongoDB`.
- **Entity Mapping:** Classes annotated with `@Entity` (JPA entities) are used to create query expressions and perform database operations.
- **Auto-Completion:** By using path expressions QueryDSL references to properties/columns, e.g., `QEmployee.employee` (Table QEmployee, column employee). 
- **Querying:** It provides methods for methods like `where()`, `orderBy()`, and `select()`.  



**Adding to Maven Project**: To add `OpenDSL` to a maven project, paste following tags into the `pom.xml`:

```xml
<properties>
    <querydsl.version>5.0.0</querydsl.version>
</properties>
...
<dependencies>
    <dependency>
        <groupId>com.querydsl</groupId>
        <artifactId>querydsl-apt</artifactId>
        <version>${querydsl.version}</version>
        <classifier>jakarta</classifier>
        <scope>provided</scope>
    </dependency>
    
    <dependency>
        <groupId>com.querydsl</groupId>
        <artifactId>querydsl-jpa</artifactId>
        <classifier>jakarta</classifier>
        <version>${querydsl.version}</version>
    </dependency>
</dependencies>
```

**Example Code:** In the following there are exemplary steps presented how to use `OpenDSL`:

1. **Define `@Entity` classes:** These classes are mirrored as tables by `JPA`. 
    
    ```java
    @Entity
    public class User {
    
        @Id
        @GeneratedValue
        private Long id;
    
        private String login;
    
        private Boolean disabled;
    
        @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
        private Set<BlogPost> blogPosts = new HashSet<>(0);
    
        // getters and setters
    
    }
    
    @Entity
    public class BlogPost {
    
        @Id
        @GeneratedValue
        private Long id;
    
        private String title;
    
        private String body;
    
        @ManyToOne
        private User user;
    
        // getters and setters
    
    }
    ```

2. **Generate QClasses for  model:** QClasses are auto-generated classes representing the class as part of the database model
    ```mvn compile```
3. **Exploring Generated Classes:** QClasses can be found in `target/generated-sources/java`. 
    ```java
   @Generated // do not modify a QClass manually
   class QUser {
        ...
        public static final QUser user = new QUser("user"); // instance  used for querying
        ...
   }
    ```
4. **Querying:**
    ```java
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.baeldung.querydsl.intro");
    EntityManager em = entityManagerFactory.createEntityManager();
    JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, em);
    ```
   
    and
    ```java
    QUser user = QUser.user;

   //==============================================
   // Select
   User c = queryFactory.selectFrom(user)
        .where(user.login.eq("David"))
        .fetchOne();
   
   // Ordering and Grouping
   List<User> c = queryFactory.selectFrom(user)
          .orderBy(user.login.asc())
          .fetch();
   
   NumberPath<Long> count = Expressions.numberPath(Long.class, "c");

   List<Tuple> userTitleCounts = queryFactory.select(
    blogPost.title, blogPost.id.count().as(count))
        .from(blogPost)
        .groupBy(blogPost.title)
        .orderBy(count.desc())
        .fetch();
   
   //  Complex Queries With Joins and Subqueries
   QBlogPost blogPost = QBlogPost.blogPost;

    List<User> users = queryFactory.selectFrom(user)
        .innerJoin(user.blogPosts, blogPost)
        .on(blogPost.title.eq("Hello World!"))
        .fetch();
   
   // Subquery
   List<User> users = queryFactory.selectFrom(user)
        .where(user.id.in(JPAExpressions.select(blogPost.user.id)
        .from(blogPost)
        .where(blogPost.title.eq("Hello World!"))))
        .fetch();
   
   //==============================================
   // Update 
   queryFactory.update(user)
        .where(user.login.eq("Ash"))
        .set(user.login, "Ash2")
        .set(user.disabled, true)
        .execute();
   
   //==============================================
   // Delete 
   queryFactory.delete(user)
    .where(user.login.eq("David"))
    .execute();
    ```


