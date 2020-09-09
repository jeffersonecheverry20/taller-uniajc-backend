package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    public Optional<Authority> findByRol(String rol) throws SQLException;

}
