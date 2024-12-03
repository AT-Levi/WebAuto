package uz.pdp.WebAuto.controllers.blogAndComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.entity.blog.BlogPost;
import uz.pdp.WebAuto.services.blogAndCommentService.BlogPostService;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    // Barcha blog postlarni o'qish (Blog v1 va Blog v2 uchun)
    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostService.getAllPosts();
    }

    // Blog post detallari (Blog details sahifasi uchun)
    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable Long id) {
        BlogPost blogPost = blogPostService.getPostById(id);
        return ResponseEntity.ok(blogPost);
    }

    // Blog post qo'shish
    @PostMapping
    public BlogPost createPost(@RequestBody BlogPost blogPost) {
        return blogPostService.createPost(blogPost);
    }

    // Blog postni yangilash
    @PutMapping("/{id}")
    public BlogPost updatePost(@PathVariable Long id, @RequestBody BlogPost updatedPost) {
        return blogPostService.updatePost(id, updatedPost);
    }

    // Blog postni o'chirish
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        blogPostService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
    // O'xshash postlarni olish (Related Posts uchun)
   /* @GetMapping("/{id}/related")
    public List<BlogPost> getRelatedPosts(@PathVariable Long id) {
        return blogPostService.getRelatedPosts(id);
    }*/
}

