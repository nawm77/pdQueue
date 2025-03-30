//package com.rus.nawm.pdqueue.mapper;
//
//import com.rus.nawm.pdqueue.domain.Group;
//import com.rus.nawm.pdqueue.api.dto.GroupCreateDto;
//import com.rus.nawm.pdqueue.api.dto.GroupDto;
//import com.rus.nawm.pdqueue.api.dto.GroupUpdateDto;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.Collectors;
//
//@Component
//public class GroupMapper {
//
//  /**
//   * Преобразует сущность Group в DTO GroupDto.
//   *
//   * @param group Сущность Group.
//   * @return DTO GroupDto.
//   */
//  public GroupDto toDto(Group group) {
//    if (group == null) {
//      return null;
//    }
//
//    return GroupDto.builder()
//            .id(group.getId())
//            .groupName(group.getGroupName())
//            .build();
//  }
//
//  /**
//   * Преобразует DTO GroupCreateDto в сущность Group.
//   *
//   * @param createDto DTO для создания группы.
//   * @return Сущность Group.
//   */
//  public Group toEntity(GroupDto createDto) {
//    if (createDto == null) {
//      return null;
//    }
//
//    Group group = new Group();
//    group.setGroupName(createDto.getGroupName());
//    return group;
//  }
//
//  /**
//   * Обновляет сущность Group на основе DTO GroupUpdateDto.
//   *
//   * @param group      Сущность Group.
//   * @param updateDto  DTO для обновления группы.
//   */
//  public void updateEntity(Group group, GroupUpdateDto updateDto) {
//    if (updateDto == null) {
//      return;
//    }
//
//    if (updateDto.getGroupName() != null) {
//      group.setGroupName(updateDto.getGroupName());
//    }
//  }
//}