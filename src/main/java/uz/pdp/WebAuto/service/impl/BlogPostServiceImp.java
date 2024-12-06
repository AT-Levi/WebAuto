package uz.pdp.WebAuto.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dtos.blogpost.BlogPostDTO;
import uz.pdp.WebAuto.dtos.blogpost.BlogPostRequestDTO;
import uz.pdp.WebAuto.entity.BlogPost;
import uz.pdp.WebAuto.service.BlogPostService;

import java.util.Optional;

@Service
public class BlogPostServiceImp implements BlogPostService {
    @Override
    public BlogPostDTO save(BlogPostRequestDTO dto) {
        return null;
    }

    @Override
    public Optional<BlogPost> findByOwnerId(Long ownerId) {
        return Optional.empty();
    }
}
