package com.chadianyisi.easyjson.core;

import com.chadianyisi.easyjson.parser.JsonParser;
import com.chadianyisi.easyjson.parser.JsonToken;

import java.util.List;

public class Json {

    private static final ThreadLocal<List<JsonToken>> LIST_THREAD_LOCAL = new ThreadLocal<>();

    public static JsonObject parseJsonObject(String jsonStr, Integer from) {
        if (jsonStr == null || jsonStr.isBlank()) {
            return new JsonObject();
        }
        List<JsonToken> jsonTokenList = new JsonParser().parser(jsonStr);
//        if (from == 0) {
//            jsonTokenList
//            LIST_THREAD_LOCAL.set(jsonTokenList);
//        } else {
//            jsonTokenList = LIST_THREAD_LOCAL.get();
//        }
        if (jsonTokenList == null || jsonTokenList.isEmpty()) {
            return new JsonObject();
        }
        String key = null;
        Object val = null;
        JsonObject jsonObject = new JsonObject();
        for (int i = from; i < jsonTokenList.size(); i++) {
            JsonToken jsonToken = jsonTokenList.get(i);
            switch (jsonToken.getEnums()) {
                case BEGIN_OBJECT:
                    if (key != null) {
                        JsonObject childObj = parseJsonObject(jsonStr, i + 1);
                        i = childObj.i;
                        jsonObject.put(key, childObj);
                    }
                    break;
                case END_OBJECT:
                case SEP_COLON:
                case SEP_COMMA:
                    break;
                case BEGIN_ARRAY:
                    if (key != null) {
                        JsonArray jsonArray = parseJsonArray(jsonStr, i + 1);
                        i = jsonArray.i;
                        jsonObject.put(key, jsonArray);
                    }
                    break;
                case END_ARRAY:
                    break;
                case NUMBER:
                case BOOLEAN:
                case NULL:
                case STRING:
                    if (key == null) {
                        key = jsonToken.getValue();
                    } else {
                        val = jsonToken.getValue();
                    }
            }
            if (key != null && val != null) {
                jsonObject.put(key, val);
                key = null;
                val = null;
            }
            jsonObject.i = i;
        }
        return jsonObject;
    }

    public static JsonObject parseJsonObject(String jsonStr) {
        JsonObject jsonObject = parseJsonObject(jsonStr, 0);
        LIST_THREAD_LOCAL.remove();
        return jsonObject;
    }


    public static JsonArray parseJsonArray(String jsonStr, Integer from) {
        JsonArray jsonArray = new JsonArray();
        int i = from;
        System.out.println(jsonStr.length());
        List<JsonToken> jsonTokenList = new JsonParser().parser(jsonStr);
        while (i < jsonTokenList.size()) {
            JsonObject jsonObject = parseJsonObject(jsonStr, i);
            jsonArray.add(jsonObject);
            i = jsonObject.i + 1;
            jsonArray.i = i;
        }
        return jsonArray;
    }

    public static JsonArray parseJsonArray(String jsonStr) {
        return parseJsonArray(jsonStr, 0);
    }

}
