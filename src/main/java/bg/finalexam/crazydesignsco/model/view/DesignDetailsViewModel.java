package bg.finalexam.crazydesignsco.model.view;

import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;
import bg.finalexam.crazydesignsco.model.enums.StyleEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DesignDetailsViewModel {

    private UUID id;
    private String title;
    private RoomTypeEnum roomType;
    private float squareMetres;
    private List<String> imageUrls = new ArrayList<>();
    private BigDecimal price;
    private String description;
    private StyleEnum style;
    private LocalDate date;
    private Long creatorId;
    private String creatorFirstName;
    private String creatorLastName;


    public DesignDetailsViewModel() {
    }

    public DesignDetailsViewModel(UUID id, String title, RoomTypeEnum roomType, float squareMetres, List<String> imageUrls, BigDecimal price, String description, StyleEnum style, LocalDate date, Long creatorId, String creatorFirstName, String creatorLastName) {
        this.id = id;
        this.title = title;
        this.roomType = roomType;
        this.squareMetres = squareMetres;
        this.imageUrls = imageUrls;
        this.price = price;
        this.description = description;
        this.style = style;
        this.date = date;
        this.creatorId = creatorId;
        this.creatorFirstName = creatorFirstName;
        this.creatorLastName = creatorLastName;
    }

    public String getTitle() {
        return title;
    }

    public DesignDetailsViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public RoomTypeEnum getRoomType() {
        return roomType;
    }

    public DesignDetailsViewModel setRoomType(RoomTypeEnum roomType) {
        this.roomType = roomType;
        return this;
    }

    public float getSquareMetres() {
        return squareMetres;
    }

    public DesignDetailsViewModel setSquareMetres(float squareMetres) {
        this.squareMetres = squareMetres;
        return this;
    }

//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public MyDesignsDetailsDTO setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//        return this;
//    }


    public List<String> getImageUrls() {
        return imageUrls;
    }

    public DesignDetailsViewModel setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public DesignDetailsViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public DesignDetailsViewModel setStyle(StyleEnum style) {
        this.style = style;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public DesignDetailsViewModel setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public DesignDetailsViewModel setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public DesignDetailsViewModel setId(UUID id) {
        this.id = id;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public DesignDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCreatorFirstName() {
        return creatorFirstName;
    }

    public DesignDetailsViewModel setCreatorFirstName(String creatorFirstName) {
        this.creatorFirstName = creatorFirstName;
        return this;
    }

    public String getCreatorLastName() {
        return creatorLastName;
    }

    public DesignDetailsViewModel setCreatorLastName(String creatorLastName) {
        this.creatorLastName = creatorLastName;
        return this;
    }

    public String getCreatorFullName() {
        return creatorFirstName + " " + creatorLastName;
    }

    public String getDesignHighlight() {
        return this.title + ", "+ this.squareMetres + " m2" ;
    }
}
