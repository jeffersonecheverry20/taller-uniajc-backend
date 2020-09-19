package com.edu.uniajc.matricula.service.implementacion;

import com.edu.uniajc.matricula.entity.Curso;
import com.edu.uniajc.matricula.entity.Estudiante;
import com.edu.uniajc.matricula.entity.MatriculaAcademica;
import com.edu.uniajc.matricula.exception.MatriculaAcademicaException;
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
    public MatriculaAcademica crearMatriculaAcademica(MatriculaAcademica matriculaAcademica) throws SQLException, MatriculaAcademicaException {
        List<MatriculaAcademica> matriculaAcademicas = buscarMatriculaAcademicaByCurso(matriculaAcademica.getCurso());
        MatriculaAcademica matriculaAcademicaBD = buscarMatriculaAcademicaByCursoAndEstudiante(matriculaAcademica.getCurso(), matriculaAcademica.getEstudiante());
        if(matriculaAcademicas.size() <= matriculaAcademica.getCurso().getLimiteEstudiantes() && matriculaAcademicaBD != null){
            matriculaAcademicaBD.setCurso(matriculaAcademica.getCurso());
            matriculaAcademicaBD.setEstudiante(matriculaAcademica.getEstudiante());
            matriculaAcademicaBD.setProfesor(matriculaAcademica.getProfesor());
            return matriculaAcademicaRepository.save(matriculaAcademicaBD);
        } else if(matriculaAcademicas.size() <= matriculaAcademica.getCurso().getLimiteEstudiantes()){
            return matriculaAcademicaRepository.save(matriculaAcademica);
        } else {
            throw new MatriculaAcademicaException("500", "El curso ya se encuentra dentro de su límite máximo de estudiantes, se debe aperturar nuevo curso");
        }
    }

    @Transactional
    @Override
    public MatriculaAcademica buscarMatriculaAcademicaById(Long id) throws SQLException, MatriculaAcademicaException {
        return matriculaAcademicaRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public List<MatriculaAcademica> buscarMatriculaAcademicaByCurso(Curso curso) throws SQLException, MatriculaAcademicaException {
        return matriculaAcademicaRepository.findAllByCurso(curso).orElse(null);
    }

    @Transactional
    @Override
    public List<MatriculaAcademica> buscarMatriculaAcademicaByEstudiante(Estudiante estudiante) throws SQLException, MatriculaAcademicaException {
        return matriculaAcademicaRepository.findAllByEstudiante(estudiante).orElse(null);
    }

    @Transactional
    @Override
    public MatriculaAcademica buscarMatriculaAcademicaByCursoAndEstudiante(Curso curso, Estudiante estudiante) throws SQLException, MatriculaAcademicaException {
        return matriculaAcademicaRepository.findByCursoAndEstudiante(curso, estudiante).orElse(null);
    }

    @Transactional
    @Override
    public MatriculaAcademica eliminarMatriculaAcademica(Long id) throws SQLException, MatriculaAcademicaException {
        MatriculaAcademica matriculaAcademicaBD = buscarMatriculaAcademicaById(id);
        if(matriculaAcademicaBD != null){
            matriculaAcademicaRepository.deleteById(id);
            return matriculaAcademicaBD;
        } else {
            throw new MatriculaAcademicaException("500", "La matricula a eliminar no exite en la base de datos");
        }
    }

    @Transactional
    @Override
    public void actualizarAprobacion(MatriculaAcademica matriculaAcademica, boolean aprobado) throws SQLException, MatriculaAcademicaException {
        MatriculaAcademica matriculaAcademicaBD = buscarMatriculaAcademicaByCursoAndEstudiante(matriculaAcademica.getCurso(), matriculaAcademica.getEstudiante());
        if(matriculaAcademicaBD != null){
            matriculaAcademicaBD.setAprobado(aprobado);
            matriculaAcademicaRepository.save(matriculaAcademicaBD);
        } else {
            throw new MatriculaAcademicaException("500", "La matricula ha actulizar su aprobación no existe en la base de datos");
        }
    }
}
