package com.rus.nawm.pdqueue.api.dto;

import lombok.Builder;

@Builder
public record LoginResponse(String token) {
}
