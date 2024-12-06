package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import uz.pdp.WebAuto.dtos.address.AddressDTO;
import uz.pdp.WebAuto.entity.Address;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

    @Override
    Address toEntity(AddressDTO dto);

    @Override
    AddressDTO toDto(Address entity);

    @Override
    List<Address> toEntity(List<AddressDTO> list);

    @Override
    List<AddressDTO> toDto(List<Address> list);
}
