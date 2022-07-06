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
        for (int i = from; i < jsonTokenList.size(); i++) {
            JsonToken jsonToken = jsonTokenList.get(i);
            switch (jsonToken.getEnums()) {
                case BEGIN_OBJECT:
                    if (key != null){
                        JsonObject childObj = parseJsonObject(jsonStr, i + 1);
                        i = childObj.i;
                        jsonObject.put(key, childObj);
                    }
                    break;
                case END_OBJECT:
                case SEP_COLON:
                case SEP_COMMA:
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
        return parseJsonObject(jsonStr, 0);
    }
}
