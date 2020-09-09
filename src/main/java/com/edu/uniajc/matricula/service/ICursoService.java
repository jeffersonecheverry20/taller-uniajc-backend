package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.Administrador;
import com.edu.uniajc.matricula.entity.Curso;
import com.edu.uniajc.matricula.entity.Estudiante;

import java.sql.SQLException;

public interface ICursoService {

    public Curso crearCurso(Curso curso) throws SQLException, Exception;

    public Curso buscarCursoById(Long id) throws SQLException, Exception;

    public Curso buscarCursoByCodigo(String codigo) throws SQLException, Exception;

    public Curso eliminarCurso(Long id) throws SQLException, Exception;

}
