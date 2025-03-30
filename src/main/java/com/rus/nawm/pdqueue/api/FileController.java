package com.rus.nawm.pdqueue.api;

import com.rus.nawm.pdqueue.service.MinioFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {
  private final MinioFileService fileService;

  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    fileService.uploadFile(file);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/download/{fileName}")
  public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) {
    InputStream fileStream = fileService.downloadFile(fileName);
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
            .body(new InputStreamResource(fileStream));
  }
}
