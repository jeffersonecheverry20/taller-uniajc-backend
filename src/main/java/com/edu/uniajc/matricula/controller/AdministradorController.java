package com.edu.uniajc.matricula.controller;

import com.edu.uniajc.matricula.facade.Facade;
import com.edu.uniajc.matricula.modelo.Respuesta;
import com.edu.uniajc.matricula.util.Constantes;
import com.edu.uniajc.matricula.util.Utilidades;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/administrador")
public class AdministradorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdministradorController.class);

    @Autowired
    private Facade facade;

    @PostMapping(value = "/create/carrera")
    public ResponseEntity crearCarrera(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearCarrera");
        return facade.operation(json);
    }

    @GetMapping("/buscar/carrera/{id}")
    public ResponseEntity buscarCarreraById(@PathVariable("id") Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarCarreraById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_CARRERA_BY_ID, id));
    }

    @DeleteMapping("/eliminar/carrera/{id}")
    public ResponseEntity eliminarCarreraById(@PathVariable("id") Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarCarreraById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_CARRERA, id));
    }

    @PostMapping("/crear/authority")
    public ResponseEntity crearAuthority(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearAuthority");
        return facade.operation(json);
    }

    @GetMapping("/buscar/authority/{id}")
    public ResponseEntity buscarAuhtority(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_AUTHORITY, id));
    }

    @GetMapping("/buscar/authority")
    public ResponseEntity buscarAuthorities(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarAuhtorities");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_AUTHORITIES, null));
    }

    @DeleteMapping("/eliminar/authority/{id}")
    public ResponseEntity eliminarAuhtority(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_AUTHORITY, id));
    }

    @PostMapping("/crear/estudiante")
    public ResponseEntity crearEstudiante(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearEstudiante");
        return facade.operation(json);
    }

    @PostMapping("/crear/profesor")
    public ResponseEntity crearProfesor(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearProfesor");
        return facade.operation(json);
    }

    @PostMapping("/matricular")
    public ResponseEntity matricularEstudiante(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de matricularEstudiante");
        return facade.operation(json);
    }

    @DeleteMapping("/eliminar/estudiante/{id}")
    public ResponseEntity eliminarEstudianteById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_ESTUDIANTE_BY_ID, id));
    }

    @DeleteMapping("/eliminar/estudiante/{codigo}")
    public ResponseEntity eliminarEstudianteByCodigo(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_ESTUDIANTE_BY_CODIGO, codigo));
    }

    @DeleteMapping("/eliminar/administrador/{id}")
    public ResponseEntity eliminarAdministrador(@PathVariable Long id){
        return null;
    }

    @DeleteMapping("/eliminar/profesor/{id}")
    public ResponseEntity eliminarProfesor(@PathVariable Long id){
        return null;
    }

    @PostMapping("/crear/tipoDocumento")
    public ResponseEntity crearTipoDocumento(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(json);
    }

}
