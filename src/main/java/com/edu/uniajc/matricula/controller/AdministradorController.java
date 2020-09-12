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

    //Carreras

    @PostMapping(value = "/create/carrera")
    public ResponseEntity crearCarrera(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearCarrera");
        return facade.operation(json);
    }

    @DeleteMapping("/eliminar/carrera/{id}")
    public ResponseEntity eliminarCarreraById(@PathVariable("id") Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarCarreraById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_CARRERA, id));
    }

    //Authorities
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

    //Estudiantes

    @PostMapping("/crear/estudiante")
    public ResponseEntity crearEstudiante(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearEstudiante");
        return facade.operation(json);
    }

    @DeleteMapping("/eliminar/estudiante/{id}")
    public ResponseEntity eliminarEstudianteById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_ESTUDIANTE_BY_ID, id));
    }

    @DeleteMapping("/eliminar/estudianteCodigo/{codigo}")
    public ResponseEntity eliminarEstudianteByCodigo(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_ESTUDIANTE_BY_CODIGO, codigo));
    }


    // Profesores

    @PostMapping("/crear/profesor")
    public ResponseEntity crearProfesor(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearProfesor");
        return facade.operation(json);
    }

    @DeleteMapping("/eliminar/profesor/{id}")
    public ResponseEntity eliminarProfesorById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_PROFESOR_BY_ID, id));
    }

    @DeleteMapping("/eliminar/profesorCodigo/{codigo}")
    public ResponseEntity eliminarProfesor(@PathVariable Long codigo){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_PROFESOR_BY_CODIGO, codigo));
    }

    //Administradores

    @PostMapping("/crear/administrador")
    public ResponseEntity crearAdministrador(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearProfesor");
        return facade.operation(json);
    }

    @DeleteMapping("/eliminar/administrador/{id}")
    public ResponseEntity eliminarAdministradorById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_ADMINISTRADOR_BY_ID, id));
    }

    @DeleteMapping("/eliminar/administradorCodigo/{codigo}")
    public ResponseEntity eliminarAdministradorByCodigo(@PathVariable Long codigo){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_ADMINISTRADOR_BY_CODIGO, codigo));
    }

    //Matriculas

    @PostMapping("/matricular")
    public ResponseEntity matricularEstudiante(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de matricularEstudiante");
        return facade.operation(json);
    }

    @PostMapping("/matricular/asignarProfesor")
    public ResponseEntity asignarProfesor(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de matricularEstudiante");
        return facade.operation(json);
    }

    //Tipos Documentos

    @PostMapping("/crear/tipoDocumento")
    public ResponseEntity crearTipoDocumento(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(json);
    }

    @GetMapping("/buscar/tipoDocumento/{id}")
    public ResponseEntity buscarTipoDocumentosByID(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarDocumentosById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_TD_BY_ID, id));
    }

    @GetMapping("/buscar/tipoDocumento")
    public ResponseEntity buscarTipoDocumentos(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarDocumentos");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_TDS, null));
    }

    @DeleteMapping("/eliminar/tipoDocumento/{id}")
    public ResponseEntity eliminarTipoDocumentosById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarTipoDocumentosById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_TD, id));
    }

    //Cursos

    @PostMapping("/crear/curso")
    public ResponseEntity crearCurso(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearCurso");
        return facade.operation(json);
    }

    @DeleteMapping("/eliminar/curso/{id}")
    public ResponseEntity eliminarCursoById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarCursoById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_CURSO_BY_ID, id));
    }

    @DeleteMapping("/eliminar/cursoCodigo/{codigo}")
    public ResponseEntity eliminarCursoById(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarCursoByCodigo");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_CURSO_BY_CODIGO, codigo));
    }

}
