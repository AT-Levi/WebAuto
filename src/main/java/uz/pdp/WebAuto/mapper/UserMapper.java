package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.pdp.WebAuto.dto.AuthUserDto;
import uz.pdp.WebAuto.dto.AuthenticationDto;
import uz.pdp.WebAuto.entity.user.User;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User toEntity(AuthenticationDto authenticationDto);

    User toEntity(AuthUserDto authUserDto);
}
