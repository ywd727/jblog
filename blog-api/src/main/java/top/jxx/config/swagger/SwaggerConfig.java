package top.jxx.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: top.naccl.config.swagger
 * @ClassName: SwaggerConfig
 * @Author: 简鑫鑫
 * @Description:
 * @Date: 2022/8/30 10:29
 * @Version: 1.0
 */
//启动swagger的注解
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    /**
     * 配置的全局token和swagger的相关属性的填写
     * @return
     */
    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.jxx"))//扫描那些包，为这些包创建相关的测试接口
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("blog-interface")
                        .description("创建这个swagger的目的是用来测试后端接口")
                        .version("1.0")
                        .contact(new Contact("jxx","blog.csdn.net","1134334153@qq.com"))
                        .license("The Apache License")
                        .licenseUrl("博客地址")
                        .build())
                //配置安全上下文
                .securityContexts(Arrays.asList(securityContexts()))
                .securitySchemes(unifiedAuth());
    }

    private static List<ApiKey> unifiedAuth() {
        List<ApiKey> arrayList = new ArrayList<>();
        arrayList.add(new ApiKey("token", "token", "header"));
        return arrayList;
    }

    private SecurityContext securityContexts() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "全局token");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("token", authorizationScopes));
    }
}
