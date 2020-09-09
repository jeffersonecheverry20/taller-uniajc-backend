package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    public Optional<Persona> findByNumeroDocumento(String numeroDocumento) throws SQLException;

    public Optional<Persona> findByEstudiante(Estudiante estudiante) throws SQLException;

    public Optional<Persona> findByAdministrador(Administrador administrador) throws SQLException;

    public Optional<Persona> findByProfesor(Profesor profesor) throws SQLException;

    public Optional<Persona> findByUsuario(Usuario usuario) throws SQLException;

}
