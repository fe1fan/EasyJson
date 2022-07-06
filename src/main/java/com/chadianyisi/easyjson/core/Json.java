package com.chadianyisi.easyjson.core;

import com.chadianyisi.easyjson.parser.JsonParser;
import com.chadianyisi.easyjson.parser.JsonToken;

import java.util.List;

public class Json {

    public static JsonObject parseJsonObject(String jsonStr, Integer from) {
        if (jsonStr == null || jsonStr.isBlank()) {
            return new JsonObject();
        }
        List<JsonToken> jsonTokenList = new JsonParser().parser(jsonStr);
        if (jsonTokenList == null || jsonTokenList.isEmpty()) {
            return new JsonObject();
        }
        String key = null;
        Object val = null;
        JsonObject jsonObject = new JsonObject();
        boolean keyTime = true;
        for (int i = from; i < jsonTokenList.size(); i++) {
            JsonToken jsonToken = jsonTokenList.get(i);
            switch (jsonToken.getEnums()) {
                case BEGIN_OBJECT:
                    if (keyTime) {
                        continue;
                    } else {
                        jsonObject.put(key, parseJsonObject(jsonStr, i));
                    }
                    break;
                case END_OBJECT:
                    return jsonObject;
                case SEP_COLON:
                    keyTime = false;
                    break;
                case SEP_COMMA:
                    keyTime = true;
                    break;
                case NUMBER:
                case BOOLEAN:
                case NULL:
                case STRING:
                    if (keyTime) {
                        key = jsonToken.getValue();
                    } else {
                        val = jsonToken.getValue();
                    }
            }
            if (!keyTime) {
                jsonObject.put(key, val);
                key = null;
                val = null;
            }
        }
        return jsonObject;
    }

    public static JsonObject parseJsonObject(String jsonStr) {
        return parseJsonObject(jsonStr, 0);
    }
}
