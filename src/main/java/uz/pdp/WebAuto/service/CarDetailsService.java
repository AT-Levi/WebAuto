package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.entity.CarDetails;

import java.util.Optional;

public interface CarDetailsService {

    Optional<CarDetails> findByCarId(Long carId);

}
