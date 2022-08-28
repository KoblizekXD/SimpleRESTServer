package com.koblizek.server;

import java.lang.reflect.Method;

public class FunctionalComponent {
    public enum Type {
        GET,
        POST,
        PUT,
        DELETE
    }

    private final Type type;
    private final String path;
    private final Method method;

    public FunctionalComponent(Type type, String path, Method method) {
        this.path = path;
        this.type = type;
        this.method = method;
    }
    public Type getType() {
        return type;
    }
    public String getPath() {
        return path;
    }
    public Method getMethod() {
        return method;
    }
}
