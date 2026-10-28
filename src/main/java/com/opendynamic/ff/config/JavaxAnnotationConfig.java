package com.opendynamic.ff.config;

import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ReflectionUtils;

@Configuration
public class JavaxAnnotationConfig {
    /**
     * 条件成立（存在 jakarta.annotation.PostConstruct）时， 才需要注册处理 javax.annotation.PostConstruct 的兜底处理器。
     */
    @Bean
    @Conditional(JakartaAnnotationCondition.class)
    public static BeanPostProcessor javaxPostConstructBeanPostProcessor() {
        return new JavaxPostConstructBeanPostProcessor();
    }

    /**
     * 必须是 static 内部类；包可见（不加 private 即可）
     */
    static class JakartaAnnotationCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            try {
                Class.forName("jakarta.annotation.PostConstruct");
                return true; // Jakarta 环境，注册兜底处理器
            }
            catch (ClassNotFoundException e) {
                return false; // 老 Spring 环境，原生已处理 javax，无需重复注册
            }
        }
    }

    /**
     * 必须是 static 内部类
     */
    static class JavaxPostConstructBeanPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(final Object bean, String beanName) {
            ReflectionUtils.doWithMethods(bean.getClass(), new ReflectionUtils.MethodCallback() {
                @Override
                public void doWith(Method method) throws IllegalArgumentException {
                    if (method.isAnnotationPresent(PostConstruct.class)) {
                        ReflectionUtils.makeAccessible(method);
                        ReflectionUtils.invokeMethod(method, bean);
                    }
                }
            }, ReflectionUtils.USER_DECLARED_METHODS);
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) {
            // 不做额外处理，原样返回
            return bean;
        }
    }
}