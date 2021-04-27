package zc.wx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import zc.wx.bean.ConstantEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置
 * @author zm
 * @date 2019/5/31 14:14
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("中策维修流程")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("zc.wx"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalOperationParameters(getGlobalOperationParameters());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API管理")
                .termsOfServiceUrl("")
                .contact(new Contact("zm","http://www.zxmlxy.com","943335295@qq.com"))
                .version("v1.0")
                .build();
    }

    /**
     * 生成全局通用参数, 支持配置多个响应参数
     * @return
     */
    private List<Parameter> getGlobalOperationParameters() {
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        // header query cookie
        parameterBuilder.name(ConstantEnum.SECRET_KEY.getKey()).description(ConstantEnum.SECRET_KEY.getKey()).modelRef(new ModelRef("string")).parameterType("header").required(true);
        pars.add(parameterBuilder.build());
        return pars;
    }


}