package com.chadianyisi.easyjson.core;

import java.util.ArrayList;
import java.util.List;

public class JsonArray {

    public int i;

    private List<JsonObject> list = new ArrayList();

    public void add(JsonObject obj) {
        list.add(obj);
    }

    public JsonObject get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }
}
