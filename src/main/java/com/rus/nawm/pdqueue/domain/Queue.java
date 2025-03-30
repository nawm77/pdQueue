package com.rus.nawm.pdqueue.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "queues")
public class Queue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String subjectName;
  private LocalDateTime submissionDate;

  @ManyToOne
  private User createdBy;

  @ManyToMany
  @JoinTable(
          name = "queue_students",
          joinColumns = @JoinColumn(name = "queue_id"),
          inverseJoinColumns = @JoinColumn(name = "student_id"))
  private Set<User> students;

  @ManyToMany
  @JoinTable(
          name = "queue_moderators",
          joinColumns = @JoinColumn(name = "queue_id"),
          inverseJoinColumns = @JoinColumn(name = "moderator_id"))
  private Set<User> moderators;
}
