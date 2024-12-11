package uz.pdp.WebAuto.controller.user;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
            summary = "Yangi komment yaratish",
            description = "Bu endpoint yangi komment yaratish imkoniyatini beradi. Komentni yaratish uchun kerakli ma'lumotlarni taqdim etish kerak."
    )
    @PostMapping
    public ResponseDTO<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO savedComment = commentService.save(commentDTO);
        return ResponseDTO.ok(savedComment, "Comment successfully created").getBody();
    }

    @Operation(
            summary = "Kommentni ID bo'yicha olish",
            description = "Bu endpoint komentni ID bo'yicha olish imkoniyatini beradi."
    )
    @GetMapping("/{id}")
    public ResponseDTO<CommentDTO> getCommentById(@PathVariable Long id) {
        CommentDTO comment = commentService.getById(id);
        return ResponseDTO.ok(comment, "Comment found").getBody();
    }

    @Operation(
            summary = "Object turi va ID bo'yicha kommentlarni olish",
            description = "Bu endpoint objectType va objectId bo'yicha kommentlarni olish imkoniyatini beradi. Object turi va ID'sini parametrlarga taqdim eting."
    )
    @GetMapping("/by-object")
    public ResponseDTO<List<CommentDTO>> getCommentsByObjectTypeAndId(
            @RequestParam String objectType, @RequestParam Long objectId) {
        List<CommentDTO> comments = commentService.getAllByObjectTypeAndObjectId(objectType, objectId);
        return ResponseDTO.ok(comments, "Comments retrieved").getBody();
    }

    @Operation(
            summary = "Barcha kommentlarni olish",
            description = "Bu endpoint tizimdagi barcha kommentlarni olish imkoniyatini beradi."
    )
    @GetMapping
    public ResponseDTO<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.getAll();
        return ResponseDTO.ok(comments, "All comments retrieved").getBody();
    }

    @Operation(
            summary = "Kommentni yangilash",
            description = "Bu endpoint kommentni yangilash imkoniyatini beradi. Komment ID si va yangilangan ma'lumotlar taqdim etilishi kerak."
    )
    @PutMapping("/{id}")
    public ResponseDTO<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        CommentDTO updatedComment = commentService.update(id, commentDTO);
        return ResponseDTO.ok(updatedComment, "Comment successfully updated").getBody();
    }

    @Operation(
            summary = "Kommentni o'chirish",
            description = "Bu endpoint kommentni tizimdan o'chirish imkoniyatini beradi. Kommentni o'chirish uchun uning ID'si kerak."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Object>> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseDTO.ok(null, "Comment successfully deleted");
    }
}