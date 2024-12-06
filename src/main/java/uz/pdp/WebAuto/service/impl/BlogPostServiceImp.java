package uz.pdp.WebAuto.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dtos.blogpost.BlogPostDTO;
import uz.pdp.WebAuto.dtos.blogpost.BlogPostRequestDTO;
import uz.pdp.WebAuto.entity.BlogPost;
import uz.pdp.WebAuto.repository.BlogPostRepository;
import uz.pdp.WebAuto.service.BlogPostService;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostServiceImp implements BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public BlogPostServiceImp(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> getPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost createPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }

    public BlogPostDTO save(BlogPostRequestDTO dto) {
        return null;
    }

    public Optional<BlogPost> findByOwnerId(Long ownerId) {
        return Optional.empty();
    }
}
