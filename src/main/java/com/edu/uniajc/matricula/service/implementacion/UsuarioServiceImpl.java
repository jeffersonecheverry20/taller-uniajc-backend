package com.edu.uniajc.matricula.service.implementacion;

import com.edu.uniajc.matricula.entity.TipoDocumento;
import com.edu.uniajc.matricula.entity.Usuario;
import com.edu.uniajc.matricula.repository.UsuarioRepository;
import com.edu.uniajc.matricula.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public Usuario crearUsuario(Usuario usuario) throws SQLException, Exception {
        Usuario usuarioBD = buscarUsuarioByUsuario(usuario.getUsuario());
        if(usuarioBD != null){
            usuarioBD.setPassword(usuario.getPassword());
            return usuarioRepository.save(usuarioBD);
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    @Override
    public Usuario buscarUsuarioById(Long id) throws SQLException, Exception {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Usuario buscarUsuarioByUsuario(String usuario) throws SQLException, Exception {
        return usuarioRepository.findByUsuario(usuario).orElse(null);
    }

    @Transactional
    @Override
    public Usuario buscarUsuarioByUsuarioandPassword(String usuario, String password) throws SQLException, Exception {
        return usuarioRepository.findByUsuarioAndPassword(usuario, password).orElse(null);
    }

    @Transactional
    @Override
    public Usuario eliminarUsuarioById(Long id) throws SQLException, Exception {
        Usuario usuarioBD = buscarUsuarioById(id);
        if(usuarioBD != null){
            usuarioRepository.deleteById(id);
        }
        return usuarioBD;
    }

    @Transactional
    @Override
    public Usuario eliminarUsuarioByUsuario(String usuario) throws SQLException, Exception {
        Usuario usuarioBD = buscarUsuarioByUsuario(usuario);
        if(usuarioBD != null){
            usuarioRepository.delete(usuarioBD);
        }
        return usuarioBD;
    }

}
