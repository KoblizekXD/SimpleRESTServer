package com.koblizek.server.util;

public class RESTServerException extends RuntimeException {
    public RESTServerException() {
        super();
    }
    public RESTServerException(String text) {
        super(text);
    }
}
