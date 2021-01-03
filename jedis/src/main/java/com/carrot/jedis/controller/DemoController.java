package com.carrot.jedis.controller;

import com.carrot.jedis.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Created by carrot
 * 2020/12/24 16:52
 */
@Controller
public class DemoController {

    @Autowired
    private MybatisService mybatisService;

    /**
     * http://localhost:8080/demo
     * @return
     */
    @ResponseBody
    @GetMapping("/demo")
    public String dome(){
        mybatisService.selectObject();
        return "调用成功";
    }
}
