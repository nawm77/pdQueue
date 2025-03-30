package com.rus.nawm.pdqueue.service.impl;

import com.rus.nawm.pdqueue.service.MinioFileService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
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

  @Value("${minio.endpoint}")
  private String minioEndpoint; // Добавим, чтобы формировать URL

  @Override
  public String uploadFile(MultipartFile file) {
    try {
      String fileName = file.getOriginalFilename();
      minioClient.putObject(
              PutObjectArgs.builder()
                      .bucket(bucketName)
                      .object(fileName)
                      .stream(file.getInputStream(), file.getSize(), -1)
                      .contentType(file.getContentType())
                      .build()
      );
      return minioEndpoint + "/" + bucketName + "/" + fileName; // Формируем URL
    } catch (Exception e) {
      throw new RuntimeException("Error uploading file to MinIO", e);
    }
  }

  @Override
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

  @Override
  public void deleteFile(String fileName) {
    try {
      minioClient.removeObject(
              RemoveObjectArgs.builder()
                      .bucket(bucketName)
                      .object(fileName)
                      .build()
      );
    } catch (Exception e) {
      throw new RuntimeException("Error deleting file from MinIO", e);
    }
  }
}