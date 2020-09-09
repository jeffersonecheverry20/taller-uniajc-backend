package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.Authority;

import java.sql.SQLException;
import java.util.List;

public interface IAuthorityService {

    public Authority crearAuthority(Authority authority) throws SQLException, Exception;

    public Authority buscarAuthorityByRol(String rol) throws SQLException, Exception;

    public Authority buscarAuthorityById(Long id) throws SQLException, Exception;

    public List<Authority> buscarAuhtorities() throws SQLException, Exception;

    public Authority eliminarAuthority(Long id) throws SQLException, Exception;

}
