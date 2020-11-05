package com.wgs.extend.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 无状态接口，实现方法无须判断数据源
 */
@Target({ElementType.METHOD})//作用方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoStatus {
        String description() default "";
}
