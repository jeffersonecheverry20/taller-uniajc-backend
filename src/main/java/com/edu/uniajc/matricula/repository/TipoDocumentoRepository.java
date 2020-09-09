package com.edu.uniajc.matricula.repository;

import com.edu.uniajc.matricula.entity.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {

    public Optional<TipoDocumento> findByCodigo(String codigo) throws SQLException;

}
