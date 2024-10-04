package org.portal.portal.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "copo")
@Table(name = "copo")
@Getter
@Setter
@EqualsAndHashCode(of = "idIndicador")
public class CopoModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false, name = "idindicador")
    private Long idIndicador;

    @Column(name = "idcategoria")
    private Long idCategoria;

    private String descricao;

    private double quantidade;

    private String medida;

    private BigDecimal valor;

    @Column(name = "datainicial")
    private LocalDate dataInicial;

    @Column(name = "datafinal")
    private LocalDate dataFinal;

    @Column(updatable = false, insertable = false, name = "dataatualizacao")
    private Timestamp dataAtualizacao;
}
