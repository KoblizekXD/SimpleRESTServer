package com.koblizek.test;

import com.koblizek.server.RESTManager;
import com.koblizek.server.RESTServer;
import com.koblizek.server.request.Request;
import com.koblizek.server.util.Component;
import com.koblizek.server.util.annotations.Get;

import java.io.IOException;
import java.util.HashMap;

public class Main implements Component {
    public static void main(String[] args) throws IOException {
        RESTServer server = RESTManager.createNewServer();
        server.registerComponent(UserComponent.class);
        server.registerComponent(Main.class);
        server.publish(8080);
    }
    @Get("/hw")
    public void getHelloWorld(Request request) throws IOException, IllegalAccessException {
        var map = new HashMap<String, Integer>();
        map.put("abc", 1);
        map.put("a", 1);
        map.put("v", 1);
        map.put("ed", 1);
        request.sendTable(map);
    }
}
