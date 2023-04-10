package bg.finalexam.crazydesignsco.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "comments")
public class CommentEntity extends BaseEntity{

    @Column(nullable = false, columnDefinition = "TEXT")
    private String userComment;

    @ManyToOne
    private DesignEntity designEntity;

    @ManyToOne
    private UserEntity user;

//    private LocalDateTime date;


    public CommentEntity() {
    }

    public String getUserComment() {
        return userComment;
    }

    public CommentEntity setUserComment(String userComment) {
        this.userComment = userComment;
        return this;
    }

    public DesignEntity getDesign() {
        return designEntity;
    }

    public CommentEntity setDesign(DesignEntity designEntity) {
        this.designEntity = designEntity;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public CommentEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
