package com.koblizek.server;

import com.koblizek.server.util.Component;

import java.io.IOException;

public abstract class RESTServer {
    public RESTServer() {}
    public abstract RESTServer registerComponent(Class<? extends Component> componentClass);
    public abstract void publish(int port) throws IOException;
    public abstract void stop();
}
