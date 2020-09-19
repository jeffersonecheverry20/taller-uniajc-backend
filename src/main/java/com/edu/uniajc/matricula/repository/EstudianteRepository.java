package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    public Optional<Estudiante> findByCodigoEstudiante(String codigo) throws SQLException;

}
