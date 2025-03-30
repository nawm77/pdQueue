package com.rus.nawm.pdqueue.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "queue_students")
public class QueueStudent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "queue_id")
  private Queue queue;  // Связь с очередью

  @ManyToOne
  @JoinColumn(name = "student_id")
  private User student;  // Связь с пользователем (студентом)

  private Integer position;  // Позиция студента в очереди

}