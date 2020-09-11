package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.Persona;
import com.edu.uniajc.matricula.entity.Profesor;

import java.sql.SQLException;
import java.util.List;

public interface IProfesorService {

    public Profesor crearProfesor(Profesor profesor) throws SQLException, Exception;

    public Profesor buscarProfesorById(Long id) throws SQLException, Exception;

    public Profesor buscarProfesorByCodigo(String codigo) throws SQLException, Exception;

    public List<Profesor> buscarProfesores() throws SQLException, Exception;

    public Profesor eliminarProfesorById(Long id) throws SQLException, Exception;

    public Profesor eliminarProfesorByCodigo(String codigo) throws SQLException, Exception;

}
