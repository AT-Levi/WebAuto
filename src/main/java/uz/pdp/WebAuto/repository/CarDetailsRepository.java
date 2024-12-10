package uz.pdp.WebAuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.WebAuto.entity.CarDetails;

public interface CarDetailsRepository extends JpaRepository<CarDetails, Long> {
}
