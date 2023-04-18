package bg.finalexam.crazydesignsco.service;

import bg.finalexam.crazydesignsco.model.dto.user.UserDetailsDTO;
import bg.finalexam.crazydesignsco.model.dto.user.UserRegisterDTO;
import bg.finalexam.crazydesignsco.model.entity.UserEntity;
import bg.finalexam.crazydesignsco.model.entity.UserRoleEntity;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;
import bg.finalexam.crazydesignsco.model.service.MyProfileServiceModel;
import bg.finalexam.crazydesignsco.model.service.UserServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public interface UserService {

    void createUserIfNotExist(String email);

    void registerAndLogin(UserRegisterDTO userRegisterDTO, Locale preferredLocale);

    void login(String userName);

    UserEntity findByEmail(String email);

    Optional<UserDetailsDTO> findUserById(Long userId);

    Optional<UserServiceModel> findUserByIdReturnUserModel(Long userId);

    Optional<MyProfileServiceModel> findUserByIdReturnMyProfileModel(Long userId);

    void deleteUserById(Long userId);

    List<String> findUserRolesByEmail(String email);

    void editUser(Long id, UserServiceModel userServiceModel);

    void changeUserRole(String email, String role);

    void editMyProfile(Long id, MyProfileServiceModel myProfileServiceModel);

    UserServiceModel existingEmailExceptId(String email, Long id);

    Page<UserDetailsDTO> getAllUsers(Pageable pageable);
}
