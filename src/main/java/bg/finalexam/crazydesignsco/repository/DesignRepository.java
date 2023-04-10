package bg.finalexam.crazydesignsco.repository;

import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import bg.finalexam.crazydesignsco.model.entity.UserEntity;
import bg.finalexam.crazydesignsco.model.enums.RoomTypeEnum;
import bg.finalexam.crazydesignsco.model.enums.StyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.LongStream;

@Repository
public interface DesignRepository extends
        JpaRepository<DesignEntity, UUID>,
        JpaSpecificationExecutor<DesignEntity> {



    Page<DesignEntity> findAllByCreator(Pageable pageable, UserEntity creator);
    List<DesignEntity> findAllByCreator(UserEntity creator);
    Page<DesignEntity> findAllByRoom_RoomType(Pageable pageable, RoomTypeEnum roomTypeEnum);
    Page<DesignEntity> findAllByStyle(Pageable pageable, StyleEnum styleEnum);


//    Page<DesignEntity> findAll(Pageable pageable, DesignSpecification designSpecification);
}
