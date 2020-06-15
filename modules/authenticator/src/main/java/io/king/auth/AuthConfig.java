package io.king.auth;

import io.king.core.api.module.ModuleConfig;

public final class AuthConfig extends ModuleConfig {

    public AuthConfig() {
        super("AuthenticatorProtocol", "zkingboos", "Protocol authenticator", null);
    }
}
