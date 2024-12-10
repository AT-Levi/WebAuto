package uz.pdp.WebAuto.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.CarType;
import uz.pdp.WebAuto.repository.CarTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarTypeService {

    final CarTypeRepository carTypeRepository;

    public CarTypeService(CarTypeRepository carTypeRepository) {
        this.carTypeRepository = carTypeRepository;
    }

    public List<CarType> getAllCarTypes() {
        return carTypeRepository.findAll();
    }

    public Optional<CarType> getCarTypeById(Long id) {
        return carTypeRepository.findById(id);
    }

    public CarType createCarType(CarType carType) {
        return carTypeRepository.save(carType);
    }

    public CarType updateCarType(Long id, CarType carTypeDetails) {
        return carTypeRepository.findById(id)
                .map(carType -> {
                    carType.setTypeName(carTypeDetails.getTypeName());
                    carType.setIcon(carTypeDetails.getIcon());
                    return carTypeRepository.save(carType);
                })
                .orElseThrow(() -> new RuntimeException("Car Type not found"));
    }

    public void deleteCarType(Long id) {
        carTypeRepository.deleteById(id);
    }
}
