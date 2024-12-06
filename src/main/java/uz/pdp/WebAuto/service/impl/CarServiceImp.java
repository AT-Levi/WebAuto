package uz.pdp.WebAuto.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dtos.car.CarDTO;
import uz.pdp.WebAuto.dtos.car.CarRequestDTO;
import uz.pdp.WebAuto.entity.Brand;
import uz.pdp.WebAuto.entity.Car;
import uz.pdp.WebAuto.repository.CarRepository;
import uz.pdp.WebAuto.service.CarService;

import java.util.List;

@Service
public class CarServiceImp implements CarService {

    final CarRepository carRepository;

    public CarServiceImp(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarDTO findById(Long id) {
        return null;
    }

    @Override
    public CarDTO save(CarRequestDTO dto) {
        return null;
    }

    @Override
    public CarDTO save(Car dto) {
        return null;
    }

    @Override
    public Car findByOwnerId(Long user) {
        return null;
    }

    @Override
    public List<CarDTO> getAllCars() {
        return List.of();
    }
}
