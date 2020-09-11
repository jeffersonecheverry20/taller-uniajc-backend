package com.edu.uniajc.matricula.service.implementacion;

import com.edu.uniajc.matricula.entity.Nota;
import com.edu.uniajc.matricula.repository.NotaRepository;
import com.edu.uniajc.matricula.service.INotaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class NotaServiceImpl implements INotaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotaServiceImpl.class);

    @Autowired
    private NotaRepository notaRepository;

    @Transactional
    @Override
    public Nota crearNota(Nota nota) throws SQLException, Exception {
        if(nota.getId() != null){
            Nota notaBD = buscarNotaById(nota.getId());
            notaBD.setParcialI(nota.getParcialI());
            notaBD.setParcialII(nota.getParcialII());
            notaBD.setParcialIII(nota.getParcialIII());
            notaBD.setQuiz(nota.getQuiz());
            notaBD.setProyecto(nota.getProyecto());
            notaBD.setDefinitiva(calcularDefinitiva(nota.getId()));
            notaRepository.save(notaBD);
        }
        return notaRepository.save(nota);
    }

    @Transactional
    @Override
    public Nota buscarNotaById(Long id) throws SQLException, Exception {
        return notaRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Nota eliminarNota(Long id) throws SQLException, Exception {
        Nota nota = buscarNotaById(id);
        if(nota != null){
            notaRepository.deleteById(id);
        }
        return nota;
    }

    @Transactional
    @Override
    public double calcularDefinitiva(Long id) throws SQLException, Exception {
        Nota notaBD = buscarNotaById(id);
        if(notaBD != null){
            return (notaBD.getParcialI()*0.2)+(notaBD.getParcialII()*0.2)+
                    (notaBD.getParcialIII()*0.25)+(notaBD.getQuiz()*0.15)+
                    (notaBD.getProyecto()*0.25);
        }
        return 0.0;
    }

    @Transactional
    @Override
    public boolean validarAprobacion(Long id) throws SQLException, Exception {
        Nota notaBD = buscarNotaById(id);
        return notaBD != null && notaBD.getDefinitiva() >= 3.0;
    }
}
