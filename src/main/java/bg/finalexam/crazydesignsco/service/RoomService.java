package bg.finalexam.crazydesignsco.service;

import bg.finalexam.crazydesignsco.model.dto.room.RoomDTO;
import bg.finalexam.crazydesignsco.model.entity.RoomEntity;
import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;

import java.util.List;

public interface RoomService {

    List<RoomDTO> getAllRooms();

    void deleteRoomById(Long roomId);

    RoomEntity addRoom(RoomTypeEnum roomType, Float squareMetres);

    RoomEntity findByID(Long id);
}
