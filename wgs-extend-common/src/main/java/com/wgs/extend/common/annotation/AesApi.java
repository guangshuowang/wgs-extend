package com.wgs.extend.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口数据加密
 */
@Target({ElementType.METHOD})//作用方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AesApi {
        String description() default "";
}
