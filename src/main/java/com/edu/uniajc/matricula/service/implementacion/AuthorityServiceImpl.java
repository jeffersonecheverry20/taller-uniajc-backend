package com.edu.uniajc.matricula.service.implementacion;

import com.edu.uniajc.matricula.entity.Authority;
import com.edu.uniajc.matricula.repository.AuthorityRepository;
import com.edu.uniajc.matricula.service.IAuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class AuthorityServiceImpl implements IAuthorityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityServiceImpl.class);

    @Autowired
    private AuthorityRepository authorityRepository;

    @Transactional
    @Override
    public Authority crearAuthority(Authority authority) throws SQLException, Exception {
        Authority authorityBD = buscarAuthorityByRol(authority.getRol());
        if(authorityBD != null){
            authorityBD.setRol(authority.getRol());
            return authorityRepository.save(authorityBD);
        }
        return authorityRepository.save(authority);
    }

    @Transactional
    @Override
    public Authority buscarAuthorityByRol(String rol) throws SQLException, Exception {
        return authorityRepository.findByRol(rol).orElse(null);
    }

    @Transactional
    @Override
    public Authority buscarAuthorityById(Long id) throws SQLException, Exception {
        return authorityRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public List<Authority> buscarAuhtorities() throws SQLException, Exception {
        return authorityRepository.findAll();
    }

    @Transactional
    @Override
    public Authority eliminarAuthority(Long id) throws SQLException, Exception {
        Authority authorityBD = buscarAuthorityById(id);
        if(authorityBD != null){
            authorityRepository.deleteById(id);
        }
        return authorityBD;
    }

}
