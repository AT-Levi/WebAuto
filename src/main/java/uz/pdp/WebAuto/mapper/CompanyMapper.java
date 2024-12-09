package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import uz.pdp.WebAuto.dtos.company.CompanyDataDTO;
import uz.pdp.WebAuto.entity.Company;

import java.util.List;

@Mapper(componentModel = "spring",
uses = {ImageMapper.class, AddressMapper.class})

public interface CompanyMapper extends EntityMapper<CompanyDataDTO, Company> {

    @Override
    Company toEntity(CompanyDataDTO dto);

    @Override
    CompanyDataDTO toDto(Company company);

    @Override
    List<Company> toEntity(List<CompanyDataDTO> list);

    @Override
    List<CompanyDataDTO> toDto(List<Company> list);
}
