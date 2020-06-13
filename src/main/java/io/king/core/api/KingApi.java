package io.king.core.api;

import me.saiintbrisson.commands.CommandFrame;

/**
 * Heart of core
 */
public interface KingApi {

    /**
     * https://github.com/SaiintBrisson/command-framework
     * @return instance to register others plugins
     */
    CommandFrame getCommandFrame();
}
