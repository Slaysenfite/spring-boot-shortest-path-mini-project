package com.slaysenfite;

import com.slaysenfite.config.DatabaseConfig;
import com.slaysenfite.config.SwaggerConfig;
import com.slaysenfite.config.WebServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main application for shortest path web services
 */
@SpringBootApplication
@EnableJpaRepositories
@Import( {DatabaseConfig.class, SwaggerConfig.class, WebServiceConfig.class})
public class ShortestPathServiceAssembler {
    public static void main(String[] args) {
        SpringApplication.run(ShortestPathServiceAssembler.class, args);
    }
}
