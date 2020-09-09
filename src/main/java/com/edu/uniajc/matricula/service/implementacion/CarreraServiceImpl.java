package com.edu.uniajc.matricula.service.implementacion;

import com.edu.uniajc.matricula.entity.Carrera;
import com.edu.uniajc.matricula.repository.CarreraRepository;
import com.edu.uniajc.matricula.service.ICarreraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class CarreraServiceImpl implements ICarreraService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarreraServiceImpl.class);

    @Autowired
    private CarreraRepository carreraRepository;

    @Transactional
    @Override
    public Carrera crearCarrera(Carrera carrera) throws Exception, SQLException {
        LOGGER.info("JSE --> Ejecuto el serivicio de CrearCarrera");
        Carrera carreraBD = carrera.getNombreCarrera() != null ? buscarCarreraByNombre(carrera.getNombreCarrera()) : null;
        if(carreraBD != null){
            LOGGER.info("JSE --> Encontre la carrera con nombre "+carrera.getNombreCarrera());
            carreraBD.setNombreCarrera(carrera.getNombreCarrera());
            carreraBD.setTotalCreditos(carrera.getTotalCreditos());
            return carreraRepository.save(carreraBD);
        }
        return carreraRepository.save(carrera);
    }

    @Transactional
    @Override
    public Carrera buscarCarreraById(Long id) throws Exception, SQLException {
        return carreraRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Carrera buscarCarreraByNombre(String nombre) throws Exception, SQLException {
        return carreraRepository.findByNombreCarrera(nombre).orElse(null);
    }

    @Transactional
    @Override
    public Carrera eliminarCarrera(Long id) throws Exception, SQLException {
        LOGGER.info("JSE --> Ejecuto el serivicio de EliminarCarrera");
        Carrera carreraBD = buscarCarreraById(id);
        if(carreraBD != null){
            LOGGER.info("JSE --> Encontre la carrera con nombre "+carreraBD.getNombreCarrera());
            carreraRepository.deleteById(id);
        }
        return carreraBD;
    }

}
