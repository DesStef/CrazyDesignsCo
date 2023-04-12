package bg.finalexam.crazydesignsco.service.impl;

import bg.finalexam.crazydesignsco.model.dto.userRole.UserRoleDTO;
import bg.finalexam.crazydesignsco.model.entity.UserRoleEntity;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;
import bg.finalexam.crazydesignsco.repository.UserRoleRepository;
import bg.finalexam.crazydesignsco.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity admin = new UserRoleEntity().setUserRoleEnum(UserRoleEnum.ADMIN);
            UserRoleEntity user = new UserRoleEntity().setUserRoleEnum(UserRoleEnum.USER);
            userRoleRepository.saveAll(List.of(admin, user));
        }
    }

    @Override
    public List<UserRoleDTO> getAllRoles() {
        return userRoleRepository.
                findAll().
                stream().
                map(this::mapUserRoles).
                collect(Collectors.toList());
    }

    @Override
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

    @Override
    public UserRoleEntity addUserRole(UserRoleEnum userRoleEnum) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();

        userRoleEntity.setUserRoleEnum(userRoleEnum);

        userRoleRepository.save(userRoleEntity);

        return userRoleEntity;
    }

    @Override
    public UserRoleEntity findUserRole(UserRoleEnum userRoleEnum) {
        return userRoleRepository.findUserRoleEntityByUserRoleEnum(userRoleEnum);
    }
}
