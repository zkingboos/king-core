package io.king.core.api.module;

import org.bukkit.event.Listener;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The all files needs to be annoted with @Module
 * It indicate that file is valid and core can load him
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Module {
    Class<? extends ModuleConfig> config();

    Class<? extends Listener>[] events() default {};

    Class<?>[] softDepend() default {};

    Class<?>[] imports() default {};

    Class<?>[] commands() default {};

    ModulePriority priority() default ModulePriority.NORMAL;

    Class<?>[] services() default {};
}
