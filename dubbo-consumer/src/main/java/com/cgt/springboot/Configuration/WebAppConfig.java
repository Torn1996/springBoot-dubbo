package com.cgt.springboot.Configuration;

import com.cgt.springboot.Interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: cgt
 * @Date: 2019/5/31 23:45
 * @Version 1.0
 */
@Configuration
/**
 * WebMvcConfigurerAdapter被deprecated
 */
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
        //super.addInterceptors(registry);
    }
}
