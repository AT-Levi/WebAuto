package uz.pdp.WebAuto.service;

import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.repository.car.CarRepository;
import uz.pdp.WebAuto.service.imps.CarService;

@Service
public class CarServiceImp implements CarService {

    final CarRepository carRepository;

    public CarServiceImp(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
}
