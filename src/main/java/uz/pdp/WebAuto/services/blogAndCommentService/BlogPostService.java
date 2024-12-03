package uz.pdp.WebAuto.services.blogAndCommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.blog.BlogPost;
import uz.pdp.WebAuto.repository.blogAndCommentRepository.BlogPostRepository;

import java.util.List;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost getPostById(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public BlogPost createPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public BlogPost updatePost(Long id, BlogPost updatedPost) {
        BlogPost existingPost = getPostById(id);
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        return blogPostRepository.save(existingPost);
    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }
   /* // Related Posts method (oxshash postlar uchun, masalan, bir xil kategoriyadagi postlar)
    public List<BlogPost> getRelatedPosts(Long postId) {
        // O'xshash postlarni olish uchun kerakli filtrlash shartlarini qo'shing.
        BlogPost post = getPostById(postId);
        return blogPostRepository.findRelatedPosts(post.getCategory(), postId);
    }*/
}
