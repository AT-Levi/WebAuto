package uz.pdp.WebAuto.repository;

import uz.pdp.WebAuto.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {

}
