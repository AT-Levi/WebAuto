package uz.pdp.WebAuto.controllers.blogAndComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.entity.blog.Comment;
import uz.pdp.WebAuto.services.blogAndCommentService.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Muayyan post uchun kommentariyalarni olish
    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    // Kommentariya qo'shish
    @PostMapping("/post/{postId}")
    public Comment addCommentToPost(@PathVariable Long postId, @RequestBody Comment comment) {
        return commentService.addCommentToPost(postId, comment);
    }

    // Kommentariyani yangilash
    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
        return commentService.updateComment(id, updatedComment);
    }

    // Kommentariyani o'chirish
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
