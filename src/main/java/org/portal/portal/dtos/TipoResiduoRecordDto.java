package org.portal.portal.dtos;

import jakarta.validation.constraints.NotNull;

public record TipoResiduoRecordDto(@NotNull String descricao, @NotNull boolean recuperavel) {
}
