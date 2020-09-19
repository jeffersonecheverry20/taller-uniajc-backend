package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.Estudiante;
import com.edu.uniajc.matricula.exception.EstudianteException;

import java.sql.SQLException;
import java.util.List;

public interface IEstudianteService {

    public Estudiante crearEstudiante(Estudiante estudiante) throws SQLException, EstudianteException;

    public Estudiante buscarEstudianteById(Long id) throws SQLException, EstudianteException;

    public Estudiante buscarEstudianteByCodigo(String codigo) throws SQLException, EstudianteException;

    public List<Estudiante> buscarEstudiantes() throws SQLException, EstudianteException;

    public Estudiante eliminarEstudianteById(Long id) throws SQLException, EstudianteException;

    public Estudiante eliminarEstudianteByCodigo(String codigo) throws SQLException, EstudianteException;

}
