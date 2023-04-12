package bg.finalexam.crazydesignsco.service.impl;

import bg.finalexam.crazydesignsco.model.dto.room.RoomDTO;
import bg.finalexam.crazydesignsco.model.entity.RoomEntity;
import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;
import bg.finalexam.crazydesignsco.repository.RoomRepository;
import bg.finalexam.crazydesignsco.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        return roomRepository.
                findAll().
                stream().
                map(this::mapRooms).
                collect(Collectors.toList());
    }

    private RoomDTO mapRooms(RoomEntity roomEntity) {

        return new RoomDTO().
                setId(roomEntity.getId()).
                setRoomType(roomEntity.getRoomType());
    }

    @Override
    public void deleteRoomById(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    @Override
    public RoomEntity addRoom(RoomTypeEnum roomType, Float squareMetres) {
        RoomEntity newRoom = new RoomEntity();

        newRoom.setRoomType(roomType);
        newRoom.setSquareMetres(squareMetres);

        roomRepository.save(newRoom);

        return newRoom;
    }

    @Override
    public RoomEntity findByID(Long id) {
        return roomRepository.findById(id).orElseThrow();
    }

}
