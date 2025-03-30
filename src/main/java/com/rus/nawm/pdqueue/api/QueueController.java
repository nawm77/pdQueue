package com.rus.nawm.pdqueue.api;

import com.rus.nawm.pdqueue.domain.Queue;
import com.rus.nawm.pdqueue.domain.User;
import com.rus.nawm.pdqueue.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/queues")
public class QueueController {
  private final QueueService queueService;

  @GetMapping
  public ResponseEntity<List<Queue>> getAllQueues() {
    return ResponseEntity.ok(queueService.getAllQueues());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Queue> getQueueById(@PathVariable Long id) {
    Optional<Queue> queue = queueService.getQueueById(id);
    return queue.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Queue> createQueue(@RequestBody Queue queue) {
    return ResponseEntity.ok(queueService.createQueue(queue));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Queue> updateQueue(@PathVariable Long id, @RequestBody Queue updatedQueue) {
    return ResponseEntity.ok(queueService.updateQueue(id, updatedQueue));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteQueue(@PathVariable Long id) {
    queueService.deleteQueue(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{queueId}/students")
  public ResponseEntity<Queue> addStudentToQueue(@PathVariable Long queueId, @RequestBody User student) {
    return ResponseEntity.ok(queueService.addStudentToQueue(queueId, student));
  }

  @DeleteMapping("/{queueId}/students/{studentId}")
  public ResponseEntity<Queue> removeStudentFromQueue(@PathVariable Long queueId, @PathVariable Long studentId) {
    User student = new User();
    student.setId(studentId);
    return ResponseEntity.ok(queueService.removeStudentFromQueue(queueId, student));
  }
}