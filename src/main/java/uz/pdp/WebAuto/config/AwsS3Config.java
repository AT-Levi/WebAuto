package uz.pdp.WebAuto.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Config {
    @Value("${aws.access.key}")
    private String ACCESS_KEY;
    @Value("${aws.secret.key}")
    private String SECRET_KEY;
    @Value("${aws.bucket.name}")
    private String BUCKET_NAME;
    @Value("${aws.region}")
    private String REGION;

    public AwsS3Config() {
    }

    @Bean
    public AmazonS3 amazonS3() {
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        System.out.println("Creating AmazonS3 client with region: " + REGION);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(REGION)
                .build();
    }


}
