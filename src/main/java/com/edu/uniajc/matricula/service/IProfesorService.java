package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.Persona;
import com.edu.uniajc.matricula.entity.Profesor;

import java.sql.SQLException;

public interface IProfesorService {

    public Profesor crearProfesor(Profesor profesor) throws SQLException, Exception;

    public Profesor buscarProfesorById(Long id) throws SQLException, Exception;

    public Profesor buscarProfesorByCodigo(String codigo) throws SQLException, Exception;

    public Profesor eliminarProfesor(Long id) throws SQLException, Exception;

}
