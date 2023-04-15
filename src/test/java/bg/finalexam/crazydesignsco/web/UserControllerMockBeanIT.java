package bg.finalexam.crazydesignsco.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.Cookie;
import java.util.Locale;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerMockBeanIT {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLogin_ReturnLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( "/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-login"));
    }

    @Test
    public void loginError_RedirectBackToLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login-error")
                        .param("email", "user@example.com")
                        .param("password", "111111")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
//                .andExpect(flash().attributeCount(1))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    void testUserLogin() throws Exception {
        mockMvc.perform(post("/users/login").
                        param("email", "user@example.com").
                        param("password", "topsecret").
                        with(csrf())
                ).
                andExpect(status().isOk());
//                andExpect(redirectedUrl("/"));
    }
}
