package com.rus.nawm.pdqueue.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface MinioFileService {
  void uploadFile(MultipartFile file);
  InputStream downloadFile(String fileName);
}
