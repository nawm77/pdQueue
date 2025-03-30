package com.rus.nawm.pdqueue.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class FileMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName; // Имя файла в MinIO
    private String filePath; // Путь или URL файла в MinIO (если необходимо)
    
    @ManyToOne
    private User createdBy;  // Ссылка на пользователя, который загрузил файл

    private LocalDateTime uploadDate;  // Дата загрузки файла

    @ManyToOne
    private Queue queue;  // Очередь, к которой привязан файл (если требуется)
}