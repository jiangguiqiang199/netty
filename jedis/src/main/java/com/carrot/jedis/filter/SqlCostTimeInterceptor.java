package com.carrot.jedis.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

/**
 * mybatis拦截器试验
 *
 * mybatis默认允许拦截以下四个类的方法
 * Executor 用来执行SQL、
 * ParameterHandler 设置 SQL 中的占位符参数
 * StatementHandler 执行 SQL 语句
 * ResultSetHandler 封装执行结果
 *
 * 责任链的关键要素: 处理器，处理器链
 *
 * mybatis拦截器：Interceptor(处理器)，InterceptorChain(处理器链)
 * Plugin: 用来生成被拦截对象的动态代理
 *
 * @Author: Created by carrot
 * 2020/12/23 17:38
 */
@Slf4j
@Intercepts({
        // 通过以下三个参数，可以明确的定位需要拦截的方法
        // type 表示需要拦截的类
        // method 表示需要拦截的方法名
        // args 表示需要拦截的参数列表
@Signature(type = StatementHandler.class, method ="update" , args = {Statement.class}),
        @Signature(type = StatementHandler.class, method ="query" , args = {Statement.class, ResultHandler.class}),
@Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
}
)
public class SqlCostTimeInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        StatementHandler statementHandler = (StatementHandler) target;
        long startTime = System.currentTimeMillis();
        try {
            return invocation.proceed();
        }finally {
            long costTime = System.currentTimeMillis() - startTime;
            BoundSql boundSql = statementHandler.getBoundSql(); String sql = boundSql.getSql();
            log.info("执行 SQL：[ {} ]执行耗时[ {} ms]", sql, costTime);
        }
    }

    @Override
    public Object plugin(Object target) {
        log.info("被拦截(代理)的目标对象为:{}",target.toString());
        // 返回一个target(Executor,StatementHandler,ResultSetHandler,ParameterHandler)的代理对象
        // 使用当前拦截器，生成目标对象的代理对象
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        log.info("插件(拦截器)配置信息:{}",properties);
    }
}
