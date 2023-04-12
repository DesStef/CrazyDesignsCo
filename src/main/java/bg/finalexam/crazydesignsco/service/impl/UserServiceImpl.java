package bg.finalexam.crazydesignsco.service.impl;

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
import bg.finalexam.crazydesignsco.service.UserService;
import bg.finalexam.crazydesignsco.service.impl.DesignServiceImpl;
import bg.finalexam.crazydesignsco.service.impl.EmailServiceImpl;
import bg.finalexam.crazydesignsco.service.impl.UserRoleServiceImpl;
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
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailsService;
    private final DesignServiceImpl designServiceImpl;
    private final EmailServiceImpl emailServiceImpl;
    private final UserRoleRepository userRoleRepository;
    private final UserRoleServiceImpl userRoleServiceImpl;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper,
                           UserDetailsService userDetailsService,
                           DesignServiceImpl designServiceImpl, EmailServiceImpl emailServiceImpl,
                           UserRoleRepository userRoleRepository,
                           UserRoleServiceImpl userRoleServiceImpl) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
        this.designServiceImpl = designServiceImpl;
        this.emailServiceImpl = emailServiceImpl;
        this.userRoleRepository = userRoleRepository;
        this.userRoleServiceImpl = userRoleServiceImpl;
    }

    @Override
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

    @Override
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
        emailServiceImpl.sendRegistrationEmail(newUser.getEmail(),
                newUser.getFirstName() + " " + newUser.getLastName(),
                preferredLocale);
    }

    @Override
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

    @Override
    public UserEntity findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public Optional<UserDetailsDTO> findUserById(Long userId) {
        return userRepository.
                findById(userId).
                map(userMapper::userEntityToUserDetailsDto);
    }

    @Override
    public Optional<UserServiceModel> findUserByIdReturnUserModel(Long userId) {
        return userRepository.
                findById(userId).
                map(userMapper::userEntityToUserServiceModel);
    }


    @Override
    public Optional<MyProfileServiceModel> findUserByIdReturnMyProfileModel(Long userId) {
        return userRepository.
                findById(userId).
                map(userMapper::userEntityToMyProfileServiceModel);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteUserEntityById(userId);
    }

    @Override
    public List<String> findUserRolesByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not exists."));
        return userEntity.getUserRoles().stream()
                .sorted(Comparator.comparing(UserRoleEntity::getId))
                .map(userRoleEntity -> userRoleEntity.getUserRoleEnum().name())
                .collect(Collectors.toList());
    }

    @Override
    public void editUser(Long id, UserServiceModel userServiceModel) {
        UserEntity userFromRepo = userRepository.findById(id).
                orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not exists."));

        if (userServiceModel.getPassword() == null || userServiceModel.getPassword().isEmpty()) {
            userServiceModel.setPassword(userFromRepo.getPassword());
        }
        userMapper.updateUserFromUserServiceModel(userServiceModel, userFromRepo);

        UserRoleEntity newUserRole = userRoleServiceImpl.findUserRole(userServiceModel.getUserRoleEnum());
        List<UserRoleEntity> currentUserRoles = userFromRepo.getUserRoles();

        if (newUserRole != null) {
            if (!currentUserRoles.contains(newUserRole)) {
                userFromRepo.addRole(newUserRole);
            } else {
                currentUserRoles.remove(newUserRole);
                userFromRepo.setUserRoles(currentUserRoles);
            }
        }

        userRepository.save(userFromRepo);
    }

    @Override
    public void changeUserRole(String email, String role) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            List<UserRoleEntity> userRoles = userEntity.getUserRoles();
            UserRoleEntity userRole = userRoleServiceImpl.findUserRole(UserRoleEnum.valueOf(role));
            if (!userRoles.contains(userRole)) {
                userRoles.add(userRole);
            }

            userEntity.setUserRoles(userRoles);
            userRepository.save(userEntity);
        }
    }

    @Override
    public void editMyProfile(Long id, MyProfileServiceModel myProfileServiceModel) {
        UserEntity userFromRepo = userRepository.findById(id).
                orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not exists."));

        userMapper.updateUserFromMyProfileServiceModel(myProfileServiceModel, userFromRepo);

        userFromRepo.setPassword(passwordEncoder.encode(myProfileServiceModel.getPassword()));
        userFromRepo.setId(id);

        userRepository.save(userFromRepo);
    }

    @Override
    public UserServiceModel existingEmailExceptId(String email, Long id) {
        return userRepository.findByEmailExcludingId(email, id)
                .map(userMapper::userEntityToUserServiceModel)
                .orElse(null);
    }

//    TODO UserDetailsView to be added
    @Override
    public Page<UserDetailsDTO> getAllUsers(Pageable pageable) {
        return userRepository.
                findAll(pageable).
                map(userMapper::userEntityToUserDetailsDto);
    }

}
