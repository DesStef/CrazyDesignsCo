package bg.finalexam.crazydesignsco.model.dto.user;

import bg.finalexam.crazydesignsco.model.validation.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match."
)
public class MyProfileUpdateDTO {

    private Long id;

    @NotEmpty(message = "User email should be provided.")
    @Email(message = "User email should be valid.")
//    @UniqueUserEmail(message = "User email should be unique.")
    private String email;


    @NotBlank(message = "Please enter first name")
    @Size(
            min=1,
            max=30,
            message = "Name should be between 1 and 20 characters."
    )
    private String firstName;

    @NotBlank(message = "Please enter first name")
    @Size(
            min=1,
            max=30,
            message = "Name should be between 1 and 20 characters."
    )
    private String lastName;

    @NotEmpty
    @Size(min = 5)
    private String password;

    @NotEmpty
    @Size(min = 5)
    private String confirmPassword;

    @NotEmpty
    @Size(min = 5)
    private String phone;

//    private boolean isActive;
    private boolean wantDeletion;


    public MyProfileUpdateDTO() {
    }

    public Long getId() {
        return id;
    }

    public MyProfileUpdateDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public MyProfileUpdateDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public MyProfileUpdateDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MyProfileUpdateDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public MyProfileUpdateDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MyProfileUpdateDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public MyProfileUpdateDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

//    public boolean isActive() {
//        return isActive;
//    }
//
//    public MyProfileUpdateDTO setActive(boolean active) {
//        isActive = active;
//        return this;
//    }

    public boolean isWantDeletion() {
        return wantDeletion;
    }

    public MyProfileUpdateDTO setWantDeletion(boolean wantDeletion) {
        this.wantDeletion = wantDeletion;
        return this;
    }
}
