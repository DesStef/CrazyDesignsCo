package bg.finalexam.crazydesignsco.service;

import bg.finalexam.crazydesignsco.model.dto.design.*;
import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import bg.finalexam.crazydesignsco.model.entity.PictureEntity;
import bg.finalexam.crazydesignsco.model.entity.RoomEntity;
import bg.finalexam.crazydesignsco.model.entity.UserEntity;
import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;
import bg.finalexam.crazydesignsco.model.enums.StyleEnum;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;
import bg.finalexam.crazydesignsco.model.mapper.DesignMapper;
import bg.finalexam.crazydesignsco.model.user.DesignCoUserDetails;
import bg.finalexam.crazydesignsco.model.view.DesignDetailsViewModel;
import bg.finalexam.crazydesignsco.model.view.DesignsHighlightViewModel;
import bg.finalexam.crazydesignsco.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DesignService {

    private final DesignRepository designRepository;
    private final UserRepository userRepository;
    private final DesignMapper designMapper;
    private final RoomService roomService;
    private final PictureService pictureService;

    public DesignService(DesignRepository designRepository, UserRepository userRepository,
                         DesignMapper designMapper, RoomService roomService, PictureService pictureService) {
        this.designRepository = designRepository;
        this.userRepository = userRepository;
        this.designMapper = designMapper;
        this.roomService = roomService;
        this.pictureService = pictureService;
    }


    public boolean isOwner(String userName, UUID offerId) {

        boolean isOwner = designRepository.
                findById(offerId).
                filter(o -> o.getCreator().getEmail().equals(userName)).
                isPresent();

        if (isOwner) {
            return true;
        }

        return userRepository.
                findByEmail(userName).
                filter(this::isAdmin).
                isPresent();
    }

    private boolean isAdmin(UserEntity user) {
        return user.getUserRoles().
                stream().
                anyMatch(r -> r.getUserRoleEnum() == UserRoleEnum.ADMIN);
    }

    public void deleteDesignById(UUID designId) {
        DesignEntity designToDelete =
                designRepository.findById(designId).orElseThrow();

        Long roomIdToDelete = designToDelete.getRoom().getId();
        roomService.deleteRoomById(roomIdToDelete);

        designRepository.deleteById(designId);
    }

    public Page<DesignsHighlightViewModel> getAllDesigns(Pageable pageable) {
//        return designRepository.
//                findAll(pageable).
//                map(designMapper::designEntityToDesignDetailDto);

        List<DesignsHighlightViewModel> collect = designRepository.findAll(pageable).stream().map(design -> new DesignsHighlightViewModel(
                design.getId(),
                design.getTitle(),
                design.getRoom().getRoomType(),
                design.getRoom().getSquareMetres(),
                design.getPictures().stream().findFirst().get().getImageUrl(),
                design.getPrice(),
                design.getStyle(),
                design.getDate(),
                design.getCreator().getId())).toList();

        return convertToPage(collect, pageable);
    }

    public Page<DesignsHighlightViewModel> getAllDesignsByUser(Pageable pageable, DesignCoUserDetails userDetails) {

        UserEntity creator = userRepository.findByEmail(userDetails.getUsername()).
                orElseThrow();

        List<DesignsHighlightViewModel> collect = designRepository.findAllByCreator(pageable, creator).stream().map(design -> new DesignsHighlightViewModel(
                design.getId(),
                design.getTitle(),
                design.getRoom().getRoomType(),
                design.getRoom().getSquareMetres(),
                design.getPictures().stream().findFirst().get().getImageUrl(),
                design.getPrice(),
                design.getStyle(),
                design.getDate(),
                design.getCreator().getId())).toList();

        return convertToPage(collect, pageable);
    }

    public Page<DesignsHighlightViewModel> getAllDesignsByRoomTypeOther(Pageable pageable) {

        RoomTypeEnum otherRoom = RoomTypeEnum.OTHER;
//
//        return designRepository.
//                findAllByRoom_RoomType(pageable, otherRoom).
//                map(designMapper::designEntityToDesignDetailDto);

        List<DesignsHighlightViewModel> collect = designRepository.findAllByRoom_RoomType(pageable, otherRoom).stream().map(design -> new DesignsHighlightViewModel(
                design.getId(),
                design.getTitle(),
                design.getRoom().getRoomType(),
                design.getRoom().getSquareMetres(),
                design.getPictures().stream().findFirst().get().getImageUrl(),
                design.getPrice(),
                design.getStyle(),
                design.getDate(),
                design.getCreator().getId())).toList();

        return convertToPage(collect, pageable);
    }

    public Page<DesignsHighlightViewModel> getAllDesignsByStyleOther(Pageable pageable) {

        StyleEnum otherStyle = StyleEnum.OTHER;

//        Page<DesignEntity> allByStyle = designRepository.findAllByStyle(pageable, otherStyle);
//        return designRepository.
//                findAllByStyle(pageable, otherStyle).
//                map(designMapper::designEntityToDesignDetailDto);

        List<DesignsHighlightViewModel> collect = designRepository.findAllByStyle(pageable, otherStyle).stream().map(design -> new DesignsHighlightViewModel(
                design.getId(),
                design.getTitle(),
                design.getRoom().getRoomType(),
                design.getRoom().getSquareMetres(),
                design.getPictures().stream().findFirst().get().getImageUrl(),
                design.getPrice(),
                design.getStyle(),
                design.getDate(),
                design.getCreator().getId())).toList();

        return convertToPage(collect, pageable);
    }

    public UpdateDesignDTO getDesignAndEditDetails(UUID designId) {
        DesignEntity design = designRepository.findById(designId).orElseThrow();
        UpdateDesignDTO updateDesignDTO = designMapper.designEntityToUpdateDesignDto(design);

        List<String> picsUrls = design.getPictures().stream().map(PictureEntity::getImageUrl).toList();

        updateDesignDTO.setImageUrl(picsUrls.get(0));

        if (picsUrls.size() == 2) {
            updateDesignDTO.setImageUrl2(picsUrls.get(1));
        }
        if (picsUrls.size() == 3) {
            updateDesignDTO.setImageUrl2(picsUrls.get(1));
            updateDesignDTO.setImageUrl3(picsUrls.get(2));
        }

        return updateDesignDTO;
    }

    public Optional<DesignDetailsViewModel> findDesignByUUID(UUID designId) {
//        return designRepository.
//                findById(designId).
//                map(designMapper::designEntityToDesignDetailDto);


        return designRepository.findById(designId).map(design -> new DesignDetailsViewModel(
                design.getId(),
                design.getTitle(),
                design.getRoom().getRoomType(),
                design.getRoom().getSquareMetres(),
                design.getPictures().stream().map(PictureEntity::getImageUrl).collect(Collectors.toList()),
                design.getPrice(),
                design.getDescription(),
                design.getStyle(),
                design.getDate(),
                design.getCreator().getId(),
                design.getCreator().getFirstName(),
                design.getCreator().getLastName()
        ));
    }

    public void addDesign(CreateDesignDTO createDesignDTO, UserDetails userDetails) {
        DesignEntity designEntity = designMapper.
                createDesignDtoToDesignEntity(createDesignDTO);

        UserEntity creator = userRepository.findByEmail(userDetails.getUsername()).
                orElseThrow();

        RoomEntity newRoom = roomService.addRoom(createDesignDTO.getRoomType(), createDesignDTO.getSquareMetres());

        designEntity.setCreator(creator);
        designEntity.setRoom(newRoom);
        designEntity.setDate(LocalDate.now());

        designRepository.save(designEntity);

        List<String> allImageUrls = createDesignDTO.getAllImageUrls();
        System.out.println(allImageUrls);
        Set<PictureEntity> pictures = new HashSet<>();

        for (int i = 0; i < allImageUrls.size(); i++) {
            PictureEntity pictureEntity = pictureService.addPicture(allImageUrls.get(i), designEntity);
            pictures.add(pictureEntity);
        }
        System.out.println(pictures);
        designEntity.setPictures(pictures);
    }

    //    TODO: make with DesignHighlightvew
    public List<DesignDetailsDTO> searchDesign(SearchDesignDTO searchDesignDTO) {
        return this.designRepository.findAll(new DesignSpecification(searchDesignDTO)).
                stream().map(designMapper::designEntityToDesignDetailDto).collect(Collectors.toList());
    }

    public void editDesign(UUID designId, UpdateDesignDTO editDesignDTO, UserDetails userDetails) {
        DesignEntity designFromRepo = designRepository.getReferenceById(designId);
        designFromRepo.setDate(LocalDate.now());

        designMapper.updateDesignFromUpdateDesignDto(editDesignDTO, designFromRepo);
        designRepository.save(designFromRepo);

        Set<PictureEntity> fromRepoPictures = designFromRepo.getPictures();
        for (PictureEntity pictureEntity : fromRepoPictures) {
            pictureService.deletePicture(pictureEntity.getId());
        }
        List<String> allImageUrls = editDesignDTO.getAllImageUrls();
        Set<PictureEntity> pictures = new HashSet<>();

        for (int i = 0; i < allImageUrls.size(); i++) {
            PictureEntity pictureEntity = pictureService.addPicture(allImageUrls.get(i), designFromRepo);
            pictures.add(pictureEntity);
        }

        designFromRepo.setPictures(pictures);
    }

    public List<DesignEntity> getAllDesignEntitiesByUser(DesignCoUserDetails userDetails) {
        UserEntity creator = userRepository.findByEmail(userDetails.getUsername()).
                orElseThrow();

        return designRepository.
                findAllByCreator(creator).stream().toList();
    }

    public static <T> Page<T> convertToPage(List<DesignsHighlightViewModel> objectList, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), objectList.size());
        List<T> subList = start >= end ? new ArrayList<>() : (List<T>) objectList.subList(start, end);
        return new PageImpl<>(subList, pageable, objectList.size());
    }
}
