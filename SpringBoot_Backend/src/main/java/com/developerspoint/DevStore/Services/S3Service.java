package com.developerspoint.DevStore.Services;

import com.developerspoint.DevStore.AppMetaData.AppMetaData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.ByteBuffer;

@Service
public class S3Service {
    private final S3Client s3Client;
    private final AppMetadataService appMetaDataService;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3Service(@Value("${aws.access-key}") String accessKey,
                     @Value("${aws.secret-key}") String secretKey,
                     @Value("${aws.region}") String region,
                     AppMetadataService appMetaDataService) {
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
        this.appMetaDataService = appMetaDataService;
    }

    public String uploadFile(MultipartFile file, MultipartFile icon, String name, String version,
                             String description, String company, String developerName) throws IOException {
        String filename = "apks/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String iconFilename = "icons/" + System.currentTimeMillis() + "_" + icon.getOriginalFilename();

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(filename)
                        .build(),
                RequestBody.fromByteBuffer(ByteBuffer.wrap(file.getBytes()))
        );

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(iconFilename)
                        .build(),
                RequestBody.fromByteBuffer(ByteBuffer.wrap(icon.getBytes()))
        );

        String apkUrl = "https://" + bucketName + ".s3.amazonaws.com/" + filename;
        String iconUrl = "https://" + bucketName + ".s3.amazonaws.com/" + iconFilename;

        // Save metadata to MySQL
        AppMetaData appMetaData = new AppMetaData(name, version, description, iconUrl, apkUrl, company, developerName);
        appMetaDataService.saveAppMetadata(appMetaData);

        return apkUrl;
    }
}
