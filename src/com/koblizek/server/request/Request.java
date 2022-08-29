package com.koblizek.server.request;

import com.koblizek.server.util.Table;
import com.koblizek.server.util.annotations.table.TableComponent;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Collections;

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
                        builder.append("\""+field.getName()+"\":\""+field.get(tableClass)+"\"");
                    } else {
                        builder.append("\""+field.getName()+"\":\""+field.get(tableClass)+"\",");
                    }
                } else {
                    if (i+1 == tableClass.getClass().getFields().length) {
                        builder.append("\""+field.getName()+"\":"+field.get(tableClass)+"");
                    } else {
                        builder.append("\""+field.getName()+"\":"+field.get(tableClass)+",");
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
}