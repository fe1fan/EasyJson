package com.chadianyisi.easyjson;

import com.chadianyisi.easyjson.core.Json;
import com.chadianyisi.easyjson.core.JsonArray;
import com.chadianyisi.easyjson.core.JsonObject;

public class Main {
    public static void main(String[] args) {
//        JsonObject jsonObject = Json.parseJsonObject("{\"key\": 1, \"value\": 1, \"obj\":{\"key\": 1, \"successful\": false, \"value\": 1}}");
        JsonArray jsonArray = Json.parseJsonArray("[{\"key\": 1, \"value\": 1}, {\"key\": 1, \"value\": 1}]");
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject o = jsonArray.get(i);
            System.out.println(o.getKeys());
        }
    }
}