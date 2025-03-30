package com.rus.nawm.pdqueue.api.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@Tag(name = "File API")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@RequestMapping("/api/v1/file")
public interface FileApi {
  @Operation(summary = "Upload file")
  @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file);

  @Operation(summary = "Download file")
  @GetMapping(value = "/download/{fileName}", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
  ResponseEntity<InputStreamResource> downloadFile(@PathVariable("fileName") String fileName);
}
