package io.king.core.api.module;

/**
 * Priority defines the order where module is loaded
 * System is be loaded first of normal
 */
public enum ModulePriority {
    SYSTEM,
    HIGH,
    NORMAL,
    LOW
}
