package bg.finalexam.crazydesignsco.model.dto.design;

import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;
import bg.finalexam.crazydesignsco.model.enums.StyleEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SearchDesignDTO {

    private StyleEnum style;

    private RoomTypeEnum roomType;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private LocalDate minDate;

    private LocalDate maxDate;


    public RoomTypeEnum getRoomType() {
        return roomType;
    }

    public SearchDesignDTO setRoomType(RoomTypeEnum roomType) {
        this.roomType = roomType;
        return this;
    }


    public StyleEnum getStyle() {
        return style;
    }

    public SearchDesignDTO setStyle(StyleEnum style) {
        this.style = style;
        return this;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public SearchDesignDTO setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public SearchDesignDTO setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public LocalDate getMinDate() {
        return minDate;
    }

    public SearchDesignDTO setMinDate(LocalDate minDate) {
        this.minDate = minDate;
        return this;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }

    public SearchDesignDTO setMaxDate(LocalDate maxDate) {
        this.maxDate = maxDate;
        return this;
    }

    public boolean isEmpty() {
        return
                (style == null || style.toString().isEmpty()) &&
                        (roomType == null || roomType.toString().isEmpty()) &&
                        minPrice == null &&
                        maxPrice == null &&
                        minDate == null &&
                        maxDate == null;
    }

    @Override
    public String toString() {
        return "SearchDesignDTO{" +
                "style=" + style +
                ", roomType=" + roomType +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", minDate=" + minDate +
                ", maxDate=" + maxDate +
                '}';
    }
}
