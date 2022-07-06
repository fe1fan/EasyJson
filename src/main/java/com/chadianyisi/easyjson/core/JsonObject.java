package com.chadianyisi.easyjson.core;

import java.util.HashMap;
import java.util.Set;

public class JsonObject extends HashMap<String, Object> {
    public Set<String> getKeys() {
        return super.keySet();
    }
}
