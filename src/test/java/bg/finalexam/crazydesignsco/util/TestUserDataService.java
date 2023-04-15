package bg.finalexam.crazydesignsco.util;

import bg.finalexam.crazydesignsco.model.user.DesignCoUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TestUserDataService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new DesignCoUserDetails(5L,
                "topsecret",
                username, "Test",
                "User",
                "12345678",
                true,
                Collections.emptyList());
    }
}
