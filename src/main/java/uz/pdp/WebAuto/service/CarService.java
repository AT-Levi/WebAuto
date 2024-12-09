package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.dtos.car.CarRequestDTO;
import uz.pdp.WebAuto.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    CarRequestDTO findById(Long id);

    CarRequestDTO save(CarRequestDTO dto);

    CarRequestDTO save(Car dto);

    List<CarRequestDTO> getAllCars();

    boolean delete(Long id);

    CarRequestDTO update(Long id, CarRequestDTO carRequestDTO);


}
