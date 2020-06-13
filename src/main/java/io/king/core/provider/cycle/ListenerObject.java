package io.king.core.provider.cycle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public final class ListenerObject<T> {
    private final Class<T> typeClass;
    private final Consumer<T> consumer;
}
