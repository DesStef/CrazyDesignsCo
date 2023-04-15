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
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DesignEntityControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testUser, testAdmin;

    private DesignEntity testDesignEntity, testAdminDesignEntity;

    @Transactional
    @BeforeEach
    void setUp() {
        testUser = testDataUtils.createTestUser("user1@example.com");
        testAdmin = testDataUtils.createTestAdmin("admin2@example.com");

        testDesignEntity = testDataUtils.createTestDesign(testUser);
        testAdminDesignEntity = testDataUtils.createTestDesign(testAdmin);
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    @WithMockUser
    void testAddDesignPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/designs/add")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteByAnonymousUser_Forbidden() throws Exception {
        mockMvc.perform(delete("/designs/{id}", testDesignEntity.getId()).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(
            username = "admin2@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testDeleteByAdmin() throws Exception {
        mockMvc.perform(delete("/designs/{id}", testDesignEntity.getId()).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/designs/all"));
    }

    @WithMockUser(
            username = "user1@example.com",
            roles = "USER"
    )
    @Test
    void testDeleteByOwner() throws Exception {
        mockMvc.perform(delete("/designs/{id}", testDesignEntity.getId()).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/designs/all"));
    }

    @WithMockUser(
            username = "user1@example.com",
            roles = "USER"
    )
    @Test
    public void testDeleteNotOwned_Forbidden() throws Exception {
        mockMvc.perform(delete("/designs/{id}", testAdminDesignEntity.getId()).
                        with(csrf())
                ).
                andExpect(status().isForbidden());
    }

    @WithUserDetails(value = "user1@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    @Test
    void testAddDesign() throws Exception {

        mockMvc.perform(post("/designs/add").
                        param("title", "Test title").
                        param("roomType", "BATHROOM").
                        param("style", "BOHEMIAN").
                        param("squareMetres", "30").
                        param("price", "10").
                        param("description", "test description").
                        param("imageUrl", "https://www.decorilla.com/online-decorating/wp-content/uploads/2020/01/bohemian-interior-design-feature.jpg").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/designs/all"));
    }

    @WithMockUser(
            username = "user1@example.com",
            roles = "USER"
    )
    @Test
    void testShowDesignDetails() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/designs/{id}", testDesignEntity.getId())).
                andExpect(status().isOk())
                .andExpect(view().name("design-details"));

    }
}
