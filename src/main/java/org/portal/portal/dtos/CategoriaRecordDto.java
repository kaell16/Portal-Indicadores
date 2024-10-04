package org.portal.portal.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRecordDto(@NotBlank String descricao) {
}
