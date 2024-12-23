package uz.pdp.WebAuto.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.comment.CommentDTO;
import uz.pdp.WebAuto.service.CommentService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPER_ADMIN', 'DEALER', 'EMPLOYEE')")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @Operation(
            summary = "Yangi komment yaratish",
            description = "Bu endpoint yangi komment yaratish imkoniyatini beradi. Komentni yaratish uchun kerakli ma'lumotlarni taqdim etish kerak."
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<CommentDTO>> createComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO savedComment = commentService.save(commentDTO);
        return ResponseDTO.ok(savedComment);
    }

    @Operation(
            summary = "Kommentni ID bo'yicha olish",
            description = "Bu endpoint komentni ID bo'yicha olish imkoniyatini beradi."
    )
    @GetMapping("get/{id}")
    public ResponseEntity<ResponseDTO<CommentDTO>> getCommentById(@PathVariable Long id) {
        CommentDTO comment = commentService.getById(id);
        return ResponseDTO.ok(comment);
    }

    @Operation(
            summary = "Object turi va ID bo'yicha kommentlarni olish",
            description = "Bu endpoint objectType va objectId bo'yicha kommentlarni olish imkoniyatini beradi. Object turi va ID'sini parametrlarga taqdim eting."
    )
    @GetMapping("/get/by-object")
    public ResponseEntity<ResponseDTO<List<CommentDTO>>> getCommentsByObjectTypeAndId(
            @RequestParam String objectType, @RequestParam Long objectId) {
        List<CommentDTO> comments = commentService.getAllByObjectTypeAndObjectId(objectType, objectId);
        return ResponseDTO.ok(comments);
    }

    @Operation(
            summary = "Barcha kommentlarni olish",
            description = "Bu endpoint tizimdagi barcha kommentlarni olish imkoniyatini beradi."
    )
    @GetMapping("get-all")
    public ResponseEntity<ResponseDTO<List<CommentDTO>>> getAllComments() {
        List<CommentDTO> comments = commentService.getAll();
        return ResponseDTO.ok(comments);
    }

    @Operation(
            summary = "Kommentni yangilash",
            description = "Bu endpoint kommentni yangilash imkoniyatini beradi. Komment ID si va yangilangan ma'lumotlar taqdim etilishi kerak."
    )
    @PutMapping("update/{id}")
    public ResponseDTO<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        CommentDTO updatedComment = commentService.update(id, commentDTO);
        return ResponseDTO.ok(updatedComment, "Comment successfully updated").getBody();
    }

    @Operation(
            summary = "Kommentni o'chirish",
            description = "Bu endpoint kommentni tizimdan o'chirish imkoniyatini beradi. Kommentni o'chirish uchun uning ID'si kerak."
    )
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteComment(@PathVariable("id") Long commentId) {
        commentService.delete(commentId);
        return ResponseDTO.ok("Comment is deleted By id: " + commentId);
    }
}