package uz.pdp.WebAuto.repository;

import uz.pdp.WebAuto.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Object> findByName(String name);
}
