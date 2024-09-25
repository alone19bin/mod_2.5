package com.maxim.spring_security_rest_api_app.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${s3.accessKey}")
    private String awsAccessKeyId;

    @Value("${s3.secretKey}")
    private String awsSecretAccessKey;

    @Value("${s3.region}")
    private String awsRegion;

    @Value("${s3.bucketName}")
    private String bucketName;

    @Bean
    public AmazonS3 s3client() {
        AWSCredentials credentials = new BasicAWSCredentials(
                awsAccessKeyId,
                awsSecretAccessKey
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.fromName(awsRegion))
                .build();
    }
}
