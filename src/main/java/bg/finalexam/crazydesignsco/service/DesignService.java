package bg.finalexam.crazydesignsco.service;

import bg.finalexam.crazydesignsco.model.dto.design.CreateDesignDTO;
import bg.finalexam.crazydesignsco.model.dto.design.DesignDetailsDTO;
import bg.finalexam.crazydesignsco.model.dto.design.SearchDesignDTO;
import bg.finalexam.crazydesignsco.model.dto.design.UpdateDesignDTO;
import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import bg.finalexam.crazydesignsco.model.entity.PictureEntity;
import bg.finalexam.crazydesignsco.model.entity.RoomEntity;
import bg.finalexam.crazydesignsco.model.entity.UserEntity;
import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;
import bg.finalexam.crazydesignsco.model.enums.StyleEnum;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;
import bg.finalexam.crazydesignsco.model.user.DesignCoUserDetails;
import bg.finalexam.crazydesignsco.model.view.DesignDetailsViewModel;
import bg.finalexam.crazydesignsco.model.view.DesignsHighlightViewModel;
import bg.finalexam.crazydesignsco.repository.DesignSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public interface DesignService {

    boolean isOwner(String userName, UUID offerId);

    void deleteDesignById(UUID designId);

    Page<DesignsHighlightViewModel> getAllDesigns(Pageable pageable);

    Page<DesignsHighlightViewModel> getAllDesignsByUser(Pageable pageable, DesignCoUserDetails userDetails);

    Page<DesignsHighlightViewModel> getAllDesignsByRoomTypeOther(Pageable pageable);

    Page<DesignsHighlightViewModel> getAllDesignsByStyleOther(Pageable pageable);

    UpdateDesignDTO getDesignAndEditDetails(UUID designId);

    Optional<DesignDetailsViewModel> findDesignByUUID(UUID designId);

    void addDesign(CreateDesignDTO createDesignDTO, UserDetails userDetails);

    List<DesignsHighlightViewModel> searchDesign(SearchDesignDTO searchDesignDTO);

    void editDesign(UUID designId, UpdateDesignDTO editDesignDTO, UserDetails userDetails);

    List<DesignEntity> getAllDesignEntitiesByUser(DesignCoUserDetails userDetails);

}
