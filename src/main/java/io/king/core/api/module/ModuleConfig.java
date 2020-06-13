package io.king.core.api.module;

import lombok.RequiredArgsConstructor;

/**
 * Used to identify authors and version
 * And can integrate with my website in future
 */
@RequiredArgsConstructor
public abstract class ModuleConfig {
    private final String author, description, hashId;
}
