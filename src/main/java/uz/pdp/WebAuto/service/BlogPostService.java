package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.dtos.blogpost.BlogPostDTO;
import uz.pdp.WebAuto.dtos.blogpost.BlogPostRequestDTO;
import uz.pdp.WebAuto.entity.BlogPost;

import java.util.Optional;

public interface BlogPostService {

    BlogPostDTO save(BlogPostRequestDTO dto);
    Optional<BlogPost> findByOwnerId(Long ownerId);
}
