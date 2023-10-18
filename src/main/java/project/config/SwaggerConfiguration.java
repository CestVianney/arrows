package project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfiguration {
    private static final String PATH_API = "/arrows.*";
    private static final String API_READ_ONLY = "api-readonly";

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.OAS_30)
                .groupName(API_READ_ONLY)
                .select()
                .apis(RequestHandlerSelectors.basePackage("project.adapters.api"))
                .paths(paths())
                .build();
    }

    private Predicate<String> paths() {
        return regex(PATH_API);
    }
}
