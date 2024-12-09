package uz.pdp.WebAuto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dtos.car.CarRequestDTO;
import uz.pdp.WebAuto.entity.Brand;
import uz.pdp.WebAuto.entity.Car;
import uz.pdp.WebAuto.mapper.CarMapper;
import uz.pdp.WebAuto.repository.BrandRepository;
import uz.pdp.WebAuto.repository.CarRepository;
import uz.pdp.WebAuto.service.CarService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImp implements CarService {

    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final CarMapper carMapper;

    @Override
    public CarRequestDTO findById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with id: " + id));
        return carMapper.toDto(car);
    }

    @Override
    public CarRequestDTO save(CarRequestDTO dto) {
        Long brandId = dto.getBrandId();
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new IllegalArgumentException("Brand not found with id: " + brandId));

        Car car = carMapper.toEntity(dto);
        car.setBrand(brand);

        car = carRepository.save(car);
        return carMapper.toDto(car);
    }

    @Override
    public CarRequestDTO save(Car car) {
        Car savedCar = carRepository.save(car);
        return carMapper.toDto(savedCar);
    }



    @Override
    public List<CarRequestDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(carMapper::toDto).collect(Collectors.toList());
    }


    public List<CarRequestDTO> findByBrandId(Long brandId) {
        List<Car> cars = carRepository.findByBrandId(brandId);
        return cars.stream().map(carMapper::toDto).collect(Collectors.toList()).reversed();
    }


    public List<CarRequestDTO> findByFuelType(String fuelType) {
        List<Car> cars = carRepository.findByFuelType(fuelType);
        return cars.stream().map(carMapper::toDto).collect(Collectors.toList()).reversed();
    }


    public List<CarRequestDTO> findByTransmission(String transmission) {
        List<Car> cars = carRepository.findByTransmission(transmission);
        return cars.stream().map(carMapper::toDto).collect(Collectors.toList()).reversed();
    }


    public List<CarRequestDTO> findByYear(int year) {
        List<Car> cars = carRepository.findByYear(year);
        return cars.stream().map(carMapper::toDto).collect(Collectors.toList()).reversed();
    }


    public List<CarRequestDTO> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Car> cars = carRepository.findByPriceBetween(minPrice, maxPrice);
        return cars.stream().map(carMapper::toDto).collect(Collectors.toList()).reversed();
    }


    @Override
    public boolean delete(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CarRequestDTO update(Long id, CarRequestDTO carRequestDTO) {
        /*return carRepository.findById(id)
                .map(existingCar -> {
                    // Обновляем данные автомобиля
                    existingCar.setModel(carRequestDTO.getModel());
                    existingCar.setYear(carRequestDTO.getYear());
                    existingCar.setDescription(carRequestDTO.getDescription());
                    existingCar.setColor(carRequestDTO.getColor());
                    existingCar.setWarranty(carRequestDTO.getWarranty());
                    existingCar.setFuelType(carRequestDTO.getFuelType());
                    existingCar.setTransmission(carRequestDTO.getTransmission());
                    existingCar.setEngineType(carRequestDTO.getEngineType());
                    existingCar.setCarTypes((List<CarType>) carRequestDTO.getCarTypeId());
                    existingCar.setMileage(carRequestDTO.getMileage());
                    existingCar.setCondition(carRequestDTO.getCondition());
                    existingCar.setPrice(carRequestDTO.getPrice());

                    // Обновляем бренд, если он указан
                    if (carRequestDTO.getBrandId() != null) {
                        Brand brand = brandRepository.findById(carRequestDTO.getBrandDTO().id())
                                .orElseThrow(() -> new RuntimeException("Brand not found"));
                        existingCar.setBrand(brand);
                    }

                    // Обновляем изображения, если они указаны
                    if (carRequestDTO.getCarImages() != null) {
                        existingCar.setImages(carRequestDTO.getImages());
                    }

                    // Сохраняем изменения
                    Car updatedCar = carRepository.save(existingCar);

                    // Конвертируем обратно в DTO и возвращаем
                    return carMapper.toDto(updatedCar);
                })
                .orElseThrow(() -> new RuntimeException("Car with ID " + id + " not found"));*/

        return new CarRequestDTO();
    }


}
