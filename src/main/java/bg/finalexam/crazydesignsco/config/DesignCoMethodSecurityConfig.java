package bg.finalexam.crazydesignsco.config;

import bg.finalexam.crazydesignsco.service.DesignService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DesignCoMethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    private final DesignService designService;

    public DesignCoMethodSecurityConfig(DesignService designService) {
        this.designService = designService;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new DesignCoSecurityExpressionHandler(designService);
    }

}
