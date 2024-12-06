package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.dtos.car.CarDTO;
import uz.pdp.WebAuto.dtos.car.CarRequestDTO;
import uz.pdp.WebAuto.entity.Brand;
import uz.pdp.WebAuto.entity.Car;
import uz.pdp.WebAuto.entity.Service;

import java.util.List;
import java.util.Optional;

public interface CarService {

    CarDTO findById(Long id);

    CarDTO save(CarRequestDTO dto);

    CarDTO save(Car dto);

    Car findByOwnerId(Long user);

    List<CarDTO> getAllCars();

}
