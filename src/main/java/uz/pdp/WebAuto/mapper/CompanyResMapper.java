package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
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
    Company toEntity(CompanyResponseDTO dto);

    @Override
    CompanyResponseDTO toDto(Company company);

    @Override
    List<Company> toEntity(List<CompanyResponseDTO> list);

    @Override
    List<CompanyResponseDTO> toDto(List<Company> list);
}
