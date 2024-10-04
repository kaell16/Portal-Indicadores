package org.portal.portal.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tipoResiduo")
@Table(name = "tipoResiduo")
@Getter
@Setter
@EqualsAndHashCode(of = "idTipoResiduo")
public class TipoResiduoModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false, name = "idtiporesiduo")
    private Long idTipoResiduo;

    private String descricao;

    private boolean recuperavel;
}
