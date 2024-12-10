package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.dtos.car.CarDetailsDTO;
import uz.pdp.WebAuto.entity.CarDetails;

import java.util.List;
import java.util.Optional;

public interface CarDetailsService {

    CarDetailsDTO save(CarDetailsDTO carDetailsDTO);

    CarDetailsDTO getById(Long id);

    List<CarDetailsDTO> getAll();

    CarDetailsDTO update(Long id, CarDetailsDTO carDetailsDTO);

    void delete(Long id);
}
