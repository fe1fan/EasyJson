package com.chadianyisi.easyjson.parser;

public enum JsonTokenEnums {
    BEGIN_OBJECT(),
    END_OBJECT(),

    BEGIN_ARRAY(),
    END_ARRAY(),

    NULL(),
    NUMBER(),
    STRING(),
    BOOLEAN(),

    SEP_COLON(),
    SEP_COMMA(),

    ;
}
