package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.Curso;
import com.edu.uniajc.matricula.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    public Optional<Curso> findByCodigoCurso(String codigo) throws SQLException;

    public Optional<Curso> findByNombreCurso(String nombre) throws SQLException;

}
