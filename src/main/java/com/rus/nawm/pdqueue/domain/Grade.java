package com.rus.nawm.pdqueue.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double grade;
    
    @ManyToOne
    private User student;
    
    @ManyToOne
    private Queue queue;
}