package com.edu.uniajc.matricula.controller;

import com.edu.uniajc.matricula.entity.Profesor;
import com.edu.uniajc.matricula.facade.Facade;
import com.edu.uniajc.matricula.util.Constantes;
import com.edu.uniajc.matricula.util.Utilidades;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profesor")
public class ProfesorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfesorController.class);

    @Autowired
    private Facade facade;

    @GetMapping("/buscar/estudiante/{id}")
    public ResponseEntity buscarEstudianteById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarEstudianteById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_AUTHORITY, id));
    }

    @GetMapping("/buscar/estudiante/{codigo}")
    public ResponseEntity buscarEstudianteByCodigo(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de buscarEstudianteByCodigo");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_AUTHORITY, codigo));
    }

}
