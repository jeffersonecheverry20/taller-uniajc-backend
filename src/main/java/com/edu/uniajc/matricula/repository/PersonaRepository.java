package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    public Optional<Persona> findByNumeroDocumento(String numeroDocumento) throws SQLException;

    public Optional<Persona> findByEstudiante(Estudiante estudiante) throws SQLException;

    public Optional<Persona> findByAdministrador(Administrador administrador) throws SQLException;

    public Optional<Persona> findByProfesor(Profesor profesor) throws SQLException;

    @Query(nativeQuery = true, value = "select * from matricula.persona where usuario = :idUsuario")
    public Optional<Persona> findByUsuario(@Param("idUsuario") Long idUsuario) throws SQLException;

}
