package com.winning.devops.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author chensj
 * @title Swagger2配置类
 * 它可以轻松的整合到Spring Boot中，并与Spring MVC程序配合组织出强大RESTful API文档。
 * 它既可以减少我们创建文档的工作量，同时说明内容又整合入实现代码中，
 * 让维护文档和修改代码整合为一体，可以让我们在修改代码逻辑的同时方便的修改文档说明
 * @{code @ApiOperation}注解来给API增加说明、
 * @{code @ApiImplicitParams}、@{code @ApiImplicitParam}注解来给参数增加说明
 * 访问路径：http://host:port/project/swagger-ui.html
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot
 * @date: 2019-04-20 11:56
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.winning.mbk"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("模板知识库 RESTful APIs")
                .description("模板知识库后台api接口文档")
                .version("1.0")
                .build();
    }
}
