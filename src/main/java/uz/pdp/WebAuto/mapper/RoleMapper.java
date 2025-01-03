package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.pdp.WebAuto.dtos.role.RoleDTO;
import uz.pdp.WebAuto.entity.Role;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

    @Override
    @Mapping(target = "name", source = "roleName") // RoleDTO -> Role
    Role toEntity(RoleDTO dto);

    @Override
    @Mapping(target = "roleName", source = "name") // Role -> RoleDTO
    RoleDTO toDto(Role entity);

    @Override
    List<Role> toEntity(List<RoleDTO> list);

    @Override
    List<RoleDTO> toDto(List<Role> list);
}
