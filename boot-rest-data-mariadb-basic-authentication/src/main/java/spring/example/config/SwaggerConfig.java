package spring.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TODO support swagger on project
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket productApi() {
        List<SecurityScheme> schemeList = new ArrayList<>();
        schemeList.add(new BasicAuth("basicAuth"));

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("spring.example.rest"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                .securitySchemes(schemeList);
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("\"Spring Boot REST API for Online Store\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(
                        new Contact(
                                "Andrew Berezhnov",
                                "https://www.linkedin.com/in/andrey-berezhnov-15b0b9118/",
                                "andrew.my@yahoo.com"
                        )
                )
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/swagger-resources/");
    }

    @Bean
    public SecurityConfiguration security() {
        return new SecurityConfiguration(
                (String) "test-app-client-id",
                (String) "test-app-client-secret",
                (String) "example.com",
                (String) "test-app",
                (String) ",",
                (Map<String, Object>) null,
                (Boolean) true);
    }
}
