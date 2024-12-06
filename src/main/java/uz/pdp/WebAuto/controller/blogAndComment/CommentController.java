package uz.pdp.WebAuto.controller.blogAndComment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.entity.Comment;
import uz.pdp.WebAuto.enums.ObjectType;
import uz.pdp.WebAuto.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{objectId}/{objectType}")
    public List<Comment> getCommentsForObject(
            @PathVariable Long objectId,
            @PathVariable ObjectType objectType) {
        return commentService.getCommentsForObject(objectId, objectType);
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.addComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }
}
