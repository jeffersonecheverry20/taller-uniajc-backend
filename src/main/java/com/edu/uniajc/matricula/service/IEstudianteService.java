package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.Estudiante;

import java.sql.SQLException;
import java.util.List;

public interface IEstudianteService {

    public Estudiante crearEstudiante(Estudiante estudiante) throws SQLException, Exception;

    public Estudiante buscarEstudianteById(Long id) throws SQLException, Exception;

    public Estudiante buscarEstudianteByCodigo(String codigo) throws SQLException, Exception;

    public List<Estudiante> buscarEstudiantes() throws SQLException, Exception;

    public Estudiante eliminarEstudianteById(Long id) throws SQLException, Exception;

    public Estudiante eliminarEstudianteByCodigo(String codigo) throws SQLException, Exception;

}
