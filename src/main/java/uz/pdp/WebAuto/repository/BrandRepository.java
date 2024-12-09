package uz.pdp.WebAuto.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.WebAuto.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("UPDATE Brand b SET b.deleted = true WHERE b.id =: id")
    void deleteByBrandId(@Param("id") Long id);
}
