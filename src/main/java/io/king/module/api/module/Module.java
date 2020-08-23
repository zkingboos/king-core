/*
 * Copyright (c) 2020 codeproton-projects
 */

package io.king.module.api.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Module {

    Class<? extends Configuration> config();

}
