package com.carrot.jedis.service;

import com.carrot.jedis.dao.LockTableMapper;
import com.carrot.jedis.model.LockTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: Created by carrot
 * 2020/12/21 16:23
 */
@Slf4j
@Component
public class MybatisService {

    @Resource
    private LockTableMapper lockTableMapper;


    public void selectObject(){
        String key = "12222222";
        // 会打印SQL
        LockTable lockTable01 = lockTableMapper.selectByPrimaryKey(key);
        log.info("【lockTable01:{}】",lockTable01);
        // 不会打印SQL
        LockTable lockTable02 = lockTableMapper.selectByPrimaryKey(key);
        log.info("【lockTable02:{}】",lockTable02);
        // lockTable01 和 lockTable02 内存地址一致
        log.info("【lockTable01与lockTable02是同一个对象吗：{}】",lockTable01 == lockTable02);
    }

    public void insertObject(){
        LockTable lockTable=new LockTable();
//        lockTable.setRowKey("12222222");
        lockTable.setRowKey("12222223");
        lockTable.setBranchId(12343124124143L);
        int count = lockTableMapper.insert(lockTable);
        log.info("【影响行数:{}】",count);
    }
}
