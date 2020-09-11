package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.Administrador;

import java.sql.SQLException;
import java.util.List;

public interface IAdministradorService {

    public Administrador crearAdministrador(Administrador administrador) throws SQLException, Exception;

    public Administrador buscarAdministradorById(Long id) throws SQLException, Exception;

    public Administrador buscarAdministradorByCodigo(String codigo) throws SQLException, Exception;

    public List<Administrador> buscarAdministradores() throws SQLException, Exception;

    public Administrador eliminarAdministradorById(Long id) throws SQLException, Exception;

    public Administrador eliminarAdministradorByCodigo(String codigo) throws SQLException, Exception;

}
