package com.chadianyisi.easyjson.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UnknownFormatConversionException;

public class JsonParser {

    public List<JsonToken> jsonTokenList = new ArrayList<>();

    private char[] chars;

    private Integer index = 0;


    public List<JsonToken> parser(String jsonStr) {
        if (jsonStr == null || jsonStr.isBlank()) {
            return Collections.emptyList();
        }
        this.chars = jsonStr.toCharArray();
        for (; index < chars.length; index++) {
            char ch = chars[index];
            if (ch == ' ') {
                continue;
            }
            switch (ch) {
                case '{':
                    jsonTokenList.add(new JsonToken(JsonTokenEnums.BEGIN_OBJECT, String.valueOf(ch)));
                    break;
                case '}':
                    jsonTokenList.add(new JsonToken(JsonTokenEnums.END_OBJECT, String.valueOf(ch)));
                    break;
                case '[':
                    jsonTokenList.add(new JsonToken(JsonTokenEnums.BEGIN_ARRAY, String.valueOf(ch)));
                    break;
                case ']':
                    jsonTokenList.add(new JsonToken(JsonTokenEnums.END_ARRAY, String.valueOf(ch)));
                    break;
                case ',':
                    jsonTokenList.add(new JsonToken(JsonTokenEnums.SEP_COMMA, String.valueOf(ch)));
                    break;
                case ':':
                    jsonTokenList.add(new JsonToken(JsonTokenEnums.SEP_COLON, String.valueOf(ch)));
                    break;
                case 'n':
                    jsonTokenList.add(this.readToken("ull", JsonTokenEnums.NULL));
                    break;
                case 't':
                    jsonTokenList.add(this.readToken("rue", JsonTokenEnums.BOOLEAN));
                    break;
                case 'f':
                    jsonTokenList.add(this.readToken("alse", JsonTokenEnums.BOOLEAN));
                    break;
                case '"':
                    //read string
                    jsonTokenList.add(readString());
                    break;
                case '-':
                    //read number
                    jsonTokenList.add(readNumber());
                    break;
                default: {
                    //read number
                    if (isDigital(ch)) {
                        jsonTokenList.add(readNumber());
                    } else {
                        throw new UnknownFormatConversionException("easyjson can't find this token: " + ch);
                    }
                }

            }
        }
        return jsonTokenList;
    }

    private boolean isDigital(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private JsonToken readToken(String str, JsonTokenEnums enums) {
        char start = chars[index];
        for (char c : str.toCharArray()) {
            if (c == chars[index + 1]) index += 1;
            else return null;
        }
        return new JsonToken(enums, start + str);
    }

    private JsonToken readString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (; index < chars.length; index++) {
            if (chars[index + 1] == '"') {
                index++;
                break;
            }
            stringBuilder.append(chars[index + 1]);
        }
        return new JsonToken(JsonTokenEnums.STRING, stringBuilder.toString());
    }

    private JsonToken readNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        for (; index < chars.length; index++) {
            if (!isDigital(chars[index])) {
                index--;
                break;
            }
            stringBuilder.append(chars[index]);
        }
        return new JsonToken(JsonTokenEnums.NUMBER, stringBuilder.toString());
    }
}
