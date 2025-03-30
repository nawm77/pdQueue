package com.rus.nawm.pdqueue.repository;

import com.rus.nawm.pdqueue.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String name);
}