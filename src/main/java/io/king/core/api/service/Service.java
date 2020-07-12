package io.king.core.api.service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Service serve as life-cycle but inside of module
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    Class<?>[] value() default {};
}