package com.koblizek.test;

import com.koblizek.server.RESTManager;
import com.koblizek.server.RESTServer;
import com.koblizek.server.util.Component;

import java.io.IOException;

public class Main implements Component {
    public static void main(String[] args) throws IOException {
        RESTServer server = RESTManager.createNewServer();
        server.registerComponent(Main.class);
        server.publish(8080);
    }
}
