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
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "codigo_estudiante")
    private String codigoEstudiante;

    @Column(name = "creditos_aprobados", nullable = false)
    private int creditosAprobados;

    @Column(name = "semestre_actual", nullable = false)
    private String semestreActual;

    @ManyToOne
    @JoinColumn(name = "carrera", referencedColumnName = "id", nullable = false)
    private Carrera carrera;

    @OneToOne
    @JoinColumn(name = "authority", referencedColumnName = "id", nullable = false)
    private Authority authority;

    @JsonBackReference
    @OneToMany(mappedBy = "estudiante")
    private Set<MatriculaAcademica> matriculaAcademicas;
}
