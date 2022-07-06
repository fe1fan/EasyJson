package com.chadianyisi.easyjson.parser;

public class JsonToken {
    private final JsonTokenEnums enums;
    private final String value;


    public JsonToken(JsonTokenEnums enums, String value) {
        this.enums = enums;
        this.value = value;
    }

    public JsonTokenEnums getEnums() {
        return enums;
    }

    public String getValue() {
        return value;
    }
}
