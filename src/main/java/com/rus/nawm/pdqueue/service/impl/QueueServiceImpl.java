package com.rus.nawm.pdqueue.service.impl;

import com.rus.nawm.pdqueue.domain.FileMetadata;
import com.rus.nawm.pdqueue.domain.Queue;
import com.rus.nawm.pdqueue.domain.User;
import com.rus.nawm.pdqueue.repository.FileMetadataRepository;
import com.rus.nawm.pdqueue.repository.QueueRepository;
import com.rus.nawm.pdqueue.repository.UserRepository;
import com.rus.nawm.pdqueue.service.MinioFileService;
import com.rus.nawm.pdqueue.service.QueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Log4j2
@RequiredArgsConstructor
public class QueueServiceImpl implements QueueService {
  private final QueueRepository queueRepository;
  private final UserRepository userRepository;
  private final MinioFileService fileService;
  private final FileMetadataRepository fileMetadataRepository;

  @Override
  public List<Queue> getAllQueues() {
    return queueRepository.findAll();
  }

  @Override
  public Optional<Queue> getQueueById(Long id) {
    return queueRepository.findById(id);
  }

  @Override
  public Queue createQueue(Queue queue) {
    return queueRepository.save(queue);
  }

  @Override
  public Queue updateQueue(Long id, Queue updatedQueue) {
    return queueRepository.findById(id)
            .map(existingQueue -> {
              existingQueue.setSubjectName(updatedQueue.getSubjectName());
              existingQueue.setSubmissionDate(updatedQueue.getSubmissionDate());
              existingQueue.setStudents(updatedQueue.getStudents());
              existingQueue.setModerators(updatedQueue.getModerators());
              return queueRepository.save(existingQueue);
            })
            .orElseThrow(() -> new IllegalArgumentException("Queue not found"));
  }

  @Override
  public void deleteQueue(Long id) {
    queueRepository.deleteById(id);
  }

  @Override
  public Queue addStudentToQueue(Long queueId, User student) {
    return queueRepository.findById(queueId)
            .map(queue -> {
              Set<User> students = queue.getStudents();
              students.add(student);
              queue.setStudents(students);
              return queueRepository.save(queue);
            })
            .orElseThrow(() -> new IllegalArgumentException("Queue not found"));
  }

  @Override
  public Queue removeStudentFromQueue(Long queueId, User student) {
    return queueRepository.findById(queueId)
            .map(queue -> {
              Set<User> students = queue.getStudents();
              students.remove(student);
              queue.setStudents(students);
              return queueRepository.save(queue);
            })
            .orElseThrow(() -> new IllegalArgumentException("Queue not found"));
  }

  @Override
  public FileMetadata uploadFileToQueue(Long queueId, MultipartFile file, Long userId) {
    Queue queue = queueRepository.findById(queueId)
            .orElseThrow(() -> new IllegalArgumentException("Очередь не найдена"));

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

    String fileUrl = fileService.uploadFile(file); // Загружаем в MinIO

    FileMetadata fileMetadata = new FileMetadata();
    fileMetadata.setQueue(queue);
    fileMetadata.setCreatedBy(user);
    fileMetadata.setFileName(file.getOriginalFilename());
    fileMetadata.setFilePath(fileUrl);
    fileMetadata.setUploadDate(LocalDateTime.now());

    return fileMetadataRepository.save(fileMetadata);
  }

  @Override
  public List<FileMetadata> getQueueFiles(Long queueId) {
    return fileMetadataRepository.findByQueueId(queueId);
  }

  @Override
  public void deleteFileFromQueue(Long queueId, Long fileId) {
    FileMetadata file = fileMetadataRepository.findByIdAndQueueId(fileId, queueId)
            .orElseThrow(() -> new IllegalArgumentException("Файл не найден в очереди"));

    fileService.deleteFile(file.getFileName()); // Удаляем из MinIO
    fileMetadataRepository.delete(file); // Удаляем из БД
  }
}