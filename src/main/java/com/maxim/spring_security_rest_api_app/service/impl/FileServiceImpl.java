package com.maxim.spring_security_rest_api_app.service.impl;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.maxim.spring_security_rest_api_app.model.File;
import com.maxim.spring_security_rest_api_app.service.FileService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private final AmazonS3 s3client;


    @Autowired
    public FileServiceImpl(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public void createBucket() {
        String bucketName = "expo600_bucket";

        if (s3client.doesBucketExistV2(bucketName)) {
            log.info("Bucket {} already exists, use a different name", bucketName);
            return;
        }
        s3client.createBucket(bucketName);
    }

    public void listBuckets(){
        List<Bucket> buckets = s3client.listBuckets();
        log.info("buckets: {}", buckets);
    }


    @Override
    @SneakyThrows
    public void upload(File file) {
        String bucketName = "expo600_bucket";
        java.io.File newFile = new java.io.File(file.getLocation());
        file.setCreated(LocalDateTime.now());
        log.info("File Uploaded {}", file.getFileName());

        try{
            s3client.putObject(bucketName, file.getFileName(), newFile);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()),
                    "Request processing failed at cloud platform", e);
        } catch (SdkClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to process your request", e);
        }
    }

    @Override
    @SneakyThrows
    public InputStream download(File file) {
        String bucketName = "expo600_bucket";
        if (s3client.doesObjectExist(bucketName, file.getFileName())) {
            S3Object s3Object = s3client.getObject(bucketName, file.getFileName());
            log.info("File download {}", file.getFileName());
            return s3Object.getObjectContent();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Requested file does not exist on bucket");
        }
    }

    @Override
    public Optional<String> listFiles() {
        String bucketName = "expo600_bucket";
        ObjectListing objectListing = s3client.listObjects(bucketName);
        if (objectListing != null) {
            log.info("All files in the bucket");
            return Optional.of(String.valueOf(objectListing.getObjectSummaries()));
        } else {
            log.info("No file present in bucket");
            return Optional.empty();
        }
    }

    @Override
    public void deleteFile(String fileName) {
        String bucketName = "expo600_bucket";
        s3client.deleteObject(bucketName, fileName);
        log.info("Deleting a File {}", fileName);
    }

}
