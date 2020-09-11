package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    public Optional<Administrador> findByCodigoAdministrador(String codigo) throws SQLException;

}
