package com.chadianyisi.easyjson.core;

import java.util.HashMap;
import java.util.Set;

public class JsonObject extends HashMap<String, Object> {

    public int i;
    public Set<String> getKeys() {
        return super.keySet();
    }

    public JsonObject getJsonObject(String key) {
        return (JsonObject) super.get(key);
    }
}
