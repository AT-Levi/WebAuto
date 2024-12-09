package uz.pdp.WebAuto.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.WebAuto.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Image i SET i.deleted = true WHERE i.id = :id")
    void deleteByImageId(@Param("id") Long id);
}
