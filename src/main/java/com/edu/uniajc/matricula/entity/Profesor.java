package com.edu.uniajc.matricula.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Profesor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "codigo", nullable = false)
    private String codigoProfesor;

    @OneToOne(mappedBy = "profesor")
    private Persona persona;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authority", referencedColumnName = "id", nullable = false)
    private Authority authority;

    @OneToMany(mappedBy = "profesor")
    private Set<Curso> cursos;

}
