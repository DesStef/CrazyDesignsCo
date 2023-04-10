package bg.finalexam.crazydesignsco.repository;

import bg.finalexam.crazydesignsco.model.entity.RoomEntity;
import bg.finalexam.crazydesignsco.model.entity.UserRoleEntity;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
//    this query is causing error
//    void deleteAllInBatch(List<UserRoleEntity> userRoles);
  UserRoleEntity findUserRoleEntityByUserRoleEnum(UserRoleEnum userRoleEnum);
}
