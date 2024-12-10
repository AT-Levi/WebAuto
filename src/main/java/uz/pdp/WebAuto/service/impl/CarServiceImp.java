package uz.pdp.WebAuto.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dtos.car.CarDTO;
import uz.pdp.WebAuto.dtos.car.CreateCarDTO;
import uz.pdp.WebAuto.dtos.image.ImageRequestDTO;
import uz.pdp.WebAuto.entity.*;
import uz.pdp.WebAuto.enums.FuelType;
import uz.pdp.WebAuto.enums.TransmissionType;
import uz.pdp.WebAuto.mapper.CarMapper;
import uz.pdp.WebAuto.repository.BrandRepository;
import uz.pdp.WebAuto.repository.CarDetailsRepository;
import uz.pdp.WebAuto.repository.CarRepository;
import uz.pdp.WebAuto.repository.CarTypeRepository;
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
    private final CarTypeRepository carTypeRepository;
    private final CarDetailsRepository carDetailsRepository;
    private final ImageServiceImp imageServiceImp;

    @Override
    public CarDTO findById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with id: " + id));
        return carMapper.toDto(car);
    }

    @Override
    @Transactional
    public CarDTO save(CreateCarDTO dto) {
        List<ImageRequestDTO> carImages = dto.getCarImages();
        List<Image> images = imageServiceImp.saveImages(carImages);
        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new IllegalArgumentException("Brand not found with id: " + dto.getBrandId()));
        CarType carType = carTypeRepository.findById(dto.getCarTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Brand not found with id: " + dto.getCarTypeId()));

        Car newCar = Car.builder()
                .brand(brand)
                .carType(carType)
                .carDetails(null)
                .color(dto.getColor())
                .year(dto.getYear())
                .condition(dto.getCondition())
                .description(dto.getDescription())
                .engineType(dto.getEngineType())
                .fuelType(dto.getFuelType())
                .mileage(dto.getMileage())
                .images(images)
                .model(dto.getModel())
                .warranty(dto.getWarranty())
                .transmission(dto.getTransmission())
                .price(dto.getPrice())
                .build();

        return save(newCar);
    }

    @Override
    public CarDTO save(Car newCar) {
        Car savedCar = carRepository.save(newCar);
        return carMapper.toDto(savedCar);
    }

    @Override
    public List<CarDTO> getAll() {
        return carMapper.toDto(carRepository.findAll());
    }


    @Override
    public List<CarDTO> getAllForPage(Pageable pageRequest) {
        List<Car> allCars = carRepository.getCarsForPage(pageRequest);
        return carMapper.toDto(allCars);
    }

    public List<CarDTO> findByBrandId(Long brandId) {
        List<Car> cars = carRepository.findByBrandId(brandId);
        return cars.stream().map(carMapper::toDto).collect(Collectors.toList()).reversed();
    }

    public List<CarDTO> findByFuelType(String fuelTypeStr) {
        FuelType fuelType = FuelType.valueOf(fuelTypeStr);
        List<Car> cars = carRepository.findByFuelType(fuelType);
        return cars.stream().map(carMapper::toDto).collect(Collectors.toList()).reversed();
    }

    public List<CarDTO> findByTransmission(String transmission) {
        TransmissionType transmissionType = TransmissionType.valueOf(transmission);
        List<Car> cars = carRepository.findByTransmission(transmissionType);
        return cars.stream().map(carMapper::toDto).collect(Collectors.toList()).reversed();
    }

    public List<CarDTO> findByYear(int year) {
        List<Car> cars = carRepository.findByYear(year);
        return cars.stream().map(carMapper::toDto).collect(Collectors.toList()).reversed();
    }

    public List<CarDTO> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
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
    public CarDTO update(Car car) {
        return carMapper.toDto(carRepository.save(car));
    }

    @Override
    public Page<CarDTO> findAllForPage(Pageable pageRequest) {
        return null;
    }

}
