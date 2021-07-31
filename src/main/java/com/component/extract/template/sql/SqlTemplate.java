package com.component.extract.template.sql;

import com.component.extract.template.ContentsTemplateAB;

import java.sql.Clob;
import java.util.Map;

public class SqlTemplate extends ContentsTemplateAB<Map<String, Object>> {
    @Override
    public String getContents(Map<String, Object> parmMap) {
        Object sql = parmMap.get("SQL_TXT");
        String content;
        if(sql instanceof Clob){
            content = clobToStr((Clob)sql);
        }else{
            content = sql.toString();
        }
        return content + ";";
    }
}
