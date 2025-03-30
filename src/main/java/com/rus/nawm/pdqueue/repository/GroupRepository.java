package com.rus.nawm.pdqueue.repository;

import com.rus.nawm.pdqueue.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByGroupName(String groupName);
}