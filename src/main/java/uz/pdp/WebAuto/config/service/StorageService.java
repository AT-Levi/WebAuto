package uz.pdp.WebAuto.config.service;

import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.*;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@Log4j2
public class StorageService {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket.name}")
    private String bucketName;

    private final S3Client client;

    public StorageService(S3Client client) {
        this.client = client;
    }

    public String uploadFile(MultipartFile file, String folder, String fileName) {
        File fileObj = convertMultipartFileToFile(file);
        String fullPath = folder + "/" + fileName;
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fullPath)
                    .build();
            client.putObject(putObjectRequest, Paths.get(Objects.requireNonNull(fileObj).getPath()));
            log.info("File uploaded: {}", fullPath);

            String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fullPath);
            return fileUrl;
        } catch (S3Exception e) {
            log.error("Error uploading file: {}", e.awsErrorDetails().errorMessage());
            return "File upload failed: " + e.awsErrorDetails().errorMessage();
        }
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting MultipartFile to File; {}", e.getMessage());
        }
        return convertedFile;
    }

    public byte[] downloadFile(String fileName) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();
            return client.getObject(getObjectRequest, ResponseTransformer.toBytes()).asByteArray();

        } catch (S3Exception e) {
            log.error("Error downloading file from S3: {}", e.awsErrorDetails().errorMessage());
            return null;
        }
    }

    public void deleteFile(String fileName) {
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();
            client.deleteObject(deleteObjectRequest);

            log.info("File deleted: {}", fileName);
        } catch (S3Exception e) {
            log.error("Error deleting file from S3: {}", e.awsErrorDetails().errorMessage());
        }
    }
}