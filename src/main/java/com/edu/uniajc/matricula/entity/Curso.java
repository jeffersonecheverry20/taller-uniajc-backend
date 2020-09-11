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
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombreCurso;

    @Column(name = "grupo", nullable = false)
    private String grupoCurso;

    @Column(name = "codigo", nullable = false)
    private String codigoCurso;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "limite_estudiantes", nullable = false)
    private int limiteEstudiantes;

    @Column(name = "creditos", nullable = false)
    private int creditos;

    @JsonBackReference
    @OneToMany(mappedBy = "curso")
    private Set<MatriculaAcademica> matriculaAcademicas;

}
