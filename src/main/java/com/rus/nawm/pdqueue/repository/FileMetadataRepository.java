package com.rus.nawm.pdqueue.repository;

import com.rus.nawm.pdqueue.domain.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long> {
    List<FileMetadata> findByQueueId(Long queueId);
    Optional<FileMetadata> findByIdAndQueueId(Long fileId, Long queueId);
}