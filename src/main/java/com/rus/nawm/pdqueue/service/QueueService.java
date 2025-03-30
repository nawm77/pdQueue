package com.rus.nawm.pdqueue.service;

import com.rus.nawm.pdqueue.domain.FileMetadata;
import com.rus.nawm.pdqueue.domain.Queue;
import com.rus.nawm.pdqueue.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface QueueService {
  List<Queue> getAllQueues();
  Optional<Queue> getQueueById(Long id);
  Queue createQueue(Queue queue);
  Queue updateQueue(Long id, Queue updatedQueue);
  void deleteQueue(Long id);
  Queue addStudentToQueue(Long queueId, User student);
  Queue removeStudentFromQueue(Long queueId, User student);
  List<FileMetadata> getQueueFiles(Long queueId);
  FileMetadata uploadFileToQueue(Long queueId, MultipartFile file, Long userId);
  void deleteFileFromQueue(Long queueId, Long fileId);
}