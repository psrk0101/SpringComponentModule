package com.component.comn;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CommonDAO {

    @Autowired
    public SqlSessionTemplate tmsSqlSessionTemplate;

    public int delete(String sqlId)
    {
        return tmsSqlSessionTemplate.delete(sqlId);
    }

    public int delete(String sqlId,String key)
    {
        return tmsSqlSessionTemplate.delete(sqlId, key);
    }

    public int delete(String sqlId, Map rowMap)
    {
        return tmsSqlSessionTemplate.delete(sqlId, rowMap);
    }

    public int delete(String sqlId, List<Map> list)
    {
        int retVal = 0;

        for(Map rowMap : list)
        {
            retVal += tmsSqlSessionTemplate.delete(sqlId, rowMap);
        }
        return retVal;
    }

    public int insert(String sqlId)
    {
        return tmsSqlSessionTemplate.insert(sqlId);
    }

    public int insert(String sqlId, Map rowMap)
    {
        return tmsSqlSessionTemplate.insert(sqlId, rowMap);
    }

    public int insert(String sqlId, List<Map> list)
    {
        int retVal = 0;

        for(Map rowMap : list)
        {
            retVal += tmsSqlSessionTemplate.insert(sqlId, rowMap);
        }
        return retVal;
    }

    public int update(String sqlId)
    {
        return tmsSqlSessionTemplate.update(sqlId);
    }

    public int update(String sqlId, Map rowMap)
    {
        return tmsSqlSessionTemplate.update(sqlId, rowMap);
    }

    public int update(String sqlId, List<Map> list)
    {
        int retVal = 0;

        for(Map rowMap : list)
        {
            retVal += tmsSqlSessionTemplate.update(sqlId, rowMap);
        }
        return retVal;
    }

    public final int queryForInt(String sqlId)
    {
        Integer intVal = (Integer)tmsSqlSessionTemplate.selectOne(sqlId);
        return intVal.intValue();
    }

    public final int queryForInt(String sqlId, Map rowMap)
    {
        Integer intVal = (Integer)tmsSqlSessionTemplate.selectOne(sqlId, rowMap);
        return intVal.intValue();
    }

    public final int queryForInt(String sqlId, String key)
    {
        Integer intVal = (Integer)tmsSqlSessionTemplate.selectOne(sqlId, key);
        return intVal.intValue();
    }

    public final double queryForDouble(String sqlId)
    {
        Double doubleVal = Double.parseDouble(tmsSqlSessionTemplate.selectOne(sqlId)+"");
        return doubleVal.doubleValue();
    }

    public final double queryForDouble(String sqlId, Map rowMap)
    {
        Double doubleVal = Double.parseDouble(tmsSqlSessionTemplate.selectOne(sqlId, rowMap)+"");
        return doubleVal.doubleValue();
    }

    public final double queryForDouble(String sqlId, String key)
    {
        Double doubleVal = Double.parseDouble(tmsSqlSessionTemplate.selectOne(sqlId, key)+"");
        return doubleVal.doubleValue();
    }

    public final Map queryForMap(String sqlId, Map rowMap)
    {
        return (Map)tmsSqlSessionTemplate.selectOne(sqlId, rowMap);
    }

    public final Map queryForMap(String sqlId, Map rowMap, String keyValue)
    {
        return (Map)tmsSqlSessionTemplate.selectMap(sqlId, rowMap, keyValue);
    }

    public final List queryForList(String sqlId, Map rowMap)
    {
        return tmsSqlSessionTemplate.selectList(sqlId, rowMap);
    }

    public final List queryForList(String sqlId, Object paramObj)
    {
        return tmsSqlSessionTemplate.selectList(sqlId, paramObj);
    }

    public final List queryForList(String sqlId)
    {
        return tmsSqlSessionTemplate.selectList(sqlId);
    }

    public final String queryForStr(String sqlId, Map rowMap)
    {
        return (String)tmsSqlSessionTemplate.selectOne(sqlId, rowMap);
    }

    public final String queryForStr(String sqlId)
    {
        return (String)tmsSqlSessionTemplate.selectOne(sqlId);
    }
}

