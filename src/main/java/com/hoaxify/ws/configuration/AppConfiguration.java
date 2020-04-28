package com.hoaxify.ws.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "hoaxify")
public class AppConfiguration {
    private String uploadPath;
}
