package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.WebAuto.dtos.*;
import uz.pdp.WebAuto.entity.user.AuthUser;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    AuthUser toEntity(AuthenticationDTO authenticationDto);

    AuthUser toEntity(AuthUserDTO authUserDto);

    UserDataDTO toDto(AuthUser user);

}
