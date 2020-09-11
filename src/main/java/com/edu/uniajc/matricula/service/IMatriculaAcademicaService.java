package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.Curso;
import com.edu.uniajc.matricula.entity.Estudiante;
import com.edu.uniajc.matricula.entity.MatriculaAcademica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.List;

public interface IMatriculaAcademicaService {

    public MatriculaAcademica crearMatriculaAcademica(MatriculaAcademica matriculaAcademica) throws SQLException, Exception;

    public MatriculaAcademica buscarMatriculaAcademicaById(Long id) throws SQLException, Exception;

    public List<MatriculaAcademica> buscarMatriculaAcademicaByCurso(Curso curso) throws SQLException, Exception;

    public List<MatriculaAcademica> buscarMatriculaAcademicaByEstudiante(Estudiante estudiante) throws SQLException, Exception;

    public MatriculaAcademica buscarMatriculaAcademicaByCursoAndEstudiante(Curso curso, Estudiante estudiante) throws SQLException, Exception;

    public MatriculaAcademica eliminarMatriculaAcademica(Long id) throws SQLException, Exception;

    public void actualizarAprobacion(MatriculaAcademica matriculaAcademica, boolean aprobado) throws SQLException, Exception;

}

