package com.rus.nawm.pdqueue.service.impl;

import com.rus.nawm.pdqueue.domain.Queue;
import com.rus.nawm.pdqueue.repository.QueueRepository;
import com.rus.nawm.pdqueue.service.QueueService;
import com.rus.nawm.pdqueue.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class QueueServiceImpl implements QueueService {
  private final QueueRepository queueRepository;
  private final UserService userService;

  public List<Queue> getAllQueues() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();
    Long id = userService.getUserByUserName(username).getId();
    List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .toList();

    if (roles.contains("ROLE_STUDENT")) {
      return queueRepository.findByStudentId(id);

    } else if (roles.contains("ROLE_TEACHER")) {
      return queueRepository.findByCreatorId(id);

    } else if (roles.contains("ROLE_ADMIN")) {
      return queueRepository.findAll();

    } else {
      throw new AccessDeniedException("Недостаточно прав для доступа к очередям");
    }
  }
}
