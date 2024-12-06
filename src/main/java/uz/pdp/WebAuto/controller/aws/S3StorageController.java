package uz.pdp.WebAuto.controller.aws;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.service.S3StorageService;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class S3StorageController {

    private final S3StorageService s3StorageService;

    public S3StorageController(S3StorageService s3StorageService) {
        this.s3StorageService = s3StorageService;
    }

    @PostMapping(value = "/upload",consumes ={"application/json", MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        s3StorageService.uploadFile(file);
        return ResponseEntity.ok("File successfully uploaded");

    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity downloadFile(@PathVariable String fileName) throws IOException {
        InputStream download= s3StorageService.downloadFile(fileName);
        byte[] bytes = download.readAllBytes();
        return ResponseEntity
                .accepted()
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(bytes);
    }


    @DeleteMapping("/delete-file")
    public ResponseEntity<String> deleteFile(
            @RequestParam String fileName) {
        try {
            s3StorageService.deleteFile(fileName);
            return ResponseEntity.ok("File deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to delete file: " + e.getMessage());
        }
    }

}
