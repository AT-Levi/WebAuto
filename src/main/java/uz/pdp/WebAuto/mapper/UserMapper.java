package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;
import uz.pdp.WebAuto.dtos.user.UserResponseDTO;
import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserResponseDTO, User> {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @Override
    User toEntity(UserResponseDTO dto);

    @Override
    UserResponseDTO toDto(User entity);

    @Override
    List<User> toEntity(List<UserResponseDTO> list);

    @Override
    List<UserResponseDTO> toDto(List<User> list);
}
