package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import uz.pdp.WebAuto.dtos.image.ImageRequestDTO;
import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.image.ImageDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper extends EntityMapper<ImageDTO, Image> {

    @Override
    Image toEntity(ImageDTO dto);

    @Override
    ImageDTO toDto(Image entity);

    @Override
    List<Image> toEntity(List<ImageDTO> list);

    @Override
    List<ImageDTO> toDto(List<Image> list);
}
