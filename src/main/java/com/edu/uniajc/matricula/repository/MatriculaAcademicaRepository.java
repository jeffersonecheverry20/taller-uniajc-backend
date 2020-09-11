package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.Curso;
import com.edu.uniajc.matricula.entity.Estudiante;
import com.edu.uniajc.matricula.entity.MatriculaAcademica;
import com.edu.uniajc.matricula.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaAcademicaRepository extends JpaRepository<MatriculaAcademica, Long> {

    public Optional<List<MatriculaAcademica>> findAllByCurso(Curso curso) throws SQLException;

    public Optional<List<MatriculaAcademica>> findAllByEstudiante(Estudiante estudiante) throws SQLException;

    public Optional<MatriculaAcademica> findByCursoAndEstudiante(Curso curso, Estudiante estudiante) throws SQLException;


}
