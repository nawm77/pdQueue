package com.rus.nawm.pdqueue.service.impl;

import com.rus.nawm.pdqueue.service.MinioFileService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MinioFileServiceImpl implements MinioFileService {
  private final MinioClient minioClient;

  @Value("${minio.bucket-name}")
  private String bucketName;

  public void uploadFile(MultipartFile file) {
    try {
      minioClient.putObject(
              PutObjectArgs.builder()
                      .bucket(bucketName)
                      .object(file.getOriginalFilename())
                      .stream(file.getInputStream(), file.getSize(), -1)
                      .contentType(file.getContentType())
                      .build()
      );
    } catch (Exception e) {
      throw new RuntimeException("Error uploading file to MinIO", e);
    }
  }

  public InputStream downloadFile(String fileName) {
    try {
      return minioClient.getObject(
              GetObjectArgs.builder()
                      .bucket(bucketName)
                      .object(fileName)
                      .build()
      );
    } catch (Exception e) {
      throw new RuntimeException("Error downloading file from MinIO", e);
    }
  }
}
