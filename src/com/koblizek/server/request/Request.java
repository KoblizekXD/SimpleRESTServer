package com.koblizek.server.request;

import com.koblizek.server.util.Table;
import com.koblizek.server.util.annotations.table.TableComponent;
import com.koblizek.server.util.json.JSON;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

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
    public void sendTable(Table tableClass) throws IOException, IllegalAccessException {
        exchange.getResponseHeaders().put("Content-Type", Collections.singletonList("application/json"));
        StringBuilder builder = new StringBuilder("{");
        for (int i = 0; i < tableClass.getClass().getFields().length; i++) {
            Field field = tableClass.getClass().getFields()[i];
            if (field.isAnnotationPresent(TableComponent.class)) {
                if (field.getType() == String.class) {
                    if (i+1 == tableClass.getClass().getFields().length) {
                        builder.append("\"")
                                .append(field.getName())
                                .append("\":\"")
                                .append(field.get(tableClass)).append("\"");
                    } else {
                        builder.append("\"")
                                .append(field.getName())
                                .append("\":\"")
                                .append(field.get(tableClass)).append("\",");
                    }
                } else {
                    if (i+1 == tableClass.getClass().getFields().length) {
                        builder.append("\"")
                                .append(field.getName())
                                .append("\":")
                                .append(field.get(tableClass));
                    } else {
                        builder.append("\"")
                                .append(field.getName())
                                .append("\":")
                                .append(field.get(tableClass)).append(",");
                    }
                }
            }
        }
        builder.append("}");
        exchange.sendResponseHeaders(200, builder.length());
        OutputStream stream = exchange.getResponseBody();
        stream.write(builder.toString().getBytes());
        stream.close();
    }
    public <K, V> void sendJSON(Map<K, V> map) throws IOException {
        if (map.isEmpty()) return;
        exchange.getResponseHeaders().put("Content-Type", Collections.singletonList("application/json"));
        StringBuilder builder = new StringBuilder("{");
        map.forEach((k, v) -> {
            if (v.getClass() == String.class) {
                builder.append("\"")
                        .append(k)
                        .append("\":\"")
                        .append(v)
                        .append("\",");
            } else {
                builder.append("\"")
                        .append(k)
                        .append("\":")
                        .append(v)
                        .append(",");
            }
        });
        builder.deleteCharAt(builder.toString().length()-1);
        builder.append("}");
        exchange.sendResponseHeaders(200, builder.length());
        OutputStream stream = exchange.getResponseBody();
        stream.write(builder.toString().getBytes());
        stream.close();
    }
    public <T> void sendJSON(JSON<T> json) throws IOException {
        exchange.getResponseHeaders().put("Content-Type", Collections.singletonList("application/json"));
        exchange.sendResponseHeaders(200, json.toString().length());
        OutputStream stream = exchange.getResponseBody();
        stream.write(json.toString().getBytes());
        stream.close();
    }
}