package org.portal.portal.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity(name = "campus")
@Table(name = "campus")
@Getter
@Setter
@EqualsAndHashCode(of = "idCampus")
public class CampusModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false, name = "idcampus")
    private Long idCampus;

    private String nome;

    private String cidade;

    private String UF;
}
