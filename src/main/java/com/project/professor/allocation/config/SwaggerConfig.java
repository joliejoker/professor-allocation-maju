package com.project.professor.allocation.config;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/*")
                .group("")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
					.info(
						new Info()
							.title("Allocation's System")
							.description("Allocation's System Rest Server")
							.version("0.0.2-SNAPSHOT")
					);
  }
	
}
