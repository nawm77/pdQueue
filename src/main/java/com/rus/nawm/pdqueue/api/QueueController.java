package com.rus.nawm.pdqueue.api;

import com.rus.nawm.pdqueue.domain.Queue;
import com.rus.nawm.pdqueue.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/queues")
public class QueueController {
  private final QueueService queueService;

  @GetMapping("/all")
  public ResponseEntity<List<Queue>> getAllQueues() {
    return ResponseEntity.ok(queueService.getAllQueues());
  }
}
