package bg.finalexam.crazydesignsco.model.mapper;

import bg.finalexam.crazydesignsco.model.dto.user.MyProfileUpdateDTO;
import bg.finalexam.crazydesignsco.model.dto.user.UserDetailsDTO;
import bg.finalexam.crazydesignsco.model.dto.user.UserRegisterDTO;
import bg.finalexam.crazydesignsco.model.dto.user.UserUpdateDTO;
import bg.finalexam.crazydesignsco.model.entity.UserEntity;
import bg.finalexam.crazydesignsco.model.service.MyProfileServiceModel;
import bg.finalexam.crazydesignsco.model.service.UserServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    UserEntity userDtoToUserEntity(UserRegisterDTO registerDTO);

//    @Mapping(target = "active", constant = "true")
    UserDetailsDTO userEntityToUserDetailsDto(UserEntity userEntity);

//    @Mapping(source = "roomType", target = "room.roomType")
//    @Mapping(source = "squareMetres", target = "room.squareMetres")
    void updateUserFromDto(UserUpdateDTO userDto, @MappingTarget UserEntity user);

    UserServiceModel userUpdateDtoToUserServiceModel(UserUpdateDTO userUpdateDTO);

//    @Mapping(source = "userRoleEnum", target = "userRoles.userRoleEnum")
    void updateUserFromUserServiceModel(UserServiceModel userServiceModel, @MappingTarget UserEntity user);
    void updateUserFromMyProfileServiceModel(MyProfileServiceModel myProfileServiceModel, @MappingTarget UserEntity user);

//    @Mapping(source = "userRoles.userRoleEnum", target = "userRoleEnum")
    UserServiceModel userEntityToUserServiceModel(UserEntity userEntity);

    UserUpdateDTO userServiceModelToUserUpdateDto(UserServiceModel userServiceModel);

    MyProfileServiceModel userEntityToMyProfileServiceModel(UserEntity userEntity);

    MyProfileUpdateDTO myProfileServiceModelToMyProfileUpdateDto(MyProfileServiceModel myProfileServiceModel);

    MyProfileServiceModel myProfileUpdateDtoToMyProfileServiceModel(MyProfileUpdateDTO myProfileUpdateDTO);

    UserEntity optionalUserEntityToUserEntity(UserEntity userEntityOptional);
}
