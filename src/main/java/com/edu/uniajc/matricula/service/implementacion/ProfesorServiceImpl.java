package com.edu.uniajc.matricula.service.implementacion;

import com.edu.uniajc.matricula.entity.Profesor;
import com.edu.uniajc.matricula.repository.ProfesorRepository;
import com.edu.uniajc.matricula.service.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProfesorServiceImpl implements IProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Transactional
    @Override
    public Profesor crearProfesor(Profesor profesor) throws SQLException, Exception {
        Profesor profesorBD = buscarProfesorByCodigo(profesor.getCodigoProfesor());
        if(profesorBD != null){
            profesorBD.setAuthority(profesor.getAuthority());
            profesorBD.setCodigoProfesor(profesorBD.getCodigoProfesor());
            return profesorRepository.save(profesorBD);
        }
        return profesorRepository.save(profesor);
    }

    @Transactional
    @Override
    public Profesor buscarProfesorById(Long id) throws SQLException, Exception {
        return profesorRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Profesor buscarProfesorByCodigo(String codigo) throws SQLException, Exception {
        return profesorRepository.findByCodigoProfesor(codigo).orElse(null);
    }

    @Override
    public List<Profesor> buscarProfesores() throws SQLException, Exception {
        return profesorRepository.findAll();
    }

    @Transactional
    @Override
    public Profesor eliminarProfesorById(Long id) throws SQLException, Exception {
        Profesor profesorBD = buscarProfesorById(id);
        if(profesorBD != null){
            profesorRepository.deleteById(id);
        }
        return profesorBD;
    }

    @Transactional
    @Override
    public Profesor eliminarProfesorByCodigo(String codigo) throws SQLException, Exception {
        Profesor profesorBD = buscarProfesorByCodigo(codigo);
        if(profesorBD != null){
            profesorRepository.delete(profesorBD);
        }
        return profesorBD;
    }



}
