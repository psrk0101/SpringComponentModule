package com.component.comn;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CommonDAO {

    @Autowired
    public SqlSessionTemplate sqlSessionTemplate;

    public int delete(String sqlId)
    {
        return sqlSessionTemplate.delete(sqlId);
    }

    public int delete(String sqlId,String key)
    {
        return sqlSessionTemplate.delete(sqlId, key);
    }

    public int delete(String sqlId, Map rowMap)
    {
        return sqlSessionTemplate.delete(sqlId, rowMap);
    }

    public int delete(String sqlId, List<Map> list)
    {
        int retVal = 0;

        for(Map rowMap : list)
        {
            retVal += sqlSessionTemplate.delete(sqlId, rowMap);
        }
        return retVal;
    }

    public int insert(String sqlId)
    {
        return sqlSessionTemplate.insert(sqlId);
    }

    public int insert(String sqlId, Map rowMap)
    {
        return sqlSessionTemplate.insert(sqlId, rowMap);
    }

    public int insert(String sqlId, List<Map> list)
    {
        int retVal = 0;

        for(Map rowMap : list)
        {
            retVal += sqlSessionTemplate.insert(sqlId, rowMap);
        }
        return retVal;
    }

    public int update(String sqlId)
    {
        return sqlSessionTemplate.update(sqlId);
    }

    public int update(String sqlId, Map rowMap)
    {
        return sqlSessionTemplate.update(sqlId, rowMap);
    }

    public int update(String sqlId, List<Map> list)
    {
        int retVal = 0;

        for(Map rowMap : list)
        {
            retVal += sqlSessionTemplate.update(sqlId, rowMap);
        }
        return retVal;
    }

    public final int queryForInt(String sqlId)
    {
        Integer intVal = (Integer) sqlSessionTemplate.selectOne(sqlId);
        return intVal.intValue();
    }

    public final int queryForInt(String sqlId, Map rowMap)
    {
        Integer intVal = (Integer) sqlSessionTemplate.selectOne(sqlId, rowMap);
        return intVal.intValue();
    }

    public final int queryForInt(String sqlId, String key)
    {
        Integer intVal = (Integer) sqlSessionTemplate.selectOne(sqlId, key);
        return intVal.intValue();
    }

    public final double queryForDouble(String sqlId)
    {
        Double doubleVal = Double.parseDouble(sqlSessionTemplate.selectOne(sqlId)+"");
        return doubleVal.doubleValue();
    }

    public final double queryForDouble(String sqlId, Map rowMap)
    {
        Double doubleVal = Double.parseDouble(sqlSessionTemplate.selectOne(sqlId, rowMap)+"");
        return doubleVal.doubleValue();
    }

    public final double queryForDouble(String sqlId, String key)
    {
        Double doubleVal = Double.parseDouble(sqlSessionTemplate.selectOne(sqlId, key)+"");
        return doubleVal.doubleValue();
    }

    public final Map queryForMap(String sqlId, Map rowMap)
    {
        return (Map) sqlSessionTemplate.selectOne(sqlId, rowMap);
    }

    public final Map queryForMap(String sqlId, Map rowMap, String keyValue)
    {
        return (Map) sqlSessionTemplate.selectMap(sqlId, rowMap, keyValue);
    }

    public final List queryForList(String sqlId, Map rowMap)
    {
        return sqlSessionTemplate.selectList(sqlId, rowMap);
    }

    public final List queryForList(String sqlId, Object paramObj)
    {
        return sqlSessionTemplate.selectList(sqlId, paramObj);
    }

    public final List queryForList(String sqlId)
    {
        return sqlSessionTemplate.selectList(sqlId);
    }

    public final String queryForStr(String sqlId, Map rowMap)
    {
        return (String) sqlSessionTemplate.selectOne(sqlId, rowMap);
    }

    public final String queryForStr(String sqlId)
    {
        return (String) sqlSessionTemplate.selectOne(sqlId);
    }
}

