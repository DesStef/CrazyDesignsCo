package bg.finalexam.crazydesignsco.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuthSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler {

    private UserServiceImpl userServiceImpl;
    public OAuthSuccessHandlerImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        setDefaultTargetUrl("/");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        if (authentication instanceof
                OAuth2AuthenticationToken oAuth2AuthenticationToken) {
            var userEmail = oAuth2AuthenticationToken.
                    getPrincipal().
                    getAttribute("email").
                    toString();

            userServiceImpl.createUserIfNotExist(userEmail);
            userServiceImpl.login(userEmail);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
