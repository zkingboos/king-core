package io.king.core.api.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotes this class on a @Service to use
 * Inject constructors
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Injectable {
}
