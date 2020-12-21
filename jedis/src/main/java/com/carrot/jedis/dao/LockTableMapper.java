package com.carrot.jedis.dao;

import com.carrot.jedis.model.LockTable;

public interface LockTableMapper {
    int deleteByPrimaryKey(String rowKey);

    int insert(LockTable record);

    int insertSelective(LockTable record);

    LockTable selectByPrimaryKey(String rowKey);

    int updateByPrimaryKeySelective(LockTable record);

    int updateByPrimaryKey(LockTable record);
}