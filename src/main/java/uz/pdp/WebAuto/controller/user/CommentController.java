package uz.pdp.WebAuto.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.comment.CommentDTO;
import uz.pdp.WebAuto.service.CommentService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseDTO<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO savedComment = commentService.save(commentDTO);
        return ResponseDTO.ok(savedComment, "Comment successfully created").getBody();
    }

    @GetMapping("/{id}")
    public ResponseDTO<CommentDTO> getCommentById(@PathVariable Long id) {
        CommentDTO comment = commentService.getById(id);
        return ResponseDTO.ok(comment, "Comment found").getBody();
    }

    @GetMapping("/by-object")
    public ResponseDTO<List<CommentDTO>> getCommentsByObjectTypeAndId(
            @RequestParam String objectType, @RequestParam Long objectId) {
        List<CommentDTO> comments = commentService.getAllByObjectTypeAndObjectId(objectType, objectId);
        return ResponseDTO.ok(comments, "Comments retrieved").getBody();
    }

    @GetMapping
    public ResponseDTO<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.getAll();
        return ResponseDTO.ok(comments, "All comments retrieved").getBody();
    }

    @PutMapping("/{id}")
    public ResponseDTO<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        CommentDTO updatedComment = commentService.update(id, commentDTO);
        return ResponseDTO.ok(updatedComment, "Comment successfully updated").getBody();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Object>> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseDTO.ok(null, "Comment successfully deleted");
    }
}
