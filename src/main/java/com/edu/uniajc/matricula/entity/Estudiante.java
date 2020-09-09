package com.edu.uniajc.matricula.entity;

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
    public Long id;

    @Column(name = "codigo_estudiante")
    public String codigoEstudiante;

    @Column(name = "creditos_aprobados", nullable = false)
    public int creditosAprobados;

    @Column(name = "semstre_actual", nullable = false)
    private String semestreActual;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrera", referencedColumnName = "id", nullable = false)
    private Carrera carrera;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authority", referencedColumnName = "id", nullable = false)
    private Authority authority;

}
