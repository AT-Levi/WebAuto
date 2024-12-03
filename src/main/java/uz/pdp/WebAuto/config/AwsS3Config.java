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

    @Value("${server.spring.cloud.aws.credentials.access-key}")
    private String ACCESS_KEY;

    @Value("${server.spring.cloud.aws.credentials.secret-key}")
    private String SECRET_KEY;

    @Value("${server.spring.cloud.aws.s3.bucket}")
    private String BUCKET_NAME;

    @Value("${server.spring.cloud.aws.region.static}")
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
