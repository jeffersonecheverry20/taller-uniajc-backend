package com.edu.uniajc.matricula.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profesor", referencedColumnName = "id", nullable = false)
    private Profesor profesor;

    @OneToMany(mappedBy = "curso")
    private Set<MatriculaAcademica> matriculaAcademicas;

}
