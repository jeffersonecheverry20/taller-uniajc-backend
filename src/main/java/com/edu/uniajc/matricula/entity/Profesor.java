package com.edu.uniajc.matricula.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Profesor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "codigo", nullable = false)
    private String codigoProfesor;

    @OneToOne
    @JoinColumn(name = "authority", referencedColumnName = "id", nullable = false)
    private Authority authority;

    @JsonBackReference
    @OneToMany(mappedBy = "nota")
    private Set<MatriculaAcademica> matriculaAcademicas;


}
