package bg.finalexam.crazydesignsco.repository;

import bg.finalexam.crazydesignsco.model.entity.CommentEntity;
import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByDesignEntity(DesignEntity designEntity);

}
