package bg.finalexam.crazydesignsco.model.dto.room;

import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class RoomDTO {

    private long id;

    @NotNull(message = "Please select room type!")
    private RoomTypeEnum roomType;

    public long getId() {
        return id;
    }

    public RoomDTO setId(long id) {
        this.id = id;
        return this;
    }
//    @Positive
//    private float squareMetres;
//
//    public float getSquareMetres() {
//        return squareMetres;
//    }
//
//    public RoomDTO setSquareMetres(float squareMetres) {
//        this.squareMetres = squareMetres;
//        return this;
//    }

    public RoomTypeEnum getRoomType() {
        return roomType;
    }

    public RoomDTO setRoomType(RoomTypeEnum roomType) {
        this.roomType = roomType;
        return this;
    }

}
