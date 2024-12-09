package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.pdp.WebAuto.dtos.role.RoleDTO;
import uz.pdp.WebAuto.dtos.user.UserResponseDTO;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.entity.User;

import java.util.List;
import java.util.Set;

@Mapper(
        componentModel = "spring",
        uses = {RoleMapper.class}
)
public interface UserMapper extends EntityMapper<UserResponseDTO, User> {

    @Override
    User toEntity(UserResponseDTO dto);

    @Override
    @Mapping(target = "roles", source = "roles")
    UserResponseDTO toDto(User entity);

    @Override
    List<User> toEntity(List<UserResponseDTO> list);

    @Override
    List<UserResponseDTO> toDto(List<User> list);

    Set<Role> mapRoleDTOsToRoles(Set<RoleDTO> roleDTOs);

    Set<RoleDTO> mapRolesToRoleDTOs(Set<Role> roles);
}
