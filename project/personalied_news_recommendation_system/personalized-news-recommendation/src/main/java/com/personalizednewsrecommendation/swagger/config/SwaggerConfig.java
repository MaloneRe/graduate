package com.personalizednewsrecommendation.swagger.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*重要！如果你的项目引入junit测试，此处需要使用@WebAppConfiguration，如果没有使用junit使用@Configuration(很多的博客都没有注明这个问题，为此我花了非常多的时间解决问题)*/
@WebAppConfiguration
@EnableSwagger2//重要！
@EnableWebMvc
@ComponentScan(basePackages = "com.personalizednewsrecommendation.manager.controller")//扫描control所在的package请修改为你control所在package
public class SwaggerConfig extends WebMvcConfigurerAdapter{
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("个性化新闻推荐系统项目接口文档")
                .description("个性化新闻推荐系统项目接口测试")
                .version("1.0.0")
                .termsOfServiceUrl("")
                .license("self")
                .licenseUrl("/personalized-news-recommendation/error.html")
                .build();
    }
    /**
     * 这个与<mvc:resource map = "" location = "">
     * 等同
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {  
        registry.addResourceHandler("swagger-ui.html")  
                .addResourceLocations("classpath:/META-INF/resources/");  
        registry.addResourceHandler("webjars/**")  
                .addResourceLocations("classpath:/META-INF/resources/webjars/");  
    }  
}
