package com.edu.uniajc.matricula.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class MatriculaAcademica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estudiante", referencedColumnName = "id", nullable = false)
    private Estudiante estudiante;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curso", referencedColumnName = "id", nullable = false)
    private Curso curso;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nota", referencedColumnName = "id", nullable = false)
    private Nota nota;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "profesor", referencedColumnName = "id")
    private Profesor profesor;

    @Column(name = "aprobado", nullable = false)
    private boolean aprobado;

}
