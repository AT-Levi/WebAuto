package uz.pdp.WebAuto.service.impl;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.CarType;
import uz.pdp.WebAuto.repository.CarTypeRepository;
import uz.pdp.WebAuto.service.CarTypeService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
public class CarTypeServiceImp implements CarTypeService {

    final CarTypeRepository carTypeRepository;

    public CarTypeServiceImp(CarTypeRepository carTypeRepository) {
        this.carTypeRepository = carTypeRepository;
    }


    @Override
    public ResponseDTO<CarType> save(CarType carType) {
        CarType saved = carTypeRepository.save(carType);
        return new ResponseDTO<>(true, HttpStatus.OK.value(), saved, "CarType successfully created", null);
    }

    @Override
    public ResponseDTO<CarType> update(Long id, CarType carType) {
        CarType existing = carTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarType not found with id: " + id));
        existing.setName(carType.getName());
        existing.setIcon(carType.getIcon());
        CarType updated = carTypeRepository.save(existing);
        return new ResponseDTO<>(true, HttpStatus.OK.value(), updated, "CarType successfully updated", null);
    }

    @Override
    public ResponseDTO<Void> delete(Long id) {
        CarType existing = carTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarType not found with id: " + id));
        carTypeRepository.delete(existing);
        return new ResponseDTO<>(true, HttpStatus.OK.value(), null, "CarType successfully deleted", null);
    }

    @Override
    public ResponseDTO<CarType> findById(Long id) {
        CarType carType = carTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarType not found with id: " + id));
        return new ResponseDTO<>(true, HttpStatus.OK.value(), carType, "CarType found", null);
    }

    @Override
    public ResponseDTO<List<CarType>> findAll() {
        List<CarType> carTypes = carTypeRepository.findAll();
        return new ResponseDTO<>(true, HttpStatus.OK.value(), carTypes, "List of all CarTypes", null);
    }






    //    public List<CarType> getAllCarTypes() {
//        return carTypeRepository.findAll();
//    }
//
//    public Optional<CarType> getCarTypeById(Long id) {
//        return carTypeRepository.findById(id);
//    }
//
//    public CarType createCarType(CarType carType) {
//        return carTypeRepository.save(carType);
//    }
//
//    public CarType updateCarType(Long id, CarType carTypeDetails) {
//        return carTypeRepository.findById(id)
//                .map(carType -> {
//                    carType.setName(carTypeDetails.getName());
//                    carType.setIcon(carTypeDetails.getIcon());
//                    return carTypeRepository.save(carType);
//                })
//                .orElseThrow(() -> new RuntimeException("Car Type not found"));
//    }
//
//    public void deleteCarType(Long id) {
//        carTypeRepository.deleteById(id);
//    }
}
