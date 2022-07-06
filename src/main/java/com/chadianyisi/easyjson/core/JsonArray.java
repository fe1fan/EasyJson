package com.chadianyisi.easyjson.core;

import java.util.ArrayList;
import java.util.List;

public class JsonArray {
    private List<Object> list = new ArrayList();

    public void add(Object obj) {
        list.add(obj);
    }

    public Object get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }
}
