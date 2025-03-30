package com.rus.nawm.pdqueue.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;

public interface MinioFileService {
  String uploadFile(MultipartFile file); // Возвращает URL загруженного файла
  InputStream downloadFile(String fileName);
  void deleteFile(String fileName);
}