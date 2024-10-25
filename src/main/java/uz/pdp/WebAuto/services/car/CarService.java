package uz.pdp.WebAuto.services.car;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.car.Car;
import uz.pdp.WebAuto.repository.car.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car carDetails) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setModel(carDetails.getModel());
                    car.setYear(carDetails.getYear());
                    car.setDescription(carDetails.getDescription());
                    car.setColor(carDetails.getColor());
                    car.setWarranty(carDetails.getWarranty());
                    car.setFuelType(carDetails.getFuelType());
                    car.setTransmission(carDetails.getTransmission());
                    car.setEngineType(carDetails.getEngineType());
                    car.setCarTypeId(carDetails.getCarTypeId());
                    car.setMileage(carDetails.getMileage());
                    car.setCondition(carDetails.getCondition());
                    car.setBrand(carDetails.getBrand());
                    car.setPrice(carDetails.getPrice());
                    car.setImages(carDetails.getImages());
                    return carRepository.save(car);
                })
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
