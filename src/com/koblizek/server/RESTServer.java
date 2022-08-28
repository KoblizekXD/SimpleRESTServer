package com.koblizek.server;

import com.koblizek.server.util.Component;

public abstract class RESTServer {
    public RESTServer() {

    }
    public abstract RESTServer registerComponent(Class<? extends Component> componentClass);
    public abstract void publish(int port);
    public abstract void stop();
}
