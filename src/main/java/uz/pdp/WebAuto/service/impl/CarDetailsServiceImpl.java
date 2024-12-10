package uz.pdp.WebAuto.service.impl;

import org.springframework.stereotype.Service;

import uz.pdp.WebAuto.dtos.car.CarDetailsDTO;
import uz.pdp.WebAuto.entity.CarDetails;
import uz.pdp.WebAuto.repository.CarDetailsRepository;
import uz.pdp.WebAuto.service.CarDetailsService;

import java.util.List;

@Service
public class CarDetailsServiceImpl implements CarDetailsService {
    private final CarDetailsRepository carDetailsRepository;

    public CarDetailsServiceImpl(CarDetailsRepository carDetailsRepository) {
        this.carDetailsRepository = carDetailsRepository;
    }

    @Override
    public CarDetailsDTO save(CarDetailsDTO carDetailsDTO) {
        CarDetails carDetails = toEntity(carDetailsDTO);
        carDetails = carDetailsRepository.save(carDetails);
        return toDto(carDetails);
    }

    @Override
    public CarDetailsDTO getById(Long id) {
        CarDetails carDetails = carDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarDetails not found"));
        return toDto(carDetails);
    }

    @Override
    public List<CarDetailsDTO> getAll() {
        return carDetailsRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public CarDetailsDTO update(Long id, CarDetailsDTO carDetailsDTO) {
        CarDetails carDetails = carDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarDetails not found"));
        carDetails = toEntity(carDetailsDTO).toBuilder().id(carDetails.getId()).build();
        return toDto(carDetailsRepository.save(carDetails));
    }

    @Override
    public void delete(Long id) {
        if (!carDetailsRepository.existsById(id)) {
            throw new RuntimeException("CarDetails not found");
        }
        carDetailsRepository.deleteById(id);
    }

    private CarDetailsDTO toDto(CarDetails carDetails) {
        return new CarDetailsDTO(
                carDetails.getCarId(),
                carDetails.getLength(),
                carDetails.getHeight(),
                carDetails.getWheelbase(),
                carDetails.getHeightWithRoofRails(),
                carDetails.getWidthWithMirrors(),
                carDetails.getLuggageCapacity(),
                carDetails.getWidth(),
                carDetails.getGrossVehicleWeight(),
                carDetails.getMaxLoadingWeight(),
                carDetails.getMaxRoofLoad(),
                carDetails.getNumberOfSeats(),
                carDetails.getFuelTankCapacity(),
                carDetails.getMaxTowingWeight(),
                carDetails.getMinTowingWeight(),
                carDetails.getMinKerbWeight(),
                carDetails.getTurningCircle(),
                carDetails.getEngineSize(),
                carDetails.getNumberOfDoors(),
                carDetails.getNumberOfCylinder()
        );
    }

    private CarDetails toEntity(CarDetailsDTO carDetailsDTO) {
        return CarDetails.builder()
                .carId(carDetailsDTO.getCarId())
                .length(carDetailsDTO.getLength())
                .height(carDetailsDTO.getHeight())
                .wheelbase(carDetailsDTO.getWheelbase())
                .heightWithRoofRails(carDetailsDTO.getHeightWithRoofRails())
                .widthWithMirrors(carDetailsDTO.getWidthWithMirrors())
                .luggageCapacity(carDetailsDTO.getLuggageCapacity())
                .width(carDetailsDTO.getWidth())
                .GrossVehicleWeight(carDetailsDTO.getGrossVehicleWeight())
                .MaxLoadingWeight(carDetailsDTO.getMaxLoadingWeight())
                .MaxRoofLoad(carDetailsDTO.getMaxRoofLoad())
                .NumberOfSeats(carDetailsDTO.getNumberOfSeats())
                .fuelTankCapacity(carDetailsDTO.getFuelTankCapacity())
                .maxTowingWeight(carDetailsDTO.getMaxTowingWeight())
                .minTowingWeight(carDetailsDTO.getMinTowingWeight())
                .minKerbWeight(carDetailsDTO.getMinKerbWeight())
                .TurningCircle(carDetailsDTO.getTurningCircle())
                .engineSize(carDetailsDTO.getEngineSize())
                .numberOfDoors(carDetailsDTO.getNumberOfDoors())
                .numberOfCylinder(carDetailsDTO.getNumberOfCylinder())
                .build();
    }
}
