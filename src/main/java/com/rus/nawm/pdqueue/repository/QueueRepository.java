package com.rus.nawm.pdqueue.repository;

import com.rus.nawm.pdqueue.domain.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {
  List<Queue> findByCreatedById(Long createdById);
  List<Queue> findBySubjectName(String subjectName);
  List<Queue> findBySubmissionDate(LocalDateTime submissionDate);

  @Query(value = "select q.* from queues q join public.queue_students qs on q.id = qs.queue_id where qs.student_id = ?1", nativeQuery = true)
  List<Queue> findByStudentId(Long userId);

  @Query("select q from Queue q where q.createdBy.id = ?1")
  List<Queue> findByCreatorId(Long creatorId);
}
