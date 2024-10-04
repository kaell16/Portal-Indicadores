package org.portal.portal.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CopoRecordDto(@NotNull Long idIndicador,
                            @NotNull Long idCategoria,
                            @NotBlank String descricao,
                            @NotBlank double quantidade,
                            @NotBlank String medida,
                            BigDecimal valor,
                            @NotBlank LocalDate dataInicial,
                            @NotBlank LocalDate dataFinal,
                            LocalDate dataAtualizacao) {}
