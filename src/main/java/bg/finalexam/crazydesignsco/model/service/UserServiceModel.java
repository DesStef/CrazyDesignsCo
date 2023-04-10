package bg.finalexam.crazydesignsco.model.service;

import bg.finalexam.crazydesignsco.model.dto.user.UserUpdateDTO;
import bg.finalexam.crazydesignsco.model.entity.UserRoleEntity;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;
import bg.finalexam.crazydesignsco.model.validation.UniqueUserEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserServiceModel {

    private Long id;

    @NotEmpty(message = "User email should be provided.")
    @Email(message = "User email should be valid.")
    @UniqueUserEmail(message = "User email should be unique.")
    private String email;


    @NotBlank(message = "Please enter first name")
    @Size(
            min=1,
            max=20,
            message = "Name should be between 1 and 20 characters."
    )
    private String firstName;

    @NotBlank(message = "Please enter first name")
    @Size(
            min=1,
            max=20,
            message = "Name should be between 1 and 20 characters."
    )
    private String lastName;

    private String password;

    @NotEmpty
    @Size(min = 5)
    private String phone;

    private boolean isActive;

    private boolean wantDeletion;

//    private UserRoleEntity userRoleEntity;

    private UserRoleEnum userRoleEnum;

//    @Size(min = 5)
//    private String confirmPassword;

    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }


    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserServiceModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserServiceModel setActive(boolean active) {
        isActive = active;
        return this;
    }

    public boolean isWantDeletion() {
        return wantDeletion;
    }

    public UserServiceModel setWantDeletion(boolean wantDeletion) {
        this.wantDeletion = wantDeletion;
        return this;
    }

//    public UserRoleEntity getUserRoleEntity() {
//        return userRoleEntity;
//    }
//
//    public UserServiceModel setUserRoleEntity(UserRoleEntity userRoleEntity) {
//        this.userRoleEntity = userRoleEntity;
//        return this;
//    }


    public UserRoleEnum getUserRoleEnum() {
        return userRoleEnum;
    }

    public UserServiceModel setUserRoleEnum(UserRoleEnum userRoleEnum) {
        this.userRoleEnum = userRoleEnum;
        return this;
    }

    @Override
    public String toString() {
        return "UserServiceModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                ", wantDeletion=" + wantDeletion +
                ", userRoleEnum=" + userRoleEnum +
                '}';
    }

//    public String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    public UserServiceModel setConfirmPassword(String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//        return this;
//    }
}
