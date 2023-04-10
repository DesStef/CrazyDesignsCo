package bg.finalexam.crazydesignsco.model.entity;

import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;

import javax.persistence.*;

@Entity(name = "rooms")
public class RoomEntity extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomTypeEnum roomType;

    @Column(nullable = false)
    private float squareMetres;

    public RoomEntity() {
    }

    public RoomTypeEnum getRoomType() {
        return roomType;
    }

    public RoomEntity setRoomType(RoomTypeEnum category) {
        this.roomType = category;
        return this;
    }

    public float getSquareMetres() {
        return squareMetres;
    }

    public RoomEntity setSquareMetres(float squareMetres) {
        this.squareMetres = squareMetres;
        return this;
    }

}
