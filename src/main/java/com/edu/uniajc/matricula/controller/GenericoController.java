package com.edu.uniajc.matricula.controller;

import com.edu.uniajc.matricula.facade.Facade;
import com.edu.uniajc.matricula.util.Constantes;
import com.edu.uniajc.matricula.util.Utilidades;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/generico")
public class GenericoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfesorController.class);

    @Autowired
    private Facade facade;

    //Estudiantes

    @GetMapping("/buscar/estudiante/{id}")
    public ResponseEntity buscarEstudianteById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarEstudianteById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_ESTUDIANTE_BY_ID, id));
    }

    @GetMapping("/buscar/estudianteCodigo/{codigo}")
    public ResponseEntity buscarEstudianteByCodigo(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de buscarEstudianteByCodigo");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_ESTUDIANTE_BY_CODIGO, codigo));
    }

    @GetMapping("/buscar/estudiante")
    public ResponseEntity buscarEstudiantes(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarEstudiantes");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_ESTUDIANTES, null));
    }


    //Profesores

    @GetMapping("/buscar/profesor/{id}")
    public ResponseEntity buscarProfesorById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarProfesorById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_PROFESOR_BY_ID, id));
    }

    @GetMapping("/buscar/profesorCodigo/{codigo}")
    public ResponseEntity buscarProfesorByCodigo(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de buscarProfesorByCodigo");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_PROFESOR_BY_CODIGO, codigo));
    }

    @GetMapping("/buscar/profesor")
    public ResponseEntity buscarProfesor(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarProfesor");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_PROFESORES, null));
    }

    //Administradores

    @GetMapping("/buscar/administrador/{id}")
    public ResponseEntity buscarAdministadorById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarAdministradorById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_ADMINISTRADOR_BY_ID, id));
    }

    @GetMapping("/buscar/administradorCodigo/{codigo}")
    public ResponseEntity buscarAdministradorByCodigo(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de buscarAdministradorByCodigo");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_ADMINISTRADOR_BY_CODIGO, codigo));
    }

    @GetMapping("/buscar/administrador")
    public ResponseEntity buscarAdministrador(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarAdministradores");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_ADMINISTRADORES, null));
    }

    //Carrera

    @GetMapping("/buscar/carrera/{id}")
    public ResponseEntity buscarCarreraById(@PathVariable("id") Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarCarreraById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_CARRERA_BY_ID, id));
    }

    @GetMapping("/buscar/carrera")
    public ResponseEntity buscarCarreras(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarCarreras");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_CARRERAS, null));
    }

    //Cursos

    @GetMapping("/buscar/curso/{id}")
    public ResponseEntity buscarCursoById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarCursoById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_CURSO_BY_ID, id));
    }

    @GetMapping("/buscar/cursoCodigo/{codigo}")
    public ResponseEntity buscarCursoById(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de buscarCursoByCodigo");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_CURSO_BY_CODIGO, codigo));
    }

    @GetMapping("/buscar/curso")
    public ResponseEntity buscarCurso(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarCurso");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_CURSOS, null));
    }


}
