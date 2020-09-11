package com.edu.uniajc.matricula.service.implementacion;

import com.edu.uniajc.matricula.entity.Administrador;
import com.edu.uniajc.matricula.repository.AdministradorRepository;
import com.edu.uniajc.matricula.service.IAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class AdministradorServiceImpl implements IAdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Transactional
    @Override
    public Administrador crearAdministrador(Administrador administrador) throws SQLException, Exception {
        Administrador administradorBD = buscarAdministradorByCodigo(administrador.getCodigoAdministrador());
        if(administradorBD != null){
            administradorBD.setCodigoAdministrador(administrador.getCodigoAdministrador());
            return administradorRepository.save(administradorBD);
        }
        return administradorRepository.save(administrador);
    }

    @Transactional
    @Override
    public Administrador buscarAdministradorById(Long id) throws SQLException, Exception {
        return administradorRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Administrador buscarAdministradorByCodigo(String codigo) throws SQLException, Exception {
        return administradorRepository.findByCodigoAdministrador(codigo).orElse(null);
    }

    @Transactional
    @Override
    public Administrador eliminarAdministradorById(Long id) throws SQLException, Exception {
        Administrador administradorBD = buscarAdministradorById(id);
        if(administradorBD != null){
            administradorRepository.deleteById(id);
        }
        return administradorBD;
    }

    @Transactional
    @Override
    public Administrador eliminarAdministradorByCodigo(String codigo) throws SQLException, Exception {
        Administrador administradorBD = buscarAdministradorByCodigo(codigo);
        if(administradorBD != null){
            administradorRepository.delete(administradorBD);
        }
        return administradorBD;
    }

}
