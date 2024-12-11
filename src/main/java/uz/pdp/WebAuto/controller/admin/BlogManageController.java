package uz.pdp.WebAuto.controller.admin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.blogpost.BlogPostDTO;
import uz.pdp.WebAuto.dtos.blogpost.BlogPostRequestDTO;
import uz.pdp.WebAuto.service.BlogPostService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/admin/blog")
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN', 'DEALER')")
@RequiredArgsConstructor
public class BlogManageController {

    private final BlogPostService service;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<BlogPostDTO>> getBrandById(@PathVariable("id") Long postId) {
        return ResponseDTO.ok(service.getPostById(postId));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<BlogPostDTO>> createBrand(@RequestBody BlogPostRequestDTO postDTO) {
        BlogPostDTO save = service.save(postDTO);
        return ResponseDTO.ok(save);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO<BlogPostDTO>> updateBrand(@RequestBody BlogPostDTO blogPostDTO) {
        BlogPostDTO update = service.update(blogPostDTO);
        return ResponseDTO.ok(update);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteBrand(@PathVariable Long id) {
        service.delete(id);
        return ResponseDTO.ok("BlogPost successfully deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<BlogPostDTO>>> getAllBlogPosts() {
        List<BlogPostDTO> allPosts = service.getAllPosts();
        return ResponseDTO.ok(allPosts);
    }
}
