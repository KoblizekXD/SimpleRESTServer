package com.koblizek.server;

import com.koblizek.server.util.Component;

public interface RESTServer {
    RESTServer registerComponent(Class<? extends Component> componentClass);
    void publish(int port);
    void stop();
}
