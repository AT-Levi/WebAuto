package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.dtos.carType.CarTypeResponseDTO;
import uz.pdp.WebAuto.dtos.carType.CreateCarTypeDTO;
import uz.pdp.WebAuto.entity.CarType;

import java.util.List;

public interface CarTypeService {

    CarTypeResponseDTO saveByDto(CreateCarTypeDTO dto);

    CarTypeResponseDTO update(CarType carType);

    void delete(Long id);

    CarTypeResponseDTO findById(Long id);

    CarType getById(Long id);

    List<CarTypeResponseDTO> findAll();

    CarTypeResponseDTO save(CarType carType);
}
