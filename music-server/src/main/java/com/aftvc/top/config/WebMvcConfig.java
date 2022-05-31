package com.aftvc.top.config;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ Author     ：Yan
 * @ Date       ：Created in 0:22 2020/7/9
 * @ Description：WebMvcConfig
 * @ Modified By：
 * @Version: $
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //添加默认的静态资源访问路径
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:static/","classpath:META-IFA/resources/","classpath:resources/","classpath:public/","classpath:/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        //  获取与jar同级目录下的upload文件夹  设置与jar同级静态资源配置
        ApplicationHome h = new ApplicationHome(this.getClass());

    }
}
