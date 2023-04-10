package bg.finalexam.crazydesignsco.model.dto.comment;

import java.util.UUID;

public class CreateCommentDTO {
    private UUID designId;
    private String message;
    private String firstName;
    private String lastName;
    private String email;

    public CreateCommentDTO() {
    }
    public CreateCommentDTO(UUID designId, String message, String email) {
        this.designId = designId;
        this.message = message;
        this.email = email;
    }

    public UUID getDesignId() {
        return designId;
    }

    public CreateCommentDTO setDesignId(UUID designId) {
        this.designId = designId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CreateCommentDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CreateCommentDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CreateCommentDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public CreateCommentDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
