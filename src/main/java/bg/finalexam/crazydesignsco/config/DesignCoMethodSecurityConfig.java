package bg.finalexam.crazydesignsco.config;

import bg.finalexam.crazydesignsco.service.impl.DesignServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DesignCoMethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    private final DesignServiceImpl designServiceImpl;

    public DesignCoMethodSecurityConfig(DesignServiceImpl designServiceImpl) {
        this.designServiceImpl = designServiceImpl;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new DesignCoSecurityExpressionHandler(designServiceImpl);
    }

}
