package com.cgt.springboot.Controller;

import Service.IHello;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cgt
 * @Date: 2019/5/30 22:59
 * @Version 1.0
 */
@RestController
public class IHelloController {
    @Reference(mock = "true",timeout = 1000)  //引用注册中心
    IHello hello;
    @RequestMapping("/hello")
    public String hello(@RequestParam("msg")String msg){
        return hello.say(msg);
    }
}
