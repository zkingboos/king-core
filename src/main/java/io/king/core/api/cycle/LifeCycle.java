package io.king.core.api.cycle;

/**
 * Cycle of core
 * This class is responsible to create instance of modules
 */
public abstract class LifeCycle {

    /**
     * Pre init the life cycle
     * @param context life's context
     */
    public void preInit(LifeContext context){
        //TODO: pre init the life cycle
    }

    /**
     * When module is initialized, this method is called
     * @param context life's context
     */
    public void init(LifeContext context) {
        //TODO: init life cycle
    }

    /**
     * When module is closed, this method is called
     * @param context death's context
     */
    public void dispose(LifeContext context) {
        //TODO: dispose the life cycle
    }
}
