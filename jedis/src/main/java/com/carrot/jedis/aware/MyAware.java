package com.carrot.jedis.aware;

import org.springframework.beans.factory.Aware;

/**
 * @Author: Created by carrot
 * 2020/12/19 12:41
 */
public interface MyAware extends Aware {

    void setFlag(Flag flag);
}
