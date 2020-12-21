package com.carrot.jvm;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

/**
 * java8新特性 时间测试
 * @Author: Created by carrot
 * 2020/11/19 9:22
 */
@Slf4j
public class LocalDateTest {

    public static void main(String[] args) {
        // 获取当前年月日
        LocalDate now = LocalDate.now();
        log.info("【当前日期:{}】",now);
        // 构造指定的年月日
        LocalDate of = LocalDate.of(2020, 12, 13);
        log.info("【构造的日期:{}】",of);
        // 获取年，月，日，星期几
        int year = now.getYear();
        Month month = now.getMonth();
        int i = now.get(ChronoField.MONTH_OF_YEAR);
        now.getDayOfMonth();
        int j = now.get(ChronoField.DAY_OF_MONTH);
        int m = now.get(ChronoField.DAY_OF_WEEK);
        log.info("【当前时间是{}年{}月{}日，星期{}】",year,month.getValue(),j,m);
        // 获取时间
        // 获取时间和日期
        // 获取秒数
        // 获取毫秒数
        // 日期时间计算
        // 日期时间格式化

    }
}
