package io.king.core.api.cycle;

import lombok.SneakyThrows;

import java.io.Closeable;
import java.io.IOException;

/**
 * Cycle of core
 * This class is responsible to create instance of modules
 */
public abstract class LifeCycle implements Closeable {

    /**
     * Pre init the life cycle
     *
     * @param context life's context
     */
    public void preInit(LifeContext context) {
        //TODO: pre init the life cycle
    }

    /**
     * When module is initialized, this method is called
     *
     * @param context life's context
     */
    public void init(LifeContext context) {
        //TODO: init life cycle
    }

    /**
     * When module is closed, this method is called
     *
     * @param context death's context
     */
    public void dispose(LifeContext context) {
        //TODO: dispose the life cycle
    }

    /**
     * Auto closeable instances
     *
     * @throws IOException throws when cant close the instance
     */
    @Override
    public void close() throws IOException {
        //TODO: libraries that annotated with closeable are closed automatically
    }

    /**
     * Inline auto closeable
     *
     * @param endLife instances
     */
    @SneakyThrows
    public void end(AutoCloseable... endLife) {
        for (AutoCloseable end : endLife) {
            end.close();
        }
    }
}
