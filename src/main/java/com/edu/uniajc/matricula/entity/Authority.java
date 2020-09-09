package com.edu.uniajc.matricula.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rol", nullable = false)
    private String rol;

    /*@OneToOne(mappedBy = "authority")
    private Estudiante estudiante;

    @OneToOne(mappedBy = "authority")
    private Profesor profesor;

    @OneToOne(mappedBy = "authority")
    private Administrador administrador;*/

}
