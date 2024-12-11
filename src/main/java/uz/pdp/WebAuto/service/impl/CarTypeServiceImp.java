package uz.pdp.WebAuto.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dtos.carType.CarTypeResponseDTO;
import uz.pdp.WebAuto.dtos.carType.CreateCarTypeDTO;
import uz.pdp.WebAuto.entity.CarType;
import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.mapper.CarTypeMapper;
import uz.pdp.WebAuto.repository.CarTypeRepository;
import uz.pdp.WebAuto.service.CarTypeService;
import uz.pdp.WebAuto.service.ImageService;

import java.util.List;

@Service
public class CarTypeServiceImp implements CarTypeService {

    final CarTypeRepository carTypeRepository;
    private final CarTypeMapper carTypeMapper;
    private final ImageService imageServiceImp;

    public CarTypeServiceImp(CarTypeRepository carTypeRepository, CarTypeMapper carTypeMapper, ImageServiceImp imageServiceImp) {
        this.carTypeRepository = carTypeRepository;
        this.carTypeMapper = carTypeMapper;
        this.imageServiceImp = imageServiceImp;
    }

    @Override
    public CarTypeResponseDTO saveByDto(CreateCarTypeDTO dto) {
        Image icon = imageServiceImp.save(dto.icon());
        CarType carType = CarType.builder()
                .typeName(dto.typeName())
                .icon(icon)
                .cars(List.of())
                .build();

        return save(carType);
    }

    @Override
    public CarTypeResponseDTO update(CarType carType) {
        CarType updatedCarType = carTypeRepository.save(carType);
        return carTypeMapper.toDto(updatedCarType);
    }

    @Override
    public CarTypeResponseDTO save(CarType carType) {
        return carTypeMapper.toDto(carTypeRepository.save(carType));
    }

    @Override
    public void delete(Long id) {
        carTypeRepository.deleteByCarTypeId(id);
    }

    @Override
    public CarTypeResponseDTO findById(Long id) {
        return carTypeMapper.toDto(getById(id));
    }

    @Override
    public CarType getById(Long id) {
        return carTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarType not found with id: " + id));
    }

    @Override
    public List<CarTypeResponseDTO> findAll() {
        return carTypeMapper.toDto(carTypeRepository.findAll());
    }
}
