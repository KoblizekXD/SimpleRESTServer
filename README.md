# Simple REST Server in Java
## Installation
You can install the latest release in gh release page
## Usage
Here are some types of using the library
##### Very Basic Server
```java
// implementing Component interface is must for handling requests
public class Main implements Component {
    public static void main(String[] args) throws IOException {
        // Create REST server
        RESTServer server = RESTManager.createNewServer();
        // Register component
        server.registerComponent(Main.class);
        // Publish server on port
        server.publish(8080);
        // Stops the server
        server.stop();
    }
    // Get request
    @Get("/hw")
    public void getHelloWorld(Request request) throws IOException {
        // Sends back "Hello world"
        request.send("Hello world");
    }
}
```  
##### Example of sending JSON

```java
import com.koblizek.server.util.json.JSON;

public class Main implements Component {
    // Get request
    @Get("/hw")
    public void getHelloWorld(Request request) throws IOException {
        // Sends back JSON
        request.sendJSON(new JSON<>());
    }
}
``` 
##### Example of sending Table

```java
import com.koblizek.server.util.Table;
import com.koblizek.server.util.annotations.table.TableComponent;

public class Main implements Component {
    // Get request
    @Get("/hw")
    public void getHelloWorld(Request request) throws IOException {
        // Sends back {"id":69, "name":"Pepa"}
        request.sendTable(new CustomTable(69, "Pepa"));
    }
}
public class CustomTable implements Table {
    @TableComponent
    public final int id;
    @TableComponent
    public final String name;
    public CustomTable(int id, String name) {
        this.name = name;
        this.id = id;
    }
}
```
You can also find some other examples in `src/com/koblizek/test/`
