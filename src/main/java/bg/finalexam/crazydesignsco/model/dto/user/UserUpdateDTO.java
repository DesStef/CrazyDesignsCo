package bg.finalexam.crazydesignsco.model.dto.user;

import bg.finalexam.crazydesignsco.model.entity.UserRoleEntity;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;
import bg.finalexam.crazydesignsco.model.validation.FieldMatch;
import bg.finalexam.crazydesignsco.model.validation.UniqueUserEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
//
//@FieldMatch(
//        first = "password",
//        second = "confirmPassword",
//        message = "Passwords do not match."
//)
public class UserUpdateDTO {

    private Long id;

    @NotEmpty(message = "User email should be provided.")
    @Email(message = "User email should be valid.")
//    @UniqueUserEmail(message = "User email should be unique.")
    private String email;


    @NotBlank(message = "Please enter first name")
    @Size(
            min=1,
            max=30,
            message = "Name should be between 1 and 30 characters."
    )
    private String firstName;

    @NotBlank(message = "Please enter first name")
    @Size(
            min=1,
            max=30,
            message = "Name should be between 1 and 30 characters."
    )
    private String lastName;

    private String password;

    private String confirmPassword;

    @NotEmpty
    @Size(min = 5)
    private String phone;

    private boolean isActive;
    private boolean wantDeletion;

    private UserRoleEnum userRoleEnum;

//    private List<UserRoleEntity> userRoles;

    public UserUpdateDTO() {
    }

    public Long getId() {
        return id;
    }

    public UserUpdateDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserUpdateDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserUpdateDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserUpdateDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserUpdateDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserUpdateDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserUpdateDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserUpdateDTO setActive(boolean active) {
        isActive = active;
        return this;
    }

    public UserRoleEnum getUserRoleEnum() {
        return userRoleEnum;
    }

    public UserUpdateDTO setUserRoleEnum(UserRoleEnum userRoleEnum) {
        this.userRoleEnum = userRoleEnum;
        return this;
    }

//
//    public List<UserRoleEntity> getUserRoles() {
//        return userRoles;
//    }
//
//    public UserUpdateDTO setUserRoles(List<UserRoleEntity> userRoles) {
//        this.userRoles = userRoles;
//        return this;
//    }

    public boolean isWantDeletion() {
        return wantDeletion;
    }

    public UserUpdateDTO setWantDeletion(boolean wantDeletion) {
        this.wantDeletion = wantDeletion;
        return this;
    }

    @Override
    public String toString() {
        return "UserUpdateDTO{" +
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
}
