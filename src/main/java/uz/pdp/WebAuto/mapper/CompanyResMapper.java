package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.pdp.WebAuto.dtos.company.CompanyResponseDTO;
import uz.pdp.WebAuto.entity.Company;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                UserMapper.class,
                AddressMapper.class,
                ImageMapper.class
        })
public interface CompanyResMapper extends EntityMapper<CompanyResponseDTO, Company> {

    @Override
    @Mapping(source = "owner.username", target = "ownerName") // owner.name -> ownerName
    CompanyResponseDTO toDto(Company company);

    @Override
    @Mapping(source = "ownerName", target = "owner.username") // ownerName -> owner.name
    Company toEntity(CompanyResponseDTO dto);

    @Override
    List<CompanyResponseDTO> toDto(List<Company> list);

    @Override
    List<Company> toEntity(List<CompanyResponseDTO> list);
}
