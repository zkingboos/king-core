/*
 * Copyright codeproton-projects (c) 2020
 */

package core;

import io.king.module.api.di.Inject;
import io.king.module.api.service.Bean;
import io.king.module.api.service.Service;

@Service(
  imports = {
    CredentialService.class
  }
)
public final class TestService {

    @Bean("MysqlCredential")
    public CredentialObject connectorFactory(@Inject CredentialService service) {
        final String credential = service.getCredential();
        System.out.println("credential = " + credential);

        return new CredentialObject(credential);
    }
}
