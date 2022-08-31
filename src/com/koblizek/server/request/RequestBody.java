package com.koblizek.server.request;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koblizek.server.util.RESTServerException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

public class RequestBody {
    private final InputStream stream;
    public RequestBody(InputStream stream) {
        this.stream = stream;
    }

    public JsonElement get(String name) throws IOException {
        String json = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        JsonObject object = new Gson().fromJson(new StringReader(json), JsonObject.class);
        return object.get(name);
    }
    @Override
    public String toString() {
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RESTServerException("IO err");
        }
    }
}
