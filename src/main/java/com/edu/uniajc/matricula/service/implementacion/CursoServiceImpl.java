package com.edu.uniajc.matricula.service.implementacion;

import com.edu.uniajc.matricula.entity.Curso;
import com.edu.uniajc.matricula.repository.CursoRepository;
import com.edu.uniajc.matricula.service.ICursoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class CursoServiceImpl implements ICursoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CursoServiceImpl.class);

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    @Override
    public Curso crearCurso(Curso curso) throws SQLException, Exception {
        Curso cursoBD = buscarCursoByCodigo(curso.getCodigoCurso());
        if(cursoBD != null){
            cursoBD.setCreditos(curso.getCreditos());
            cursoBD.setDescripcion(curso.getDescripcion());
            cursoBD.setGrupoCurso(curso.getGrupoCurso());
            cursoBD.setLimiteEstudiantes(curso.getLimiteEstudiantes());
            cursoBD.setNombreCurso(curso.getNombreCurso());
            return cursoRepository.save(cursoBD);
        }
        return cursoRepository.save(curso);
    }

    @Transactional
    @Override
    public Curso buscarCursoById(Long id) throws SQLException, Exception {
        return cursoRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Curso buscarCursoByCodigo(String codigo) throws SQLException, Exception {
        return cursoRepository.findByCodigoCurso(codigo).orElse(null);
    }

    @Override
    public List<Curso> buscarCursos() throws SQLException, Exception {
        return cursoRepository.findAll();
    }

    @Override
    public Curso buscarCursoByNombre(String nombre) throws SQLException, Exception {
        return cursoRepository.findByNombreCurso(nombre).orElse(null);
    }

    @Transactional
    @Override
    public Curso eliminarCursoById(Long id) throws SQLException, Exception {
        Curso cursoBD = buscarCursoById(id);
        if(cursoBD != null){
            cursoRepository.deleteById(id);
        }
        return cursoBD;
    }

    @Transactional
    @Override
    public Curso eliminarCursoByCodigo(String codigo) throws SQLException, Exception {
        Curso cursoBD = buscarCursoByCodigo(codigo);
        if(cursoBD != null){
            cursoRepository.delete(cursoBD);
        }
        return cursoBD;
    }

}
