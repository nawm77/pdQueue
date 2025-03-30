package com.rus.nawm.pdqueue.configuration;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MinioConfiguration {

  @Value("${minio.url}")
  private String minioUrl;

  @Value("${minio.access-key}")
  private String minioAccessKey;

  @Value("${minio.secret-key}")
  private String minioSecretKey;

  @Value("${minio.bucket-name}")
  private String bucketName;

  @Bean
  public MinioClient minioClient() {
    MinioClient minioClient = MinioClient.builder()
            .endpoint(minioUrl)
            .credentials(minioAccessKey, minioSecretKey)
            .build();

    try {
      boolean isBucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
      if (!isBucketExists) {
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        log.info("Bucket <<{}>> created successfully.", bucketName);
      } else {
        log.info("Bucket <<{}>> already exists.", bucketName);
      }
    } catch (Exception e) {
      throw new RuntimeException("Error checking or creating bucket in MinIO", e);
    }

    return minioClient;
  }
}
