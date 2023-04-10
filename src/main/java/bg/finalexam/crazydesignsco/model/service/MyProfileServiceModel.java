package bg.finalexam.crazydesignsco.model.service;

import bg.finalexam.crazydesignsco.model.validation.UniqueUserEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MyProfileServiceModel {

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

    @NotEmpty
    @Size(min = 5)
    private String password;

    @NotEmpty
    @Size(min = 5)
    private String phone;

    private boolean isActive;

    private boolean wantDeletion;


//    @NotEmpty
//    @Size(min = 5)
//    private String confirmPassword;

    public MyProfileServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public MyProfileServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public MyProfileServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public MyProfileServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MyProfileServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

//    public String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    public UserServiceModel setConfirmPassword(String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//        return this;
//    }

    public String getEmail() {
        return email;
    }

    public MyProfileServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public MyProfileServiceModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public MyProfileServiceModel setActive(boolean active) {
        isActive = active;
        return this;
    }

    public boolean isWantDeletion() {
        return wantDeletion;
    }

    public MyProfileServiceModel setWantDeletion(boolean wantDeletion) {
        this.wantDeletion = wantDeletion;
        return this;
    }
}
