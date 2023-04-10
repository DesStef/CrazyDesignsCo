package bg.finalexam.crazydesignsco.service;

import bg.finalexam.crazydesignsco.model.dto.user.UserDetailsDTO;
import bg.finalexam.crazydesignsco.model.dto.user.UserRegisterDTO;
import bg.finalexam.crazydesignsco.model.entity.UserEntity;
import bg.finalexam.crazydesignsco.model.entity.UserRoleEntity;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;
import bg.finalexam.crazydesignsco.model.mapper.UserMapper;
import bg.finalexam.crazydesignsco.model.service.MyProfileServiceModel;
import bg.finalexam.crazydesignsco.model.service.UserServiceModel;
import bg.finalexam.crazydesignsco.repository.UserRepository;
import bg.finalexam.crazydesignsco.repository.UserRoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailsService;
    private final DesignService designService;
    private final EmailService emailService;
    private final UserRoleRepository userRoleRepository;
    private final UserRoleService userRoleService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper,
                       UserDetailsService userDetailsService,
                       DesignService designService, EmailService emailService,
                       UserRoleRepository userRoleRepository,
                       UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
        this.designService = designService;
        this.emailService = emailService;
        this.userRoleRepository = userRoleRepository;
        this.userRoleService = userRoleService;
    }

    public void createUserIfNotExist(String email) {

        var userOpt = this.userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            var newUser = new UserEntity().
                    setEmail(email).
                    setPassword(null).
                    setFirstName("New").
                    setLastName("User").
                    setActive(true).
                    setWantDeletion(false).
                    setPhone("Phone").
                    setUserRoles(List.of());
            userRepository.save(newUser);
        }
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO,
                                 Locale preferredLocale) {

        UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        newUser.setActive(true);
        newUser.setWantDeletion(false);
        UserRoleEntity userRole = new UserRoleEntity().setUserRoleEnum(UserRoleEnum.USER);
        newUser.addRole(userRole);

        this.userRepository.save(newUser);
        login(newUser.getEmail());
        emailService.sendRegistrationEmail(newUser.getEmail(),
                newUser.getFirstName() + " " + newUser.getLastName(),
                preferredLocale);
    }


    public void login(String userName) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userName);

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

    public UserEntity findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow();
    }

    public Optional<UserDetailsDTO> findUserById(Long userId) {
        return userRepository.
                findById(userId).
                map(userMapper::userEntityToUserDetailsDto);
    }

    public Optional<UserServiceModel> findUserByIdReturnUserModel(Long userId) {
        return userRepository.
                findById(userId).
                map(userMapper::userEntityToUserServiceModel);
    }


    public Optional<MyProfileServiceModel> findUserByIdReturnMyProfileModel(Long userId) {
        return userRepository.
                findById(userId).
                map(userMapper::userEntityToMyProfileServiceModel);
    }

    //    TODO: delete is not working
//    public boolean deleteUserById(Long id) {
////        UserEntity userToDelete = userRepository.findById(id).orElseThrow();
////
////        List<UserRoleEntity> userRoles = userToDelete.getUserRoles();
////        userRoleRepository.deleteAllInBatch(userRoles);
//
//        try {
//            userRepository.deleteById(id);
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//    }

    public void deleteUserById(Long userId) {
        userRepository.deleteUserEntityById(userId);
    }

//    private boolean isAdmin(String name) {
//        return userRepository.
//                findByEmail(name).orElseThrow().
//                getUserRoles().
//                stream().
//                anyMatch(r -> r.getUserRoleEnum() == UserRoleEnum.ADMIN);
//    }

    public List<String> findUserRolesByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not exists."));
        return userEntity.getUserRoles().stream()
                .sorted(Comparator.comparing(UserRoleEntity::getId))
                .map(userRoleEntity -> userRoleEntity.getUserRoleEnum().name())
                .collect(Collectors.toList());
    }

    public void editUser(Long id, UserServiceModel userServiceModel) {
        UserEntity userFromRepo = userRepository.findById(id).
                orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not exists."));

        if (userServiceModel.getPassword() == null || userServiceModel.getPassword().isEmpty()) {
            userServiceModel.setPassword(userFromRepo.getPassword());
        }
        userMapper.updateUserFromUserServiceModel(userServiceModel, userFromRepo);

        UserRoleEntity newUserRole = userRoleService.findUserRole(userServiceModel.getUserRoleEnum());
        List<UserRoleEntity> currentUserRoles = userFromRepo.getUserRoles();
        System.out.println(newUserRole);

//        List<UserRoleEntity> userRoles = userFromRepo.getUserRoles();
//        System.out.println(userRoles.toString());
//        userRoles.add(newUserRole);
//        userFromRepo.setUserRoles(userRoles);
        if (newUserRole != null) {
            if (!currentUserRoles.contains(newUserRole)) {
                userFromRepo.addRole(newUserRole);
            } else {
                currentUserRoles.remove(newUserRole);
                userFromRepo.setUserRoles(currentUserRoles);
            }
            System.out.println(userFromRepo);
        }

        userRepository.save(userFromRepo);
    }


    public void changeUserRole(String email, String role) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            List<UserRoleEntity> userRoles = userEntity.getUserRoles();
            UserRoleEntity userRole = userRoleService.findUserRole(UserRoleEnum.valueOf(role));
            if (!userRoles.contains(userRole)) {
                userRoles.add(userRole);
            }

            userEntity.setUserRoles(userRoles);
            userRepository.save(userEntity);
        }
    }

    public void editMyProfile(Long id, MyProfileServiceModel myProfileServiceModel) {
        UserEntity userFromRepo = userRepository.findById(id).
                orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not exists."));

        userMapper.updateUserFromMyProfileServiceModel(myProfileServiceModel, userFromRepo);

        userFromRepo.setPassword(passwordEncoder.encode(myProfileServiceModel.getPassword()));
        userFromRepo.setId(id);

        userRepository.save(userFromRepo);
    }

    public UserServiceModel existingEmailExceptId(String email, Long id) {
        return userRepository.findByEmailExcludingId(email, id)
                .map(userMapper::userEntityToUserServiceModel)
                .orElse(null);
    }

    public Page<UserDetailsDTO> getAllUsers(Pageable pageable) {

        return userRepository.
                findAll(pageable).
                map(userMapper::userEntityToUserDetailsDto);
    }

}
