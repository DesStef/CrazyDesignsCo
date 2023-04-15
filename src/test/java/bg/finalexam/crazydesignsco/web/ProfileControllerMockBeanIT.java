package bg.finalexam.crazydesignsco.web;

import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import bg.finalexam.crazydesignsco.model.entity.UserEntity;
import bg.finalexam.crazydesignsco.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerMockBeanIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testUser, testAdmin;


    @Transactional
    @BeforeEach
    void setUp() {
        testUser = testDataUtils.createTestUser("user1@example.com");
        testAdmin = testDataUtils.createTestAdmin("admin2@example.com");
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @WithMockUser(
            username = "user1@example.com",
            roles = "USER"
    )
    @Test
    void testShowMyProfile() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/profile")).
                andExpect(status().isOk())
                .andExpect(view().name("profile"));

    }
}
