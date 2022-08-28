package com.koblizek.server;

import com.koblizek.server.util.impl.RESTServerImpl;

public final class RESTManager {
    private RESTManager() {}

    public static RESTServer createNewServer() {
        return new RESTServerImpl();
    }
}
