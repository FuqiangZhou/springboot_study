package com.zhou.springbootwithshiro.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;


/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-07-06 17:25
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhou.springbootwithshiro"))
                .paths(PathSelectors.regex("^(?!auth).*$"))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("测试接口文档")
                .description("简单的restful风格")
                .contact(new Contact("周富强", "", "zhoufuqiang@outlook.com"))
                .termsOfServiceUrl("http://www.quickercloud.com")
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes(){
        return Lists.newArrayList(new ApiKey("token", "token", "header"));
    }

    private List<SecurityContext> securityContexts() {
        return Lists.newArrayList(SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("^(?!auth).*$")).build());
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("token", authorizationScopes));
    }

}
