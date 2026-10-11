package com.opendynamic.ff.config;

import javax.el.ExpressionFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import de.odysseus.el.ExpressionFactoryImpl;

@Configuration
public class FfBeanConfig {
    @Bean
    public NamedParameterJdbcTemplate ffNamedParameterJdbcTemplate(@Qualifier("ffJdbcTemplate") JdbcTemplate ffJdbcTemplate) {
        return new NamedParameterJdbcTemplate(ffJdbcTemplate);
    }

    @Bean
    public ExpressionFactory ffExpressionFactory() {
        return new ExpressionFactoryImpl();
    }
}