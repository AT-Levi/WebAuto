package uz.pdp.WebAuto.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class S3StorageService {


    private final AmazonS3 client;
    private final String bucketName = "mywebauto";

    public S3StorageService(AmazonS3 amazonS3) {
        this.client = amazonS3;
    }

    public void uploadFile(MultipartFile file) {

        try {
            String originalFilename = file.getOriginalFilename();
            File file1 = convertMulitiPartoFile(file);
            client.putObject(bucketName, originalFilename, file1);
        }catch(AmazonServiceException e){
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private File convertMulitiPartoFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public InputStream downloadFile(String fileName) {
        S3Object o = client.getObject(bucketName, fileName);
        S3ObjectInputStream objectInputStream  = o.getObjectContent();
        return objectInputStream;
    }




    public void deleteFile( String fileName) {
        client.deleteObject(bucketName, fileName);
    }
}

