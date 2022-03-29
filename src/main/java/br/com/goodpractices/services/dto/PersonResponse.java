package br.com.goodpractices.services.dto;

import java.time.LocalDateTime;

public record PersonResponse(Long id, String name, LocalDateTime createdAt) {
}
