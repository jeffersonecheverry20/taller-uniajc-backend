package com.edu.uniajc.matricula.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class MatriculaAcademica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estudiante", referencedColumnName = "id", nullable = false)
    private Estudiante estudiante;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curso", referencedColumnName = "id", nullable = false)
    private Curso curso;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nota", referencedColumnName = "id", nullable = false)
    private Nota nota;
    @Column(name = "aprobado", nullable = false)
    private boolean aprobado;

}
