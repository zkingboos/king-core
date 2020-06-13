package io.king.core.api.service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * DependencyInjection (DI)
 * Insert instance on field of service
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
