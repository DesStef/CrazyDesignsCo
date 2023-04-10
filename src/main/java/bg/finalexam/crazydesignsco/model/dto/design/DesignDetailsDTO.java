package bg.finalexam.crazydesignsco.model.dto.design;

import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;
import bg.finalexam.crazydesignsco.model.enums.StyleEnum;

import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DesignDetailsDTO {

    private UUID id;

    @NotBlank(message = "Please add title")
    @Size(min=5,max=50,message = "Title should be between 5 and 50 characters long.")
    private String title;

    @NotNull
    private RoomTypeEnum roomType;

    @Positive
    private float squareMetres;

    private List<String> imageUrls;

    @Positive
    @NotNull(message = "Please add the price for the design!")
    private BigDecimal price;

    @NotBlank(message = "Please add description!")
    @Size(
            min=20,
            max=500,
            message = "Description should be between 20 and 500 characters long."
    )
    private String description;

    @NotNull(message = "Please select style!")
    private StyleEnum style;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @NotBlank(message = "Please enter first name")
    @Size(
            min=1,
            max=20,
            message = "Name should be between 1 and 50 characters."
    )
    private String creatorFirstName;

    @NotBlank(message = "Please enter first name")
    @Size(
            min=1,
            max=20,
            message = "Name should be between 1 and 50 characters."
    )
    private String creatorLastName;

    public DesignDetailsDTO() {
    }

    public String getTitle() {
        return title;
    }

    public DesignDetailsDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public RoomTypeEnum getRoomType() {
        return roomType;
    }

    public DesignDetailsDTO setRoomType(RoomTypeEnum roomType) {
        this.roomType = roomType;
        return this;
    }

    public float getSquareMetres() {
        return squareMetres;
    }

    public DesignDetailsDTO setSquareMetres(float squareMetres) {
        this.squareMetres = squareMetres;
        return this;
    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public DesignDetailsDTO setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//        return this;
//    }


    public List<String> getImageUrls() {
        return imageUrls;
    }

    public DesignDetailsDTO setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public DesignDetailsDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DesignDetailsDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public DesignDetailsDTO setStyle(StyleEnum style) {
        this.style = style;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public DesignDetailsDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getCreatorFirstName() {
        return creatorFirstName;
    }

    public DesignDetailsDTO setCreatorFirstName(String creatorFirstName) {
        this.creatorFirstName = creatorFirstName;
        return this;
    }

    public String getCreatorLastName() {
        return creatorLastName;
    }

    public DesignDetailsDTO setCreatorLastName(String creatorLastName) {
        this.creatorLastName = creatorLastName;
        return this;
    }

    public String getCreatorFullName() {
        return creatorFirstName + " " + creatorLastName;
    }

    public UUID getId() {
        return id;
    }

    public DesignDetailsDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getDesignHighlight() {
        return this.title + ", "+ this.squareMetres + " m2" ;
    }
}
