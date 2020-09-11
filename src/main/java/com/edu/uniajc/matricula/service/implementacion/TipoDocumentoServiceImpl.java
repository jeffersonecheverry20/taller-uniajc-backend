package com.edu.uniajc.matricula.service.implementacion;

import com.edu.uniajc.matricula.entity.TipoDocumento;
import com.edu.uniajc.matricula.repository.TipoDocumentoRepository;
import com.edu.uniajc.matricula.service.ITipoDocumentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TipoDocumentoServiceImpl implements ITipoDocumentoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TipoDocumentoServiceImpl.class);

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    public TipoDocumento crearTipoDocumento(TipoDocumento tipoDocumento) throws SQLException, Exception {
        TipoDocumento tipoDocumentoBD = buscarTipoDocumentoByCodigo(tipoDocumento.getCodigo());
        if(tipoDocumentoBD != null){
            tipoDocumentoBD.setCodigo(tipoDocumento.getCodigo());
            tipoDocumentoBD.setValor(tipoDocumento.getValor());
            return tipoDocumentoRepository.save(tipoDocumentoBD);
        }
        return tipoDocumentoRepository.save(tipoDocumento);
    }

    @Override
    public TipoDocumento buscarTipoDocumentoById(Long id) throws SQLException, Exception {
        return tipoDocumentoRepository.findById(id).orElse(null);
    }

    @Override
    public TipoDocumento buscarTipoDocumentoByCodigo(String codigo) throws SQLException, Exception {
        return tipoDocumentoRepository.findByCodigo(codigo).orElse(null);
    }

    @Override
    public List<TipoDocumento> buscarTipoDocumentos() throws SQLException, Exception {
        return tipoDocumentoRepository.findAll();
    }

    @Override
    public TipoDocumento eliminarTipoDocumento(Long id) throws SQLException, Exception {
        TipoDocumento tipoDocumentoBD = buscarTipoDocumentoById(id);
        if(tipoDocumentoBD != null){
            tipoDocumentoRepository.deleteById(id);
        }
        return tipoDocumentoBD;
    }

}
