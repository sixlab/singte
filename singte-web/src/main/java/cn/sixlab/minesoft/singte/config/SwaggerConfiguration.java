package cn.sixlab.minesoft.singte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("Singte")
                .select()
                //
                .apis(RequestHandlerSelectors.basePackage("cn.sixlab.minesoft.singte.core.controller.api"))//指定的API包，可以是controller，可以是专门的对外接口，列如：com.base.zijie.controller,根据实际情况而言
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Singte RESTful APIs")
                .description("Singte RESTful APIs").termsOfServiceUrl("http://minesoft.tech/")
                .version("1.0")
                .build();
    }

}
