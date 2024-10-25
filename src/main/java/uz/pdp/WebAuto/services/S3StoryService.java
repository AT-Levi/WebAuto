package uz.pdp.WebAuto.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3StoryService {
    private final AmazonS3 client;
    @Value("${aws.bucket.name}")
    private String bucketName;
    @Value("${public.folder.path}")
    private String publicFolderPath;

    public S3StoryService(AmazonS3 client) {
        this.client = client;
    }


    public String putObject(File file) {
        String objectKey = "public/" + file.getName(); // Указываем путь
        client.putObject(new PutObjectRequest(bucketName, objectKey, file));
        return "File uploaded: " + file.getName();
    }

    public S3Object getObject(String fileName) {
        return this.client.getObject(new GetObjectRequest(this.bucketName, fileName));
    }

    public String deleteObject(String fileName) {
        this.client.deleteObject(new DeleteObjectRequest(this.bucketName, fileName));
        return "File deleted: " + fileName;
    }

    public List<String> listFiles() {
        ObjectListing objectListing = this.client.listObjects(this.bucketName);
        return (List)objectListing.getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    public String getFileUrl(String fileName) {
        String var10000 = this.bucketName;
        return "https://" + var10000 + ".s3." + this.client.getRegionName() + ".amazonaws.com/" + fileName;
    }

    public String myGetFileUrl(String fileName){
        return "https://ap-northeast-1.console.aws.amazon.com/s3/object/burgershoppbucket?region=ap-northeast-1&bucketType=general&prefix=" + fileName;
    }
}



//https://ap-northeast-1.console.aws.amazon.com/s3/object/burgershoppbucket?region=ap-northeast-1&bucketType=general&prefix=1e6da8ac-6392-4150-82fb-007303618345.jpeg10984784400805770608.jpeg