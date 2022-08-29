package com.koblizek.server.util.json;

public class JSON<T> {
    private final StringBuilder stringBuilder;
    public JSON(String string) {
        stringBuilder = new StringBuilder(string);
    }
    public JSON() {
        stringBuilder = new StringBuilder("{");
    }
    public JSON<T> put(String name, T value) {
        if (value.getClass() == String.class) {
            stringBuilder.append(
                    "\""+name+"\":"
                    +"\""+value.toString()+"\","
                    );
        }
        return this;
    }
    @Override
    public String toString() {
        stringBuilder.deleteCharAt(stringBuilder.toString().length()-1);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
