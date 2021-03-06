package io.king.core.api.module;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Used to identify authors and version
 * And can integrate with my website in future
 */
@Getter
@RequiredArgsConstructor
public abstract class ModuleConfig {

    @NonNull
    private final String name;

    @NonNull
    private final String author;

    @NonNull
    private final String description;

    private final String hashId;
}
