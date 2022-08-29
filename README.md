# Simple REST Server in Java
## Installation  
You can install the latest release in gh release page
## Usage
##### If it is not JSON:
```java
// Components means it supports accepting requests
public class Main implements Component {
    public static void main(String[] args) throws IOException {
        // Create REST server
        RESTServer server = RESTManager.createNewServer();
        // Register component
        server.registerComponent(Main.class);
        // Publish server on port
        server.publish(8080);
    }
    // Get request
    @Get("/hw")
    public void getHelloWorld(Request request) throws IOException {
        // Sends back "Hello world"
        request.send("Hello world");
    }
}
```
##### If it is JSON:

```java
// Components means it supports accepting requests
public class Main implements Component {
    public static void main(String[] args) throws IOException {
        // Create REST server
        RESTServer server = RESTManager.createNewServer();
        // Register component
        server.registerComponent(Main.class);
        // Publish server on port
        server.publish(8080);
    }

    // Get request
    @Get("/hw")
    public void getHelloWorld(Request request) throws IOException {
        // Sends back json: {"a", 1}
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        request.sendTable(map);
    }
}
```
You can also find some other examples in `src/com/koblizek/test/`
