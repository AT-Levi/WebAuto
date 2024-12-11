package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.dtos.blogpost.BlogPostDTO;
import uz.pdp.WebAuto.dtos.blogpost.BlogPostRequestDTO;
import uz.pdp.WebAuto.dtos.brand.BrandDTO;
import uz.pdp.WebAuto.entity.BlogPost;

import java.util.List;


public interface BlogPostService {

    List<BlogPostDTO> getAllPosts();

    BlogPostDTO getPostById(Long id);

    BlogPostDTO save(BlogPostRequestDTO blogPost);

    void delete(Long id);

    List<BlogPost> findPostsByOwnerId(Long ownerId);

    BlogPostDTO update(BlogPostDTO blogPostDTO);
}

