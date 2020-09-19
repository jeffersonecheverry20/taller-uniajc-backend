package com.edu.uniajc.matricula.controller;

import com.edu.uniajc.matricula.facade.Facade;
import com.edu.uniajc.matricula.modelo.Respuesta;
import com.edu.uniajc.matricula.util.Constantes;
import com.edu.uniajc.matricula.util.Utilidades;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/administrador")
@Api(tags = "Login")
public class AdministradorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdministradorController.class);

    @Autowired
    private Facade facade;

    //Carreras

    @PostMapping(value = "/crear/carrera")
    @ApiOperation(value = "CrearCarrera", httpMethod = "POST", notes = "Crea carrera")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Carrera creada"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity crearCarrera(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearCarrera");
        return facade.operation(json);
    }

    @DeleteMapping("/eliminar/carrera/{id}")
    @ApiOperation(value = "EliminarCarreraById", httpMethod = "DELETE", notes = "Elimina carerra por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Carrera eliminada"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity eliminarCarreraById(@PathVariable("id") Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarCarreraById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_CARRERA, id));
    }

    //Authorities
    @PostMapping("/crear/authority")
    @ApiOperation(value = "CrearAuthority", httpMethod = "POST", notes = "Crea authority")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Authority creada"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity crearAuthority(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearAuthority");
        return facade.operation(json);
    }

    @GetMapping("/buscar/authority/{id}")
    @ApiOperation(value = "BuscarAuthority", httpMethod = "GET", notes = "Busca authority por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Authority encontrada"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarAuhtority(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_AUTHORITY, id));
    }

    @GetMapping("/buscar/authority")
    @ApiOperation(value = "BuscarAuthorities", httpMethod = "GET", notes = "Busca todos los authorities")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Authorities encontrados"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarAuthorities(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarAuhtorities");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_AUTHORITIES, null));
    }

    @DeleteMapping("/eliminar/authority/{id}")
    @ApiOperation(value = "EliminarAuthority", httpMethod = "DELETE", notes = "Elimina el authority por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Authority eliminado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity eliminarAuhtority(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_AUTHORITY, id));
    }

    //Estudiantes

    @PostMapping("/crear/estudiante")
    @ApiOperation(value = "CrearEstudiante", httpMethod = "POST", notes = "Crea estudiante")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estudiante creado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity crearEstudiante(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearEstudiante");
        return facade.operation(json);
    }

    @DeleteMapping("/eliminar/estudiante/{id}")
    @ApiOperation(value = "EliminarEstudianteById", httpMethod = "DELETE", notes = "Elimina estudiante por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estudiante eliminado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity eliminarEstudianteById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_ESTUDIANTE_BY_ID, id));
    }

    @DeleteMapping("/eliminar/estudianteCodigo/{codigo}")
    @ApiOperation(value = "EliminarEstudianteByCodigo", httpMethod = "DELETE", notes = "Elimina estudiante por codigo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estudiante eliminado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity eliminarEstudianteByCodigo(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_ESTUDIANTE_BY_CODIGO, codigo));
    }


    // Profesores

    @PostMapping("/crear/profesor")
    @ApiOperation(value = "CrearProfesor", httpMethod = "POST", notes = "Crea profesor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profesor creado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity crearProfesor(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearProfesor");
        return facade.operation(json);
    }

    @DeleteMapping("/eliminar/profesor/{id}")
    @ApiOperation(value = "EliminarProfesorById", httpMethod = "DELETE", notes = "Elimina profesor por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profesor eliminado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity eliminarProfesorById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_PROFESOR_BY_ID, id));
    }

    @DeleteMapping("/eliminar/profesorCodigo/{codigo}")
    @ApiOperation(value = "EliminarProfesorByCodigo", httpMethod = "DELETE", notes = "Elimina profesor por codigo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profesor eliminado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity eliminarProfesor(@PathVariable Long codigo){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_PROFESOR_BY_CODIGO, codigo));
    }

    //Administradores

    @PostMapping("/crear/administrador")
    @ApiOperation(value = "CrearAdministardor", httpMethod = "POST", notes = "Crea administrador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Administrador creado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity crearAdministrador(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearProfesor");
        return facade.operation(json);
    }

    @DeleteMapping("/eliminar/administrador/{id}")
    @ApiOperation(value = "EliminarAdministardorById", httpMethod = "DELETE", notes = "Elimina administrador por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Administrador eliminado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity eliminarAdministradorById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_ADMINISTRADOR_BY_ID, id));
    }

    @DeleteMapping("/eliminar/administradorCodigo/{codigo}")
    @ApiOperation(value = "EliminarAdministardorByCodigo", httpMethod = "DELETE", notes = "Elimina administrador por codigo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Administrador eliminado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity eliminarAdministradorByCodigo(@PathVariable Long codigo){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_ADMINISTRADOR_BY_CODIGO, codigo));
    }

    //Matriculas

    @PostMapping("/matricular")
    @ApiOperation(value = "Matricular", httpMethod = "POST", notes = "Matricula estudiantes a cursos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estudiante matriculado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity matricularEstudiante(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de matricularEstudiante");
        return facade.operation(json);
    }

    @PostMapping("/matricular/asignarProfesor")
    @ApiOperation(value = "AsignarProfesor", httpMethod = "POST", notes = "Asigna Profesor a los cursos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profesor asignado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity asignarProfesor(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de matricularEstudiante");
        return facade.operation(json);
    }

    //Tipos Documentos

    @PostMapping("/crear/tipoDocumento")
    @ApiOperation(value = "CrearTipoDocumento", httpMethod = "POST", notes = "Crea el tipo documento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tipo Documento creado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity crearTipoDocumento(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarAuhtority");
        return facade.operation(json);
    }

    @DeleteMapping("/eliminar/tipoDocumento/{id}")
    @ApiOperation(value = "EliminarTipoDocumentoById", httpMethod = "DELETE", notes = "Eliminar el tipo documento por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tipo Documento eliminado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity eliminarTipoDocumentosById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarTipoDocumentosById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_TD, id));
    }

    //Cursos

    @PostMapping("/crear/curso")
    @ApiOperation(value = "CrearCurso", httpMethod = "POST", notes = "Crea el curso")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Curso creado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity crearCurso(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller de crearCurso");
        return facade.operation(json);
    }

    @DeleteMapping("/eliminar/curso/{id}")
    @ApiOperation(value = "EliminarCursoById", httpMethod = "DELETE", notes = "Elimina el curso por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Curso eliminado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity eliminarCursoById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarCursoById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_CURSO_BY_ID, id));
    }

    @DeleteMapping("/eliminar/cursoCodigo/{codigo}")
    @ApiOperation(value = "EliminarCursoByCodigo", httpMethod = "DELETE", notes = "Elimina el curso por codigo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Curso eliminado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity eliminarCursoById(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de eliminarCursoByCodigo");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_ELIMINAR_CURSO_BY_CODIGO, codigo));
    }

}
