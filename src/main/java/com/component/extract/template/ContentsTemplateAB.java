package com.component.extract.template;

import com.component.extract.enm.SqlXmlEnum;

import java.io.BufferedReader;
import java.sql.Clob;

public abstract class ContentsTemplateAB<T> implements ContentsTemplateIF<T> {

    public abstract String getContents(T t);

    protected String clobToStr(Clob clob){
        StringBuffer buffer = new StringBuffer();
        try(BufferedReader br = new BufferedReader((clob).getCharacterStream())){
            String dummy;
            while((dummy = br.readLine()) != null){
                buffer.append(dummy);
                buffer.append(SqlXmlEnum.ENTER.getValue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return buffer.toString();
    }
}
