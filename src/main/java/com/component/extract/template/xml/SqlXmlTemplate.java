package com.component.extract.template.xml;

import com.component.extract.enm.SqlXmlEnum;
import com.component.extract.template.ContentsTemplateAB;

import java.sql.Clob;
import java.util.Map;

public class SqlXmlTemplate extends ContentsTemplateAB<Map<String, Object>> {
    private final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
    private final String DOCTYPE_DEFINITION = "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n";
    private final String OPEN_MAPPER = "<mapper namespace=\"sql-mapper\">\n";
    private final String CLOSE_MAPPER ="\n</mapper>";

    @Override
    public String getContents(Map<String, Object> paramMap){
        StringBuilder sb = new StringBuilder();
        setId(sb, paramMap);
        setResultType(sb, paramMap);
        setSqlQuery(sb, paramMap);
        setTagType(sb, paramMap);
        return getXml(sb.toString());
    }

    /** Format
     * ==> id="#{SQL_ID}"
     * */
    private void setId(StringBuilder stringBuilder, Map<String, Object> paramMap){
        stringBuilder.append(SqlXmlEnum.ID.getValue());
        stringBuilder.append(SqlXmlEnum.EQUAL.getValue());
        stringBuilder.append(coverWithDoubleQuotation(paramMap, "SQL_ID"));
    }

    /** Format
     * ==> resultType="#{RESULT_TYPE}"
     * */
    private void setResultType(StringBuilder stringBuilder, Map<String, Object> paramMap){
        if(paramMap.get("RESULT_TYPE") != null && "select".equals(paramMap.get("SQL_TYPE"))){
            stringBuilder.append(SqlXmlEnum.EMPTY_STR.getValue());
            stringBuilder.append(SqlXmlEnum.RESULT_TYPE.getValue());
            stringBuilder.append(SqlXmlEnum.EQUAL.getValue());
            stringBuilder.append(coverWithDoubleQuotation(paramMap, "RESULT_TYPE"));
        }
        stringBuilder.append(SqlXmlEnum.CLOSE_TAG.getValue());
    }

    /** Format
     * ==> > #{SQL_TXT}
     * */
    private void setSqlQuery(StringBuilder stringBuilder, Map<String, Object> parmMap){
        Object sql = parmMap.get("SQL_TXT");
        String content;
        if(sql instanceof Clob){
            content = clobToStr((Clob)sql);
        }else{
            content = sql.toString();
        }
        stringBuilder.append(coverWithEnter(content));
    }

    private void setTagType(StringBuilder stringBuilder, Map<String, Object> parmMap){
        String sqlType = String.valueOf(parmMap.get("SQL_TYPE"));
        stringBuilder.insert(0, SqlXmlEnum.OPEN_TAG.getValue() + sqlType + SqlXmlEnum.EMPTY_STR.getValue());
        stringBuilder.append(SqlXmlEnum.OPEN_TAG.getValue() + SqlXmlEnum.SLASH.getValue() + sqlType + SqlXmlEnum.CLOSE_TAG.getValue());
    }

    private String getXml(String contents){
        return XML_HEADER + DOCTYPE_DEFINITION + OPEN_MAPPER + contents + CLOSE_MAPPER;
    }

    /** Common Overloading Method
     * */
    private String coverWithDoubleQuotation(Map<String, Object> paramMap, String key){
        return SqlXmlEnum.DOUBLE_QUOTATION.getValue() + paramMap.get(key) + SqlXmlEnum.DOUBLE_QUOTATION.getValue();
    }

    /** Common Overloading Method
     * */
    private String coverWithEnter(String value){
        return SqlXmlEnum.ENTER.getValue() + value + SqlXmlEnum.ENTER.getValue();
    }
}
