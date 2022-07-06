package com.chadianyisi.easyjson;

import com.chadianyisi.easyjson.core.Json;
import com.chadianyisi.easyjson.core.JsonObject;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        JsonObject jsonObject = Json.parseJsonObject("{\"key\": 1, \"value\": 1, \"obj\":{\"key\": 1, \"successful\": false, \"value\": 1}}");
//        JsonObject jsonObject = Json.parseJsonObject("{\"key\": 1, \"value\": 1");
        Set<String> keys = jsonObject.getKeys();
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println("------");
        Object obj = jsonObject.get("key");
        System.out.println(obj);
        Object obj1 = jsonObject.get("value");
        Object o = jsonObject.getJsonObject("obj").get("successful");
        System.out.println(obj1);
        System.out.println(o);
    }
}