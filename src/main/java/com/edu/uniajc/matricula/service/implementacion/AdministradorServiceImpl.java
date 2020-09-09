package com.edu.uniajc.matricula.service.implementacion;

import com.edu.uniajc.matricula.entity.Administrador;
import com.edu.uniajc.matricula.service.IAdministradorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class AdministradorServiceImpl implements IAdministradorService {

    @Transactional
    @Override
    public Administrador crearAdministrador(Administrador administrador) throws SQLException, Exception {
        return null;
    }

    @Transactional
    @Override
    public Administrador buscarAdministradorById(Long id) throws SQLException, Exception {
        return null;
    }

    @Transactional
    @Override
    public Administrador buscarAdministradorByCodigo(String codigo) throws SQLException, Exception {
        return null;
    }

    @Transactional
    @Override
    public Administrador eliminarAdministrador(Long id) throws SQLException, Exception {
        return null;
    }

}
