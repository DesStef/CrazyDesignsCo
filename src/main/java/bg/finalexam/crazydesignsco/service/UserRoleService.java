package bg.finalexam.crazydesignsco.service;

import bg.finalexam.crazydesignsco.model.dto.userRole.UserRoleDTO;
import bg.finalexam.crazydesignsco.model.entity.UserRoleEntity;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;

import java.util.List;

public interface UserRoleService {

    void initRoles();

    List<UserRoleDTO> getAllRoles();

    List<String> findAllRolesString();

    UserRoleEntity addUserRole(UserRoleEnum userRoleEnum);

    UserRoleEntity findUserRole(UserRoleEnum userRoleEnum);
}
