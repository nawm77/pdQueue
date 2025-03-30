package com.rus.nawm.pdqueue.api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class GroupDto {
    private Long id;
    private String groupName;
}