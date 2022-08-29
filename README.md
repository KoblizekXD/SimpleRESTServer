# Simple REST Server in Java
## Installation  
You can install the latest release in gh release page
## Usage  
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
You can also find some other examples in `src/com/koblizek/test/`
