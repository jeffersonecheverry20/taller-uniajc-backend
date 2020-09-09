package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByUsuario(String usuario) throws SQLException;

    public Optional<Usuario> findByUsuarioAndPassword(String usuario, String password) throws SQLException;

}
