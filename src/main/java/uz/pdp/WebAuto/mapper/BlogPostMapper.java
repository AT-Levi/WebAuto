package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import uz.pdp.WebAuto.dtos.blogpost.BlogPostDTO;
import uz.pdp.WebAuto.entity.BlogPost;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public interface BlogPostMapper extends EntityMapper<BlogPostDTO, BlogPost> {

    @Override
    BlogPost toEntity(BlogPostDTO dto);

    @Override
    BlogPostDTO toDto(BlogPost entity);

    @Override
    List<BlogPost> toEntity(List<BlogPostDTO> list);

    @Override
    List<BlogPostDTO> toDto(List<BlogPost> list);

}
