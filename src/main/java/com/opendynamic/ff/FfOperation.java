package com.opendynamic.ff;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public @interface FfOperation {
    String procId() default "";// 流程ID

    String nodeId() default "";// 节点ID

    String taskId() default "";// 任务ID

    String memo() default "";// 备注

    String operator() default "";// 操作人
}