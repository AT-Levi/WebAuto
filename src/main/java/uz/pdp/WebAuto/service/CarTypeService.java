package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.entity.CarType;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

public interface CarTypeService {

    ResponseDTO<CarType> save(CarType carType);

    ResponseDTO<CarType> update(Long id, CarType carType);

    ResponseDTO<Void> delete(Long id);

    ResponseDTO<CarType> findById(Long id);

    ResponseDTO<List<CarType>> findAll();
}
