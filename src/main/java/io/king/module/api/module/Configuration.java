/*
 * Copyright (c) 2020 codeproton-projects
 */

package io.king.module.api.module;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public abstract class Configuration {

    private final String name;
    private final String author;
    private final String version;
    private final String website;

    public Configuration(@NonNull String name, @NonNull String author, @NonNull String version, String website) {
        this.name = name;
        this.author = author;
        this.version = version;
        this.website = website;
    }

    public Configuration(String name, String author, String version) {
        this(
          name,
          author,
          version,
          null
        );
    }

    public Configuration(String name, String author) {
        this(
          name,
          author,
          "Beta-Version"
        );
    }
}
