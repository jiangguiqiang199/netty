package com.carrot.jedis;

import com.carrot.jedis.dao.LockTableMapper;
import com.carrot.jedis.model.LockTable;
import com.carrot.jedis.service.MybatisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * ==========================一级缓存==========================================================
 * mybatis是怎么样判断两次查询是相同查询的
 * 命中原则:
 * 1、statementId <select id="selectByPrimaryKey"
 * 2、传递给SQL语句的查询参数
 * 3、分页参数 rowBounds
 * 4、sql语句(不考虑SQL语义)
 * 5、环境environment
 *
 * mybatis的一级缓存是什么时候产生的，又是什么时候销毁的
 * 生命周期:
 * 缓存的产生: sqlSession调用select方法时产生缓存
 * 缓存的销毁:
 *   1、sqlSession.close()
 *   2、commit提交 sqlSession.commit()
 *   3、sqlSession.rollback()
 *   4、在udate,delete,insert之后
 *   5、主动清除，sqlSession.clearCache()
 *
 * mybatis一级缓存会产生脏读吗？
 * 答: mybatis的一级缓存是在数据库事务的生命周期之内的。脱离数据库事务谈脏读，是没有意义的。
 *
 * 名词解释
 * 脏读(读未提交):
 *     事务1读取了事务2已经修改，但尚未提交的数据，如果事务2发生了回滚，则事务1读取的数据就变成了错误数据，也成为脏数据
 * 不可重复读:
 *     在事务1运行期间，事务2更新了数据。导致事务1两次查询结果不一致
 * 结论: myBatis一级缓存反而提升了事务隔离级别(避免了脏读和不可重复读)
 *
 *
 * 设计理念:
 * 1、在一个session内 不过期
 * 2、在一个session内 不更新(直接清除)
 * 3、在一个session内 不限制(不限制数量，有可能撑爆内存)
 * 4、一般情况下，session的生存时间很短，执行update会销毁缓存，并且支持主动销毁缓存
 * 5、大原则：要控制session的生存时间
 *
 * 与spring集成:
 * 1、未开启事务: 每次请求，spring都会关闭旧的session再创建新的session，所以此时一级缓存无效
 * 2、开启事务: 在一个事务内，Spring通过ThreadLocal始终使用同一个session，所以此时一级缓存在事务内有效
 * ==========================二级级缓存==========================================================
 * 二级缓存是跨session生效的
 * 如何开启:
 * application.properties: mybatis.configuration.cache-enabled=true
 * mybatis-config.xml
 * <settings>
 *     <setting name="cacheEnabled" value="true"></setting>
 * </settings>
 * <mapper namespace="...">
 *    <cache/>
 * </mapper>
 * Entity需要序列化
 *
 * 命中原则:
 * 1、statementId <select id="selectByPrimaryKey"
 * 2、传递给SQL语句的查询参数
 * 3、分页参数 rowBounds
 * 4、sql语句(不考虑SQL语义)
 * 5、环境environment
 *
 * mybatis的二级缓存是什么时候产生的，又是什么时候销毁的
 * 生命周期:
 * 缓存的产生: sqlSession调用select方法时产生缓存
 * 缓存的销毁:
 *   1、sqlSession.close()
 *   2、commit提交 sqlSession.commit()
 *   3、sqlSession.rollback()
 *   4、在udate,delete,insert之后
 *   5、主动清除，sqlSession.clearCache()
 * @Author: Created by carrot
 * 2020/12/20 12:13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisCacheTest {

    @Autowired
    private MybatisService mybatisService;

    @Test
    public void testInsert(){
        mybatisService.insertObject();
    }

    // 一级缓存(sqlSession级别的缓存,默认开启)试验
    @Test
//    @Transactional
    public void testCacheLevel1(){
        mybatisService.selectObject();
    }

    // 二级缓存试验(sqlSessionFactory级别的缓存)试验
    @Test
    public void testCacheLevel2(){

    }

    // 自定义缓存试验
    @Test
    public void testCacheLevelCustomize(){

    }
}
