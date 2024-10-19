package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.pdp.WebAuto.dtos.AuthUserDto;
import uz.pdp.WebAuto.dtos.AuthenticationDto;
import uz.pdp.WebAuto.entity.user.User;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User toEntity(AuthenticationDto authenticationDto);

    User toEntity(AuthUserDto authUserDto);
}
