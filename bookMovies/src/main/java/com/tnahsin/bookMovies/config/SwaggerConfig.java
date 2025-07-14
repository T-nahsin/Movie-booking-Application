package com.tnahsin.bookMovies.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.swing.text.html.HTML;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCostumCofig() {
        return new OpenAPI().info(
                new Info().title("Movie booking App APIs")
                        .description("by Nishant")
        )
                .servers(List.of(new Server()
                        .url("http://localhost:8081").description("local")))
                .tags(Arrays.asList(
                        new Tag().name("Authorization APIs")
                ));
    }
}