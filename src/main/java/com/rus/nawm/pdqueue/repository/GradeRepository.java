package com.rus.nawm.pdqueue.repository;

import com.rus.nawm.pdqueue.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    // Дополнительные методы, если нужно
    List<Grade> findByStudentId(Long studentId);
    List<Grade> findByQueueId(Long queueId);
}