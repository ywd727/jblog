package top.naccl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ProjectName: blog-api
 * @Package: top.naccl.config
 * @ClassName: MyWebAppConfigurer
 * @Author: 简鑫鑫
 * @Description:
 * @Date: 2022/8/25 14:05
 * @Version: 1.0
 */
//配置虚拟路径，通过虚拟路径指向磁盘路径
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    /*
        /image/upload/** 表示虚拟路径
        file:/D:/image/upload/ 表示磁盘路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/upload/**").addResourceLocations("file:/D:/image/upload/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}

