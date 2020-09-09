package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.TipoDocumento;
import com.edu.uniajc.matricula.entity.Usuario;

import java.sql.SQLException;

public interface IUsuarioService {

    public Usuario crearUsuario(Usuario usuario) throws SQLException, Exception;

    public Usuario buscarUsuarioById(Long id) throws SQLException, Exception;

    public Usuario buscarUsuarioByUsuario(String usuario) throws SQLException, Exception;

    public Usuario buscarUsuarioByUsuarioandPassword(String usuario, String password) throws SQLException, Exception;

    public Usuario eliminarUsuarioById(Long id) throws SQLException, Exception;

    public Usuario eliminarUsuarioByUsuario(String usuario) throws SQLException, Exception;

}
