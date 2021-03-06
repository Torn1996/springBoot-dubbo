package com.cgt.springboot.ServiceImpl;

import Service.IHello;
import org.springframework.stereotype.Service;

/**
 * @Author: cgt
 * @Date: 2019/5/30 22:37
 * @Version 1.0
 */
@Service    //声明组件
@com.alibaba.dubbo.config.annotation.Service //暴露服务
public class IHelloController implements IHello {
    @Override
    public String say(String s) {
        System.out.println("你好：" + s);
        return s;
    }
}
