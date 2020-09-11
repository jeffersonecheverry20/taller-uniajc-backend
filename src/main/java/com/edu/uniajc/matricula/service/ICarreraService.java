package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.Carrera;

import java.sql.SQLException;
import java.util.List;

public interface ICarreraService {

    public Carrera crearCarrera(Carrera carrera) throws Exception, SQLException;

    public Carrera buscarCarreraById(Long id) throws Exception, SQLException;

    public Carrera buscarCarreraByNombre(String nombre) throws Exception, SQLException;

    public List<Carrera> buscarCarreras() throws SQLException, Exception;

    public Carrera eliminarCarrera(Long id) throws Exception, SQLException;

}
