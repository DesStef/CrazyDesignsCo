package bg.finalexam.crazydesignsco.repository;

import bg.finalexam.crazydesignsco.model.entity.CommentEntity;
import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import bg.finalexam.crazydesignsco.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {

    List<PictureEntity> findAllByDesign(DesignEntity designEntity);

    void deletePictureEntityById(Long id);

    void deletePictureEntitiesByDesign(DesignEntity design);

    PictureEntity findByImageUrl(String imageUrl);
}
