package bg.finalexam.crazydesignsco.util;

import bg.finalexam.crazydesignsco.model.entity.*;
import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;
import bg.finalexam.crazydesignsco.model.enums.StyleEnum;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;
import bg.finalexam.crazydesignsco.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TestDataUtils {
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private DesignRepository designRepository;
    private RoomRepository roomRepository;
    private PictureRepository pictureRepository;

    public TestDataUtils(UserRepository userRepository,
                         UserRoleRepository userRoleRepository,
                         DesignRepository designRepository,
                         RoomRepository roomRepository,
                         PictureRepository pictureRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.designRepository = designRepository;
        this.roomRepository = roomRepository;
        this.pictureRepository = pictureRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRoleEnum(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setUserRoleEnum(UserRoleEnum.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    @Transactional
    public UserEntity createTestAdmin(String email) {

        initRoles();

        var admin = new UserEntity().
                setEmail(email).
                setPassword("57e7759fd2d59275fc3c3cd5dd2ace5013b39ee972999412f3f5f5c3382b6765c2571ef86648abe2").
                setFirstName("Admin1").
                setLastName("Adminov").
                setPhone("12345678").
                setActive(true).
                setWantDeletion(false).
                setImageUrl("http://image.png").
                setUserRoles(userRoleRepository.findAll());

        return userRepository.save(admin);
    }

    @Transactional
    public UserEntity createTestUser(String email) {

        initRoles();

        var user = new UserEntity().
                setEmail(email).
                setPassword("57e7759fd2d59275fc3c3cd5dd2ace5013b39ee972999412f3f5f5c3382b6765c2571ef86648abe2").
                setFirstName("User1").
                setLastName("Userov").
                setPhone("12345678").
                setActive(true).
                setWantDeletion(false).
                setImageUrl("http://image.png").
                setUserRoles(userRoleRepository.
                        findAll().stream().
                        filter(r -> r.getUserRoleEnum() != UserRoleEnum.ADMIN).
                        toList());

        return userRepository.save(user);
    }

    @Transactional
    public DesignEntity createTestDesign(UserEntity creator) {

        DesignEntity designEntity = new DesignEntity();

        PictureEntity pic1 = new PictureEntity();
        pic1.setImageUrl("https://www.decorilla.com/online-decorating/wp-content/uploads/2020/01/bohemian-interior-design-feature.jpg");
        pic1.setDesign(designEntity);

        Set<PictureEntity> pictureEntities = new HashSet<>();
        pictureEntities.add(pic1);

        RoomEntity roomEntity = new RoomEntity().
                setRoomType(RoomTypeEnum.BATHROOM).
                setSquareMetres(30);

        roomRepository.save(roomEntity);

//        List<CommentEntity> commentEntities = new ArrayList<>();
//        CommentEntity testComment = new CommentEntity();
//        testComment.setUserComment("test comment");
//        testComment.setDesign(designEntity);
//        testComment.setUser(creator);

//        commentEntities.add(testComment);

        designEntity.
                setTitle("Test title").
                setRoom(roomEntity).
                setPictures(pictureEntities).
                setPrice(BigDecimal.TEN).
                setDescription("Test description").
                setStyle(StyleEnum.BOHEMIAN).
                setDate(LocalDate.now()).
                setCreator(creator);

        return designRepository.save(designEntity);
    }


    public PictureEntity createTestPicture(DesignEntity designEntity) {
        PictureEntity pictureEntity = new PictureEntity().
                setImageUrl("image://test.png").
                setDesign(designEntity);

        return pictureRepository.save(pictureEntity);
    }

    public void cleanUpDatabase() {
        designRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        roomRepository.deleteAll();
        pictureRepository.deleteAll();
    }


}
