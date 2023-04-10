package bg.finalexam.crazydesignsco.model.dto.user;

import bg.finalexam.crazydesignsco.model.validation.FieldMatch;
import bg.finalexam.crazydesignsco.model.validation.UniqueUserEmail;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//@FieldMatch(
//        first = "password",
//        second = "confirmPassword",
//        message = "Passwords do not match."
//)
public class UserDetailsDTO {

    private Long id;

    @NotEmpty(message = "User email should be provided.")
    @Email(message = "User email should be valid.")
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
    private String confirmPassword;

    @NotEmpty
    @Size(min = 5)
    private String phone;

    private boolean isActive;

    public UserDetailsDTO() {
    }


    public String getFirstName() {
        return firstName;
    }

    public UserDetailsDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDetailsDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDetailsDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserDetailsDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailsDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDetailsDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserDetailsDTO setActive(boolean active) {
        isActive = active;
        return this;
    }
}
