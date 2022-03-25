package br.com.goodpratices.services.dto;

import java.time.LocalDateTime;

public record PersonResponse(Long id, String name, LocalDateTime createdAt) {
}
