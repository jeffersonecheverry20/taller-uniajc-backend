package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.MatriculaAcademica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaAcademicaRepository extends JpaRepository<MatriculaAcademica, Long> {



}
