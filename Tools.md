# Tools 
In the following, some tools for backend development are introduced



## Protobuf (Protocoll Buffer)
**Problem:** How to send information across network/internet?
**Solution:** Use a **Data Format** like, `XML`, `JSON`, `Protobuf`.
<br>
**Definition:** Protocol Buffers (protobuf) is a method for serializing structured data in a `binary format`. 
- **Serialization:** protobuf can serialize from and deserialize to almost any programming lanugage
- **Storage:** storing serialized data in protobuf format is `byte-efficient` - around 100% byte reduction compared to JSON
- **Strong Typing:** it declares and uses `strong typing`, resulting in low error rate

### Example
The following example illustrates how to:
- define a class/schema
- use generated code to create an object
- deserialize
- serialize

1. **Schema/Class Definition:** In a proto file `person.proto` define a class/schema 
    ```protobuf
    syntax = "proto3";
    message Person {
        string name = 1;
        int32 age = 2;
    }
    ```
2. **Create autogenerated (Java) class:** Based on this proto file, autogenerate code
    ```
    protoc --java_out=. person.proto
    ```
3. **Load classes:** Based on autogenerated class(es), create new objects
    ```java
    import com.example.PersonProto.Person;

    // Create a Person object
    Person person = Person.newBuilder()
        .setName("John Doe")
        .setAge(30)
        .build();
    ```

4. **De/Serialization:** Now, de/serialization can be performed
    ```java

    // Serialize: object --> bytes
    byte[] serializedData = person.toByteArray();

    // Deserialize: bytes --> object
    Person deserializedPerson = Person.parseFrom(serializedData);
    ```