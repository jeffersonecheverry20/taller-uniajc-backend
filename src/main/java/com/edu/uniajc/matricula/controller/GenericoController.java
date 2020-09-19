package com.edu.uniajc.matricula.controller;

import com.edu.uniajc.matricula.facade.Facade;
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
@RequestMapping(value = "/generico")
@Api(tags = "Generico")
public class GenericoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericoController.class);

    @Autowired
    private Facade facade;

    //Estudiantes

    @GetMapping("/buscar/estudiante/{id}")
    @ApiOperation(value = "BuscarEstudianteById", httpMethod = "GET", notes = "Busca estudiante por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estudiante encontrado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarEstudianteById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarEstudianteById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_ESTUDIANTE_BY_ID, id));
    }


    @GetMapping("/buscar/estudianteCodigo/{codigo}")
    @ApiOperation(value = "BuscarEstudianteByCodigo", httpMethod = "GET", notes = "Busca estudiante por codigo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estudiante encontrado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarEstudianteByCodigo(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de buscarEstudianteByCodigo");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_ESTUDIANTE_BY_CODIGO, codigo));
    }

    @GetMapping("/buscar/estudiante")
    @ApiOperation(value = "BuscarEstudiante", httpMethod = "GET", notes = "Busca todos los estudiantes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estudiantes encontrados"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarEstudiantes(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarEstudiantes");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_ESTUDIANTES, null));
    }


    //Profesores

    @GetMapping("/buscar/profesor/{id}")
    @ApiOperation(value = "BuscarProfesorById", httpMethod = "GET", notes = "Busca profesor por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profesor encontrado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarProfesorById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarProfesorById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_PROFESOR_BY_ID, id));
    }

    @GetMapping("/buscar/profesorCodigo/{codigo}")
    @ApiOperation(value = "BuscarProfesorByCodigo", httpMethod = "GET", notes = "Busca profesor por codigo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profesor encontrado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarProfesorByCodigo(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de buscarProfesorByCodigo");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_PROFESOR_BY_CODIGO, codigo));
    }

    @GetMapping("/buscar/profesor")
    @ApiOperation(value = "BuscarProfesor", httpMethod = "GET", notes = "Busca todos los profesores")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profesores encontrados"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarProfesor(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarProfesor");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_PROFESORES, null));
    }

    //Administradores

    @GetMapping("/buscar/administrador/{id}")
    @ApiOperation(value = "BuscarAdministradorById", httpMethod = "GET", notes = "Busca administrador por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Administrador encontrado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarAdministadorById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarAdministradorById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_ADMINISTRADOR_BY_ID, id));
    }

    @GetMapping("/buscar/administradorCodigo/{codigo}")
    @ApiOperation(value = "BuscarAdministradorByCodigo", httpMethod = "GET", notes = "Busca administrador por codigo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Administrador encontrado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarAdministradorByCodigo(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de buscarAdministradorByCodigo");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_ADMINISTRADOR_BY_CODIGO, codigo));
    }

    @GetMapping("/buscar/administrador")
    @ApiOperation(value = "BuscarAdministrador", httpMethod = "GET", notes = "Busca todos los administrador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Administradores encontrados"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarAdministrador(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarAdministradores");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_ADMINISTRADORES, null));
    }

    //Carrera

    @GetMapping("/buscar/carrera/{id}")
    @ApiOperation(value = "BuscarCarreraById", httpMethod = "GET", notes = "Busca carrera por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Carrera encontrada"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarCarreraById(@PathVariable("id") Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarCarreraById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_CARRERA_BY_ID, id));
    }

    @GetMapping("/buscar/carrera")
    @ApiOperation(value = "BuscarCarrera", httpMethod = "GET", notes = "Busca todas las carreras")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Carreras encontradas"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarCarreras(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarCarreras");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_CARRERAS, null));
    }

    //Cursos

    @GetMapping("/buscar/curso/{id}")
    @ApiOperation(value = "BuscarCursoById", httpMethod = "GET", notes = "Busca curso por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Curso encontrado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarCursoById(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarCursoById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_CURSO_BY_ID, id));
    }

    @GetMapping("/buscar/cursoCodigo/{codigo}")
    @ApiOperation(value = "BuscarCarreraByCodigo", httpMethod = "GET", notes = "Busca curso por codigo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Curso encontrado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarCursoById(@PathVariable String codigo){
        LOGGER.info("JSE --> Ejecuto el controller de buscarCursoByCodigo");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_CURSO_BY_CODIGO, codigo));
    }

    @GetMapping("/buscar/curso")
    @ApiOperation(value = "BuscarCurso", httpMethod = "GET", notes = "Busca todos los cursos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cursos encontrados"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarCurso(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarCurso");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_CURSOS, null));
    }

    //Tipos Documentos

    @GetMapping("/buscar/tipoDocumento/{id}")
    @ApiOperation(value = "BuscarTipoDocumentoById", httpMethod = "GET", notes = "Busca el tipo documento por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tipo Documento encontrado"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarTipoDocumentosByID(@PathVariable Long id){
        LOGGER.info("JSE --> Ejecuto el controller de buscarDocumentosById");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_TD_BY_ID, id));
    }

    @GetMapping("/buscar/tipoDocumento")
    @ApiOperation(value = "BuscarTipoDocumentos", httpMethod = "GET", notes = "Busca todos los tipo documentos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tipos Documentos encontrados"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity buscarTipoDocumentos(){
        LOGGER.info("JSE --> Ejecuto el controller de buscarDocumentos");
        return facade.operation(Utilidades.buildObject(Constantes.OPE_BUSCAR_TDS, null));
    }

    // Matricula
    @PostMapping("/ingresarNotas")
    @ApiOperation(value = "IngresarNotas", httpMethod = "POST", notes = "Ingresa la notas por curso y estudiante")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Nota agregada"),
            @ApiResponse(code = 400, message = "Error del cliente"),
            @ApiResponse(code = 401, message = "No está autorizado"),
            @ApiResponse(code = 401, message = "Acceso prohibido, no tiene los privilegios"),
            @ApiResponse(code = 500, message = "Error servidor")
    })
    public ResponseEntity ingresarNotas(@RequestBody String json){
        return facade.operation(json);
    }


}
