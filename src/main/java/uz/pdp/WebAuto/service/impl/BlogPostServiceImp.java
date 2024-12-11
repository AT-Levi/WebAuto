package uz.pdp.WebAuto.service.impl;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.config.service.CurrentUser;
import uz.pdp.WebAuto.dtos.blogpost.BlogPostDTO;
import uz.pdp.WebAuto.dtos.blogpost.BlogPostRequestDTO;
import uz.pdp.WebAuto.dtos.image.ImageRequestDTO;
import uz.pdp.WebAuto.entity.BlogPost;
import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.exception.NotFoundException;
import uz.pdp.WebAuto.mapper.BlogPostMapper;
import uz.pdp.WebAuto.repository.BlogPostRepository;
import uz.pdp.WebAuto.service.BlogPostService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostServiceImp implements BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostMapper blogPostMapper;
    private final CurrentUser currentUser;
    private final ImageServiceImp imageServiceImp;

    public BlogPostServiceImp(BlogPostRepository blogPostRepository, BlogPostMapper blogPostMapper, CurrentUser currentUser, ImageServiceImp imageServiceImp) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostMapper = blogPostMapper;
        this.currentUser = currentUser;
        this.imageServiceImp = imageServiceImp;
    }

    @Override
    public List<BlogPostDTO> getAllPosts() {
        return blogPostMapper.toDto(blogPostRepository.findAll());
    }

    @Override
    public BlogPostDTO getPostById(Long postId) {
        BlogPost blogPost = blogPostRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        return blogPostMapper.toDto(blogPost);
    }

    @Override
    public BlogPostDTO save(BlogPostRequestDTO blogPost) {

        List<Image> imageList = new ArrayList<>();
        for (ImageRequestDTO imageDTO : blogPost.getPicture()) {
            Image image = imageServiceImp.save(imageDTO.getLogo());
            imageList.add(image);
        }

        BlogPost build = BlogPost.builder()
                .date(null)
                .title(blogPost.getTitle())
                .author(currentUser.getCurrentUser())
                .content(blogPost.getContent())
                .pictures(imageList)
                .build();
        return blogPostMapper.toDto(blogPostRepository.save(build));
    }

    @Override
    public void delete(Long id) {
        blogPostRepository.deletePostById(id);
    }

    @Override
    public List<BlogPost> findPostsByOwnerId(Long ownerId) {
        return blogPostRepository.findByAuthorId(ownerId);
    }

    @Override
    public BlogPostDTO update(BlogPostDTO blogPostDTO) {
        BlogPost entity = blogPostMapper.toEntity(blogPostDTO);
        BlogPost save = blogPostRepository.save(entity);
        return blogPostMapper.toDto(save);
    }
}
