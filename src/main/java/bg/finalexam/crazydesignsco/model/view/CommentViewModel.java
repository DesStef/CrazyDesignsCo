package bg.finalexam.crazydesignsco.model.view;

public class CommentViewModel {
    private Long id;
    private String fullName;
    private String message;

    public CommentViewModel(Long id, String fullName, String message) {
        this.id = id;
        this.fullName = fullName;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public CommentViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentViewModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public CommentViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
