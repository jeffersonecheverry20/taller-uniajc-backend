package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    public Optional<Carrera> findByNombreCarrera(String nombre) throws SQLException;

}
