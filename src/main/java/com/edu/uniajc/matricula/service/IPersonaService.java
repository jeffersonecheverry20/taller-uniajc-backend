package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.*;

import java.sql.SQLException;

public interface IPersonaService {

    public Persona crearPersona(Persona persona) throws SQLException, Exception;

    public Persona buscarPersonaById(Long id) throws SQLException, Exception;

    public Persona buscarPersonaByNumeroDocumento(String numeroDocumento) throws SQLException, Exception;

    public Persona buscarPersonaByEstudiante(Estudiante estudiante) throws SQLException, Exception;

    public Persona buscarPersonaByAdministrador(Administrador administrador) throws SQLException, Exception;

    public Persona buscarPersonaByProfesor(Profesor profesor) throws SQLException, Exception;

    public Persona buscarPersonaByUsuario(Usuario usuario) throws SQLException, Exception;

    public Persona eliminarPersonaById(Long id) throws SQLException, Exception;

    public Persona eliminarPersonaByNumeroDocumento(String numeroDocumento) throws SQLException, Exception;

    public Persona eliminarPersonaByEstudiante(Estudiante estudiante) throws SQLException, Exception;

    public Persona eliminarPersonaByAdministrador(Administrador administrador) throws SQLException, Exception;

    public Persona eliminarPersonaByProfesor(Profesor profesor) throws SQLException, Exception;

}
