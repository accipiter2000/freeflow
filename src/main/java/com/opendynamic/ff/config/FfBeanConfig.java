package com.opendynamic.ff.config;

import javax.el.ExpressionFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.opendynamic.ff.vo.DiamondShape;
import com.opendynamic.ff.vo.LineShape;
import com.opendynamic.ff.vo.OvalShape;
import com.opendynamic.ff.vo.RectangleShape;
import com.opendynamic.ff.vo.Shape;

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

    @Bean
    public Gson ffGson() {
        return new GsonBuilder().create();
    }

    @Bean
    public Gson ffProcDefGson() {
        RuntimeTypeAdapterFactory<Shape> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory.of(Shape.class, "type");
        runtimeTypeAdapterFactory.registerSubtype(RectangleShape.class, "rectangle");
        runtimeTypeAdapterFactory.registerSubtype(OvalShape.class, "oval");
        runtimeTypeAdapterFactory.registerSubtype(DiamondShape.class, "diamond");
        runtimeTypeAdapterFactory.registerSubtype(LineShape.class, "line");

        return new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
    }
}