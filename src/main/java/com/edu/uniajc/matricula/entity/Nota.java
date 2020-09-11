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
public class Nota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "parcialI")
    private double parcialI;
    @Column(name = "parcialII")
    private double parcialII;
    @Column(name = "parcialIII")
    private double parcialIII;
    @Column(name = "quiz")
    private double quiz;
    @Column(name = "proyecto")
    private double proyecto;
    @Column(name = "definitiva")
    private double definitiva;

    @JsonBackReference
    @OneToMany(mappedBy = "nota")
    private Set<MatriculaAcademica> matriculaAcademicas;

}
