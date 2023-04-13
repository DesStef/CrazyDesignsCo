package bg.finalexam.crazydesignsco.config;

import bg.finalexam.crazydesignsco.service.DesignService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public class OwnerSecurityExpressionRoot
        extends SecurityExpressionRoot
        implements MethodSecurityExpressionOperations {

    private final Authentication authentication;
    private final DesignService designService;
    private Object filterObject;
    private Object returnObject;

    public OwnerSecurityExpressionRoot(Authentication authentication,
                                       DesignService designService) {
        super(authentication);
        this.authentication = authentication;
        this.designService = designService;
    }

    public boolean isOwner(UUID id) {
        if (authentication.getPrincipal() == null) {
            return false;
        }

        var userName = authentication.getName();

        return designService.isOwner(userName, id);
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
