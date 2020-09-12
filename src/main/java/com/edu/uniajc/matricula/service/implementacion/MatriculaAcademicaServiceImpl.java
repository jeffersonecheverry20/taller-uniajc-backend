package com.edu.uniajc.matricula.service.implementacion;

import com.edu.uniajc.matricula.entity.Curso;
import com.edu.uniajc.matricula.entity.Estudiante;
import com.edu.uniajc.matricula.entity.MatriculaAcademica;
import com.edu.uniajc.matricula.repository.MatriculaAcademicaRepository;
import com.edu.uniajc.matricula.service.IMatriculaAcademicaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class MatriculaAcademicaServiceImpl implements IMatriculaAcademicaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatriculaAcademicaServiceImpl.class);

    @Autowired
    private MatriculaAcademicaRepository matriculaAcademicaRepository;

    @Transactional
    @Override
    public MatriculaAcademica crearMatriculaAcademica(MatriculaAcademica matriculaAcademica) throws SQLException, Exception {
        MatriculaAcademica matriculaAcademicaBD = buscarMatriculaAcademicaByCursoAndEstudiante(matriculaAcademica.getCurso(), matriculaAcademica.getEstudiante());
        if(matriculaAcademicaBD != null){
            LOGGER.info("JSE --> Encontre registro en la base de datos para actualizar matricula");
            LOGGER.info("JSE --> El codigo del profesor es "+matriculaAcademicaBD.getProfesor().getCodigoProfesor());
            LOGGER.info("JSE --> El id del profesor es "+matriculaAcademicaBD.getProfesor().getId());
            matriculaAcademicaBD.setCurso(matriculaAcademica.getCurso());
            matriculaAcademicaBD.setEstudiante(matriculaAcademica.getEstudiante());
            matriculaAcademicaBD.setProfesor(matriculaAcademica.getProfesor());
            return matriculaAcademicaRepository.save(matriculaAcademicaBD);
        }
        return matriculaAcademicaRepository.save(matriculaAcademica);
    }

    @Transactional
    @Override
    public MatriculaAcademica buscarMatriculaAcademicaById(Long id) throws SQLException, Exception {
        return matriculaAcademicaRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public List<MatriculaAcademica> buscarMatriculaAcademicaByCurso(Curso curso) throws SQLException, Exception {
        return matriculaAcademicaRepository.findAllByCurso(curso).orElse(null);
    }

    @Transactional
    @Override
    public List<MatriculaAcademica> buscarMatriculaAcademicaByEstudiante(Estudiante estudiante) throws SQLException, Exception {
        return matriculaAcademicaRepository.findAllByEstudiante(estudiante).orElse(null);
    }

    @Transactional
    @Override
    public MatriculaAcademica buscarMatriculaAcademicaByCursoAndEstudiante(Curso curso, Estudiante estudiante) throws SQLException, Exception {
        return matriculaAcademicaRepository.findByCursoAndEstudiante(curso, estudiante).orElse(null);
    }

    @Transactional
    @Override
    public MatriculaAcademica eliminarMatriculaAcademica(Long id) throws SQLException, Exception {
        MatriculaAcademica matriculaAcademicaBD = buscarMatriculaAcademicaById(id);
        if(matriculaAcademicaBD != null){
            matriculaAcademicaRepository.deleteById(id);
        }
        return matriculaAcademicaBD;
    }

    @Transactional
    @Override
    public void actualizarAprobacion(MatriculaAcademica matriculaAcademica, boolean aprobado) throws SQLException, Exception {
        MatriculaAcademica matriculaAcademicaBD = buscarMatriculaAcademicaByCursoAndEstudiante(matriculaAcademica.getCurso(), matriculaAcademica.getEstudiante());
        if(matriculaAcademicaBD != null){
            matriculaAcademicaBD.setAprobado(aprobado);
            matriculaAcademicaRepository.save(matriculaAcademicaBD);
        }
    }
}
