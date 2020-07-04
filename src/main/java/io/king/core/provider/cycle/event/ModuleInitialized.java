package io.king.core.provider.cycle.event;

import io.king.core.provider.module.ModuleObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class ModuleInitialized {
    private final ModuleObject moduleObject;
}
