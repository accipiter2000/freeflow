package com.opendynamic.ff.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class FfJdbcConfig {
    @Bean
    public NamedParameterJdbcTemplate ffNamedParameterJdbcTemplate(@Qualifier("ffJdbcTemplate") JdbcTemplate ffJdbcTemplate) {
        return new NamedParameterJdbcTemplate(ffJdbcTemplate);
    }
}