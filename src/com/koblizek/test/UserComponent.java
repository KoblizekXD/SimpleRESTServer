package com.koblizek.test;

import com.koblizek.server.request.Request;
import com.koblizek.server.util.Component;
import com.koblizek.server.util.Table;
import com.koblizek.server.util.annotations.Get;
import com.koblizek.server.util.annotations.table.TableComponent;

import java.io.IOException;
import java.util.List;

public class UserComponent implements Component, Table {
    @TableComponent
    public String id = "Hello";
    @Get("/users")
    public void getUsers(Request request) throws IOException {
        request.send(List.of(
                "John",
                "Lisa",
                "Peter"
        ).toString());
    }
}