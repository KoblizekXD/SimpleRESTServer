package com.koblizek.server.request;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public final class Request {
    private final HttpExchange exchange;
    public Request(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public HttpExchange getRaw() {
        return exchange;
    }
    public void send(String text) throws IOException {
        exchange.sendResponseHeaders(200, text.length());
        OutputStream stream = exchange.getResponseBody();
        stream.write(text.getBytes());
        stream.close();
    }
}