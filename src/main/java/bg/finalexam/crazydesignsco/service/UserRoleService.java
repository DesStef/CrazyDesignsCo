package bg.finalexam.crazydesignsco.service;

import bg.finalexam.crazydesignsco.model.dto.userRole.UserRoleDTO;
import bg.finalexam.crazydesignsco.model.entity.UserRoleEntity;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;
import bg.finalexam.crazydesignsco.repository.UserRoleRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity admin = new UserRoleEntity().setUserRoleEnum(UserRoleEnum.ADMIN);
            UserRoleEntity user = new UserRoleEntity().setUserRoleEnum(UserRoleEnum.USER);
            userRoleRepository.saveAll(List.of(admin, user));
        }
    }

    public List<UserRoleDTO> getAllRoles() {
        return userRoleRepository.
                findAll().
                stream().
                map(this::mapUserRoles).
                collect(Collectors.toList());
    }

    public List<String> findAllRolesString() {
        return userRoleRepository.findAll().stream()
                .sorted(Comparator.comparing(UserRoleEntity::getId))
                .map(userRoleEntity -> userRoleEntity.getUserRoleEnum().name())
                .collect(Collectors.toList());
    }

    private UserRoleDTO mapUserRoles(UserRoleEntity userRoleEntity) {

        return new UserRoleDTO().
                setId(userRoleEntity.getId()).
                setUserRoleEnum(userRoleEntity.getUserRoleEnum());
    }

    public UserRoleEntity addUserRole(UserRoleEnum userRoleEnum) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();

        userRoleEntity.setUserRoleEnum(userRoleEnum);

        userRoleRepository.save(userRoleEntity);

        return userRoleEntity;
    }

    public UserRoleEntity findUserRole(UserRoleEnum userRoleEnum) {
        return userRoleRepository.findUserRoleEntityByUserRoleEnum(userRoleEnum);
    }
}
