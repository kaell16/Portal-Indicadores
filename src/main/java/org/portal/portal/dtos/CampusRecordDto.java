package org.portal.portal.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CampusRecordDto(@NotBlank String nome, @NotNull String cidade, @NotNull String UF) {
}
