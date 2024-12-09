package uz.pdp.WebAuto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dtos.address.AddressDTO;
import uz.pdp.WebAuto.dtos.address.AddressRequestDTO;
import uz.pdp.WebAuto.entity.Address;
import uz.pdp.WebAuto.mapper.AddressMapper;
import uz.pdp.WebAuto.repository.AddressRepository;
import uz.pdp.WebAuto.service.AddressService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImp implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    @Override
    public Optional<Address> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public AddressDTO save(AddressRequestDTO dto) {

        Address address = addressRepository.save(Address.builder()
                .street(dto.getStreet())
                .number(dto.getNumber())
                .description(dto.getDescription())
                .city(dto.getCity())
                .build());

        return addressMapper.toDto(address);
    }
}
