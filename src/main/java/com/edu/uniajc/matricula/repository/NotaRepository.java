package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {



}
