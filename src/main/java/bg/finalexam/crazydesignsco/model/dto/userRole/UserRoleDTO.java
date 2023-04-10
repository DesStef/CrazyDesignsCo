package bg.finalexam.crazydesignsco.model.dto.userRole;

import bg.finalexam.crazydesignsco.model.dto.room.RoomDTO;
import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;

import javax.validation.constraints.NotNull;

public class UserRoleDTO {
    private long id;

    @NotNull(message = "Please select user role!")
    private UserRoleEnum userRoleEnum;

    public UserRoleDTO() {
    }

    public long getId() {
        return id;
    }

    public UserRoleDTO setId(long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getUserRoleEnum() {
        return userRoleEnum;
    }

    public UserRoleDTO setUserRoleEnum(UserRoleEnum userRoleEnum) {
        this.userRoleEnum = userRoleEnum;
        return this;
    }
}
