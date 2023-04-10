package bg.finalexam.crazydesignsco.repository;

import bg.finalexam.crazydesignsco.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.email = :email AND u.id <> :id")
    Optional<UserEntity> findByEmailExcludingId(String email, Long id);

    void deleteUserEntityById(Long id);
}
