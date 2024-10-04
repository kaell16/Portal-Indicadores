package org.portal.portal.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "categoria")
@Table(name = "categoria")
@Getter
@Setter
@EqualsAndHashCode(of = "idCategoria")
public class CategoriaModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false, name = "idcategoria")
    private Long idCategoria;

    private String descricao;
}
