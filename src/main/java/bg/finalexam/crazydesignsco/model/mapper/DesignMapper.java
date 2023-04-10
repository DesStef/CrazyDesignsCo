package bg.finalexam.crazydesignsco.model.mapper;

import bg.finalexam.crazydesignsco.model.dto.design.CreateDesignDTO;
import bg.finalexam.crazydesignsco.model.dto.design.DesignDetailsDTO;
import bg.finalexam.crazydesignsco.model.dto.design.MyDesignsDetailsDTO;
import bg.finalexam.crazydesignsco.model.dto.design.UpdateDesignDTO;
import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DesignMapper {
    @Mapping(source = "roomType", target = "room.roomType")
    @Mapping(source = "squareMetres", target = "room.squareMetres")
    DesignEntity createDesignDtoToDesignEntity(CreateDesignDTO createDesignDTO);

    @Mapping(source = "roomType", target = "room.roomType")
    @Mapping(source = "squareMetres", target = "room.squareMetres")
    DesignEntity updateDesignDtoToDesignEntity(UpdateDesignDTO updateDesignDTO);

    @Mapping(source = "roomType", target = "room.roomType")
    @Mapping(source = "squareMetres", target = "room.squareMetres")
    void updateDesignFromUpdateDesignDto(UpdateDesignDTO dto, @MappingTarget DesignEntity design);

    @Mapping(source = "room.roomType", target = "roomType")
    @Mapping(source = "room.squareMetres", target = "squareMetres")
    CreateDesignDTO designEntityToCreateDesignDto(DesignEntity designEntity);

    @Mapping(source = "room.roomType", target = "roomType")
    @Mapping(source = "room.squareMetres", target = "squareMetres")
    UpdateDesignDTO designEntityToUpdateDesignDto(DesignEntity designEntity);

    @Mapping(source = "room.roomType", target = "roomType")
    @Mapping(source = "room.squareMetres", target = "squareMetres")
    @Mapping(source = "creator.firstName", target = "creatorFirstName")
    @Mapping(source = "creator.lastName", target = "creatorLastName")
    DesignDetailsDTO designEntityToDesignDetailDto(DesignEntity designEntity);

    @Mapping(source = "room.roomType", target = "roomType")
    @Mapping(source = "room.squareMetres", target = "squareMetres")
    @Mapping(source = "creator.firstName", target = "creatorFirstName")
    @Mapping(source = "creator.lastName", target = "creatorLastName")
    @Mapping(source = "creator.id", target = "creatorId")
    MyDesignsDetailsDTO designEntityToMyDesignsDetailsDto(DesignEntity designEntity);

}
