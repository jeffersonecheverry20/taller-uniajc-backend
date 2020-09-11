package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.Profesor;
import com.edu.uniajc.matricula.entity.TipoDocumento;

import java.sql.SQLException;
import java.util.List;

public interface ITipoDocumentoService {

    public TipoDocumento crearTipoDocumento(TipoDocumento tipoDocumento) throws SQLException, Exception;

    public TipoDocumento buscarTipoDocumentoById(Long id) throws SQLException, Exception;

    public TipoDocumento buscarTipoDocumentoByCodigo(String codigo) throws SQLException, Exception;

    public List<TipoDocumento> buscarTipoDocumentos() throws SQLException, Exception;

    public TipoDocumento eliminarTipoDocumento(Long id) throws SQLException, Exception;

}
