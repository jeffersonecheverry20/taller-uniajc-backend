package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {


}
