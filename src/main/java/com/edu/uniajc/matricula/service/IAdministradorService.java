package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.Administrador;

import java.sql.SQLException;

public interface IAdministradorService {

    public Administrador crearAdministrador(Administrador administrador) throws SQLException, Exception;

    public Administrador buscarAdministradorById(Long id) throws SQLException, Exception;

    public Administrador buscarAdministradorByCodigo(String codigo) throws SQLException, Exception;

    public Administrador eliminarAdministrador(Long id) throws SQLException, Exception;

}
