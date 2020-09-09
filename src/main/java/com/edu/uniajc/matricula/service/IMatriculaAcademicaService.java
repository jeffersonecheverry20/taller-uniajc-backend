package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.Estudiante;
import com.edu.uniajc.matricula.entity.MatriculaAcademica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;

public interface IMatriculaAcademicaService {

    public MatriculaAcademica crearMatriculaAcademica(MatriculaAcademica matriculaAcademica) throws SQLException, Exception;

    public MatriculaAcademica buscarMatriculaAcademicaById(Long id) throws SQLException, Exception;

    public MatriculaAcademica buscarMatriculaAcademicaByCodigo(String codigo) throws SQLException, Exception;

    public MatriculaAcademica eliminarMatriculaAcademica(Long id) throws SQLException, Exception;

}

