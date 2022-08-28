package com.koblizek.server;

import com.koblizek.server.util.impl.RESTServerImpl;

import java.io.IOException;

public final class RESTManager {
    private RESTManager() {}

    public static RESTServer createNewServer() throws IOException {
        return new RESTServerImpl();
    }
}
