package com.edu.uniajc.matricula.service.implementacion;

import com.edu.uniajc.matricula.entity.*;
import com.edu.uniajc.matricula.repository.PersonaRepository;
import com.edu.uniajc.matricula.service.IPersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class PersonaServiceImpl implements IPersonaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaServiceImpl.class);

    @Autowired
    private PersonaRepository personaRepository;

    @Transactional
    @Override
    public Persona crearPersona(Persona persona) throws SQLException, Exception {
        Persona personaBD = buscarPersonaByNumeroDocumento(persona.getNumeroDocumento());
        if(personaBD != null){
            personaBD.setNombre(persona.getNombre());
            personaBD.setTelefono(persona.getTelefono());
            personaBD.setTipoDocumento(persona.getTipoDocumento());
            personaBD.setNumeroDocumento(persona.getNumeroDocumento());
            return personaRepository.save(personaBD);
        }
        return personaRepository.save(persona);
    }

    @Transactional
    @Override
    public Persona buscarPersonaById(Long id) throws SQLException, Exception {
        return personaRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Persona buscarPersonaByNumeroDocumento(String numeroDocumento) throws SQLException, Exception {
        return personaRepository.findByNumeroDocumento(numeroDocumento).orElse(null);
    }

    @Transactional
    @Override
    public Persona buscarPersonaByEstudiante(Estudiante estudiante) throws SQLException, Exception {
        return personaRepository.findByEstudiante(estudiante).orElse(null);
    }

    @Transactional
    @Override
    public Persona buscarPersonaByAdministrador(Administrador administrador) throws SQLException, Exception {
        return personaRepository.findByAdministrador(administrador).orElse(null);
    }

    @Transactional
    @Override
    public Persona buscarPersonaByProfesor(Profesor profesor) throws SQLException, Exception {
        return personaRepository.findByProfesor(profesor).orElse(null);
    }

    @Transactional
    @Override
    public Persona buscarPersonaByUsuario(Usuario usuario) throws SQLException, Exception {
        LOGGER.info("JSE --> Ejecuto el servicio buscarPersonaByUsuario");
        return personaRepository.findByUsuario(usuario.getId()).orElse(null);
    }

    @Transactional
    @Override
    public Persona eliminarPersonaById(Long id) throws SQLException, Exception {
        Persona personaBD = buscarPersonaById(id);
        if(personaBD != null){
            personaRepository.deleteById(id);
        }
        return personaBD;
    }

    @Transactional
    @Override
    public Persona eliminarPersonaByNumeroDocumento(String numeroDocumento) throws SQLException, Exception {
        Persona personaBD = buscarPersonaByNumeroDocumento(numeroDocumento);
        if(personaBD != null){
            personaRepository.delete(personaBD);
        }
        return personaBD;
    }

    @Transactional
    @Override
    public Persona eliminarPersonaByEstudiante(Estudiante estudiante) throws SQLException, Exception {
        Persona personaBD = buscarPersonaByEstudiante(estudiante);
        if(personaBD != null){
            personaRepository.delete(personaBD);
        }
        return personaBD;
    }

    @Transactional
    @Override
    public Persona eliminarPersonaByAdministrador(Administrador administrador) throws SQLException, Exception {
        Persona personaBD = buscarPersonaByAdministrador(administrador);
        if(personaBD != null){
            personaRepository.delete(personaBD);
        }
        return personaBD;
    }

    @Transactional
    @Override
    public Persona eliminarPersonaByProfesor(Profesor profesor) throws SQLException, Exception {
        Persona personaBD = buscarPersonaByProfesor(profesor);
        if(personaBD != null){
            personaRepository.delete(personaBD);
        }
        return personaBD;
    }

}
