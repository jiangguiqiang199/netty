package com.carrot.jedis.aware;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: Created by carrot
 * 2020/12/19 12:40
 */
@Component
@Data
public class Flag {

    private boolean isCanOperate = true;
}
