package bg.finalexam.crazydesignsco.model.entity;

import bg.finalexam.crazydesignsco.model.enums.StyleEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "designs")
public class DesignEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false)
    private String title;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OneToOne()
    private RoomEntity room;

//    @Column(nullable = false)
//    private String imageUrl;

    @Column(nullable = false)
    @OneToMany(mappedBy = "design", fetch = FetchType.EAGER)
    private Set<PictureEntity> pictures;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StyleEnum style;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    private UserEntity creator;

    @OneToMany(targetEntity = CommentEntity.class, mappedBy = "designEntity", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    public DesignEntity() {
    }

    public UUID getId() {
        return id;
    }

    public DesignEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public DesignEntity setTitle(String title) {
        this.title = title;
        return this;
    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public DesignEntity setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//        return this;
//    }


    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public DesignEntity setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public DesignEntity setRoom(RoomEntity room) {
        this.room = room;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public DesignEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DesignEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public DesignEntity setStyle(StyleEnum style) {
        this.style = style;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public DesignEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public DesignEntity setCreator(UserEntity creator) {
        this.creator = creator;
        return this;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public DesignEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
