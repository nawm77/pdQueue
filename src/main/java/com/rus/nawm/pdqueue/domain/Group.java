package com.rus.nawm.pdqueue.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String groupName;
    
    @OneToMany(mappedBy = "group")
    private Set<User> users;
}