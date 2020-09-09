package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    public Optional<Profesor> findByCodigoProfesor(String codigo) throws SQLException;

}
