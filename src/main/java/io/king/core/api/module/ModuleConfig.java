package io.king.core.api.module;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Used to identify authors and version
 * And can integrate with my website in future
 */
@Getter
@RequiredArgsConstructor
public abstract class ModuleConfig {
    private final String name, author, description, hashId;
}
