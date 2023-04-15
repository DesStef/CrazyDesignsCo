package bg.finalexam.crazydesignsco.service.impl;

import bg.finalexam.crazydesignsco.model.entity.UserEntity;
import bg.finalexam.crazydesignsco.model.entity.UserRoleEntity;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;
import bg.finalexam.crazydesignsco.model.user.DesignCoUserDetails;
import bg.finalexam.crazydesignsco.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DesignCoUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private DesignCoUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new DesignCoUserDetailsService(
                mockUserRepo
        );
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        // arrange
        UserEntity testUserEntity =
                new UserEntity().
                        setEmail("pesho@example.com").
                        setPassword("topsecret").
                        setFirstName("Pesho").
                        setLastName("Petrov").
                        setActive(true).
                        setImageUrl("http://image.com/image").
                        setUserRoles(
                                List.of(
                                        new UserRoleEntity().setUserRoleEnum(UserRoleEnum.ADMIN),
                                        new UserRoleEntity().setUserRoleEnum(UserRoleEnum.USER)
                                )
                        );

        when(mockUserRepo.findByEmail(testUserEntity.getEmail())).
                thenReturn(Optional.of(testUserEntity));

        // act
        DesignCoUserDetails userDetails = (DesignCoUserDetails)
                toTest.loadUserByUsername(testUserEntity.getEmail());

        // assert
        Assertions.assertEquals(testUserEntity.getEmail(),
                userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getFirstName(), userDetails.getFirstName());
        Assertions.assertEquals(testUserEntity.getLastName(), userDetails.getLastName());
        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUserEntity.getFirstName() + " " + testUserEntity.getLastName(),
                userDetails.getFullName());


        var authorities = userDetails.getAuthorities();

        Assertions.assertEquals(2, authorities.size());

        var authoritiesIter = authorities.iterator();

        Assertions.assertEquals("ROLE_" + UserRoleEnum.ADMIN.name(),
                authoritiesIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + UserRoleEnum.USER.name(),
                authoritiesIter.next().getAuthority());
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {

        // arrange
        // NOTE: No need to arrange anything, mocks return empty optionals.

        // act && assert
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("non-existant@example.com")
        );
    }
}
