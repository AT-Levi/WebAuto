package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.dtos.address.AddressDTO;
import uz.pdp.WebAuto.dtos.address.AddressRequestDTO;
import uz.pdp.WebAuto.entity.Address;

import java.util.Optional;

public interface AddressService {

    Optional<Address> findById(Long id);

    AddressDTO save(AddressRequestDTO dtos);
}
