package bg.finalexam.crazydesignsco.model.dto.design;

import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;
import bg.finalexam.crazydesignsco.model.enums.StyleEnum;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateDesignDTO {

    private UUID id;

    @Min(1)
    private Long roomId;

    @NotNull
    private RoomTypeEnum roomType;


    @NotBlank(message = "Please add title")
    @Size(min = 5, max = 50, message = "Title should be between 5 and 50 characters long.")
    private String title;

    @Positive
    private float squareMetres;

//    @Valid
//    @NotNull
//    @NotEmpty
//    @Size(min = 1, max=10, message = "Please upload 1 to 10 pictures.")
//    private List<PictureDTO> picturesDTO;

    @NotBlank(message = "Please provide image url 1!")
    @URL
    private String imageUrl;

    @URL
    private String imageUrl2;

    @URL
    private String imageUrl3;

    @NotNull(message = "Please provide total price for the design!")
    private BigDecimal price;

    @NotBlank(message = "Please add description!")
    @Size(min = 5, max = 500,
            message = "Description should be between 20 and 500 characters long.")
    private String description;

    @NotNull(message = "Please select style!")
    private StyleEnum style;

    public CreateDesignDTO() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CreateDesignDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Long getRoomId() {
        return roomId;
    }

    public CreateDesignDTO setRoomId(Long roomId) {
        this.roomId = roomId;
        return this;
    }


    public RoomTypeEnum getRoomType() {
        return roomType;
    }

    public CreateDesignDTO setRoomType(RoomTypeEnum roomType) {
        this.roomType = roomType;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CreateDesignDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public float getSquareMetres() {
        return squareMetres;
    }

    public CreateDesignDTO setSquareMetres(float squareMetres) {
        this.squareMetres = squareMetres;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CreateDesignDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public CreateDesignDTO setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
        return this;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public CreateDesignDTO setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
        return this;
    }

    public List<String> getAllImageUrls() {
        List<String> imageUrls = new ArrayList<>();

        imageUrls.add(imageUrl);
        if (imageUrl2 != null && !imageUrl2.isEmpty()) {
            imageUrls.add(imageUrl2);
        }
        if (imageUrl3 != null && !imageUrl3.isEmpty()) {
            imageUrls.add(imageUrl3);
        }

        return imageUrls;
    }

    public String getDescription() {
        return description;
    }

    public CreateDesignDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public CreateDesignDTO setStyle(StyleEnum style) {
        this.style = style;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public CreateDesignDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getDesignHighlight() {
        return this.title + ", "+ this.squareMetres + " m2" ;
    }

}
