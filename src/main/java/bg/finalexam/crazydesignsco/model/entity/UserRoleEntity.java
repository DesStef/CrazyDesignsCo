package bg.finalexam.crazydesignsco.model.entity;

import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name ="users_roles")
public class UserRoleEntity extends BaseEntity{


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRoleEnum;

    public UserRoleEntity() {
    }

    public UserRoleEnum getUserRoleEnum() {
        return userRoleEnum;
    }

    public UserRoleEntity setUserRoleEnum(UserRoleEnum userRole) {
        this.userRoleEnum = userRole;
        return this;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                ", userRole=" + userRoleEnum +
                '}';
    }
}
