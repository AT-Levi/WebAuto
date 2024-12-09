package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;
import uz.pdp.WebAuto.entity.Image;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper extends EntityMapper<ImageResponseDTO, Image> {

    @Override
    Image toEntity(ImageResponseDTO dto);

    @Override
    ImageResponseDTO toDto(Image entity);

    @Override
    List<Image> toEntity(List<ImageResponseDTO> list);

    @Override
    List<ImageResponseDTO> toDto(List<Image> list);
}
