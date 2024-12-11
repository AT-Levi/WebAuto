package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import uz.pdp.WebAuto.dtos.user.UserDataDTO;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.enums.UserRole;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserDataMapper extends EntityMapper<UserDataDTO, User> {

    User toEntity(UserDataDTO dto);

    UserDataDTO toDto(User entity);

    List<User> toEntity(List<UserDataDTO> list);

    List<UserDataDTO> toDto(List<User> list);

    // List<String> -> Set<Role>
    default Set<Role> mapRoles(List<String> roles) {
        return roles.stream()
                .map(roleName -> Role.builder().name(UserRole.valueOf(roleName)).build())
                .collect(Collectors.toSet());
    }

    // Set<Role> -> List<String>
    default List<String> mapRolesToList(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().name()) // `Role` obyektining nomini oling
                .collect(Collectors.toList());
    }
}