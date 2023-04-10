package bg.finalexam.crazydesignsco.model.view;

import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;
import bg.finalexam.crazydesignsco.model.enums.StyleEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class DesignsHighlightViewModel {

    private UUID id;
    private String title;
    private RoomTypeEnum roomType;
    private float squareMetres;
    private String imageUrl;
    private BigDecimal price;
    private StyleEnum style;
    private LocalDate date;
    private Long creatorId;


    public DesignsHighlightViewModel() {
    }

    public DesignsHighlightViewModel(UUID id, String title, RoomTypeEnum roomType,
                                     float squareMetres, String imageUrl, BigDecimal price,
                                     StyleEnum style, LocalDate date, Long creatorId) {
        this.id = id;
        this.title = title;
        this.roomType = roomType;
        this.squareMetres = squareMetres;
        this.imageUrl = imageUrl;
        this.price = price;
        this.style = style;
        this.date = date;
        this.creatorId = creatorId;
    }

    public String getTitle() {
        return title;
    }

    public DesignsHighlightViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public RoomTypeEnum getRoomType() {
        return roomType;
    }

    public DesignsHighlightViewModel setRoomType(RoomTypeEnum roomType) {
        this.roomType = roomType;
        return this;
    }

    public float getSquareMetres() {
        return squareMetres;
    }

    public DesignsHighlightViewModel setSquareMetres(float squareMetres) {
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


    public String getImageUrl() {
        return imageUrl;
    }

    public DesignsHighlightViewModel setImageUrls(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public DesignsHighlightViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public DesignsHighlightViewModel setStyle(StyleEnum style) {
        this.style = style;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public DesignsHighlightViewModel setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public DesignsHighlightViewModel setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public DesignsHighlightViewModel setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getDesignHighlight() {
        return this.title + ", "+ this.squareMetres + " m2" ;
    }
}
