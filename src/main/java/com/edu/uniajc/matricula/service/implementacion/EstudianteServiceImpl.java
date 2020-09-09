package com.edu.uniajc.matricula.service.implementacion;

import com.edu.uniajc.matricula.entity.Estudiante;
import com.edu.uniajc.matricula.repository.EstudianteRepository;
import com.edu.uniajc.matricula.service.IEstudianteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstudianteServiceImpl.class);

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Transactional
    @Override
    public Estudiante crearEstudiante(Estudiante estudiante) throws SQLException, Exception {
        Estudiante estudianteBD = buscarEstudianteByCodigo(estudiante.getCodigoEstudiante());
        if(estudianteBD != null){
            estudianteBD.setAuthority(estudiante.getAuthority());
            estudianteBD.setCodigoEstudiante(estudiante.getCodigoEstudiante());
            return estudianteRepository.save(estudianteBD);
        }
        return estudianteRepository.save(estudiante);
    }

    @Transactional
    @Override
    public Estudiante buscarEstudianteById(Long id) throws SQLException, Exception {
        return estudianteRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Estudiante buscarEstudianteByCodigo(String codigo) throws SQLException, Exception {
        return estudianteRepository.findByCodigoEstudiante(codigo).orElse(null);
    }

    @Transactional
    @Override
    public Estudiante eliminarEstudianteById(Long id) throws SQLException, Exception {
        Estudiante estudianteBD = buscarEstudianteById(id);
        if(estudianteBD != null){
            estudianteRepository.deleteById(id);
        }
        return estudianteBD;
    }

    @Override
    public Estudiante eliminarEstudianteByCodigo(String codigo) throws SQLException, Exception {
        Estudiante estudianteBD = buscarEstudianteByCodigo(codigo);
        if(estudianteBD != null){
            estudianteRepository.delete(estudianteBD);
        }
        return estudianteBD;
    }

}
