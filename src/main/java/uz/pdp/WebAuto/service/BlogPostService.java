package uz.pdp.WebAuto.service;

import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.BlogPost;
import uz.pdp.WebAuto.repository.BlogPostRepository;

import java.util.List;
import java.util.Optional;


public interface BlogPostService {

    List<BlogPost> getAllPosts();

    Optional<BlogPost> getPostById(Long id);

    BlogPost createPost(BlogPost blogPost);

    void deletePost(Long id);
}

