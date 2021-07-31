package com.component.extract.enm;

public enum SqlXmlEnum {
    OPEN_TAG("<"),
    CLOSE_TAG(">"),
    SLASH("/"),
    ID("id"),
    EQUAL("="),
    RESULT_TYPE("resultType"),
    EMPTY_STR(" "),
    DOUBLE_QUOTATION("\""),
    ENTER("\n")
    ;

    private String value;

    SqlXmlEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
