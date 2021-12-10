package com.component.extract;

import com.component.comn.CommonDAO;
import com.component.extract.template.ContentsTemplateIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExtractService {
    @Autowired
    private CommonDAO dao;

    private String[] modules;
    private String sqlId;
    private String rootPath;
    private String fileType;
    private Map<String, String> additionalParam;
    private ContentsTemplateIF template;

    public void setModules(String modules){
        this.modules = modules.split(",");
    }

    public void setSqlId(String sqlId){
        this.sqlId = sqlId;
    }

    public void setRootPath(String rootPath){
        this.rootPath = rootPath;
    }

    public void setFileType(String fileType){
        this.fileType = fileType;
    }

    public void setTemplate(ContentsTemplateIF template){
        this.template = template;
    }

    public void setAdditionalParam(Map<String, String> additionalParam){
        this.additionalParam = additionalParam;
    }

    public void createContents(){
        Map<String, Object> paramMap = new HashMap<>();
        List<Map<String, Object>> list;
        for(String module : modules)
        {
            setParamMap(paramMap, module);
            list = dao.queryForList(sqlId, paramMap);
            setFolder(module);
            createModuleFiles(list, module);
        }
    }

    private void setParamMap(Map<String, Object> paramMap, String module){
        paramMap.put("MODULE_NAME", module);
        if(additionalParam != null){
            paramMap.putAll(additionalParam);
        }
    }

    private void setFolder(String moduleName){
        File d = new File(rootPath + moduleName);
        if(!d.exists()){
            d.mkdir();
        }
    }

    private void createModuleFiles(List<Map<String, Object>> list, String moduleName){
        for(Map<String, Object> sql : list)
        {
            createFile(sql, moduleName);
        }
    }

    private void createFile(Map<String, Object> paramMap, String moduleName){
        String content = template.getContents(paramMap);
        File f = new File(rootPath + moduleName + File.separator + paramMap.get("SQL_ID") + fileType);
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f.getPath()), "UTF-8"))){
            f.createNewFile();
            bw.write(content);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
