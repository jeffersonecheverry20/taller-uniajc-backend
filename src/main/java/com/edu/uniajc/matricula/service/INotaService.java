package com.edu.uniajc.matricula.service;

import com.edu.uniajc.matricula.entity.MatriculaAcademica;
import com.edu.uniajc.matricula.entity.Nota;
import org.aspectj.weaver.ast.Not;

import java.sql.SQLException;

public interface INotaService {

    public Nota crearNota(Nota nota) throws SQLException, Exception;

    public Nota buscarNotaById(Long id) throws SQLException, Exception;

    public Nota eliminarNota(Long id) throws SQLException, Exception;

    public double calcularDefinitiva(Long id) throws SQLException, Exception;

    public boolean validarAprobacion(Long id) throws SQLException, Exception;

}
