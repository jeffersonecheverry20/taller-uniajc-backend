package com.edu.uniajc.matricula.util;

import com.edu.uniajc.matricula.entity.*;
import com.edu.uniajc.matricula.modelo.Autenticacion;
import com.edu.uniajc.matricula.modelo.Listas;
import com.edu.uniajc.matricula.modelo.Request;
import com.edu.uniajc.matricula.modelo.Respuesta;
import com.edu.uniajc.matricula.security.JwtUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Utilidades {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utilidades.class);

    @Autowired
    private JwtUtil jwtUtil;

    public static Carrera buildCarrera(JsonObject objeto) {
        return Carrera.builder()
                .nombreCarrera(objeto.get("nombreCarrera").getAsString())
                .totalCreditos(objeto.get("totalCreditos").getAsInt())
                .build();
    }

    public static TipoDocumento buildTipoDocumento(JsonObject objeto) {
        return TipoDocumento.builder()
                .codigo(objeto.get("codigo").getAsString())
                .valor(objeto.get("valor").getAsString())
                .build();
    }

    public static Authority buildAuthority(JsonObject object){
        return Authority.builder()
                .rol(object.get("rol").getAsString())
                .build();
    }

    public static Persona buildPersona(JsonObject object, TipoDocumento tipoDocumento, Estudiante estudiante, Administrador administrador, Profesor profesor, Usuario usuario){
        JsonObject objectTipoDocumento = object.get("tipoDocumento").getAsJsonObject();
        Date fecha = convertStringToDate(object.get("fechaNacimiento").getAsString());
        return Persona.builder()
                .nombre(object.get("nombre").getAsString())
                .apellido(object.get("apellido").getAsString())
                .tipoDocumento(tipoDocumento)
                .numeroDocumento(object.get("numeroDocumento").getAsString())
                .telefono(object.get("telefono").getAsString())
                .fechaNacimiento(fecha)
                .estudiante(estudiante)
                .profesor(profesor)
                .administrador(administrador)
                .usuario(usuario)
                .build();
    }

    public static Estudiante buildEstudiante(JsonObject object,  Carrera carrera, Authority authority){
        return Estudiante.builder()
                .codigoEstudiante(object.get("codigoEstudiante").getAsString())
                .creditosAprobados(object.get("creditosAprobados").getAsInt())
                .semestreActual(object.get("semestreActual").getAsString())
                .carrera(carrera)
                .authority(authority)
                .build();
    }

    public static Usuario buildUsuario(JsonObject object){
        return Usuario.builder()
                .usuario(object.get("usuario").getAsString())
                .password(object.get("password").getAsString())
                .build();
    }

    public static Profesor buildProfesor(JsonObject object, Authority authority){
        return Profesor.builder()
                .codigoProfesor(object.get("codigoProfesor").getAsString())
                .authority(authority)
                .build();
    }

    public static Administrador buildAdministrador(JsonObject object, Authority authority){
        return Administrador.builder()
                .codigoAdministrador(object.get("codigoAdministrador").getAsString())
                .authority(authority)
                .build();
    }

    public static Curso buildCurso(JsonObject object){
        LOGGER.info("JSE --> El jsonObject en buildCurso es "+object);
        return Curso.builder()
                .codigoCurso(object.get("codigoCurso").getAsString())
                .creditos(object.get("creditos").getAsInt())
                .grupoCurso(object.get("grupoCurso").getAsString())
                .descripcion(object.get("descripcion").getAsString())
                .limiteEstudiantes(object.get("limiteEstudiantes").getAsInt())
                .nombreCurso(object.get("nombreCurso").getAsString())
                .build();
    }

    public static Nota buildNota(double p1, double p2, double p3, double quiz, double proyecto, double definitiva){
        return Nota.builder()
                .parcialI(p1)
                .parcialII(p2)
                .parcialIII(p3)
                .quiz(quiz)
                .proyecto(proyecto)
                .definitiva(definitiva)
                .build();
    }

    public static MatriculaAcademica buildMatriculaAcademica(Curso curso, Estudiante estudiante, boolean aprobado, Nota nota, Profesor profesor){
        return MatriculaAcademica.builder()
                .curso(curso)
                .estudiante(estudiante)
                .aprobado(aprobado)
                .nota(nota)
                .profesor(profesor)
                .build();
    }

    public static Respuesta buildRespuesta(String codigo, String mensaje, Object objeto, Respuesta respuesta) {
        return Respuesta.builder()
                .codigo(codigo)
                .mensaje(mensaje)
                .objeto(objeto)
                .build();
    }

    public static Autenticacion buildAutenticacion(String token, String rol) {
        return Autenticacion.builder()
                .token(token)
                .rol(rol)
                .build();
    }

    public static String buildObject(String operacion, Object objeto) {
        Gson gson = new Gson();
        Request request = Request.builder()
                .operacion(operacion)
                .objeto(objeto)
                .build();
        return gson.toJson(request);
    }

    public static ResponseEntity validarResponse(Object objeto, Respuesta respuesta){
        if(objeto == null){
            respuesta = buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, "", respuesta);
            return ResponseEntity.status(500).body(respuesta);
        }
        respuesta = buildRespuesta(Constantes.CODIGO_EXITOSO, Constantes.EXITO, objeto, respuesta);
        return ResponseEntity.status(200).body(respuesta);
    }

    public static ResponseEntity validarListaResponse(Listas objetos, Respuesta respuesta){
        if(objetos == null || objetos.getListaObjetos() == null || objetos.getListaObjetos().isEmpty()){
            respuesta = buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, "", respuesta);
            return ResponseEntity.status(500).body(respuesta);
        }
        respuesta = buildRespuesta(Constantes.CODIGO_EXITOSO, Constantes.EXITO, objetos.getListaObjetos(), respuesta);
        return ResponseEntity.status(200).body(respuesta);
    }

    public static boolean validarObjeto(Object object) {
        return object != null;
    }

    public static boolean validarListaObjetos(Listas objetos){
        return objetos.getListaObjetos() != null && !objetos.getListaObjetos().isEmpty();
    }

    public static Authority buscarAuhtority(Persona persona) {
        //LOGGER.info("JSE --> El estudiante de la persona es "+persona.getEstudiante().toString());
        //LOGGER.info("JSE --> El profesor de la persona es "+persona.getProfesor().toString());
        //LOGGER.info("JSE --> El administrador de la persona es "+persona.getAdministrador().toString());
        LOGGER.info("La persona en utilidades es "+persona.getNombre());
        LOGGER.info("La persona es diferente de null "+(persona.getEstudiante() != null));
        //LOGGER.info("El codigo del estudiante es "+persona.getEstudiante().getCodigoEstudiante());
        Authority authority = null;
        if(persona.getEstudiante() != null){
            Estudiante estudiante = persona.getEstudiante();
            authority = estudiante.getAuthority();
        } else if(persona.getProfesor() != null){
            Profesor profesor = persona.getProfesor();
            authority = profesor.getAuthority();
        } else if(persona.getAdministrador() != null){
            LOGGER.info("JSE --> Entro administrador");
            LOGGER.info("JSE --> El administrador es "+persona.getAdministrador().toString());
            Administrador administrador = persona.getAdministrador();
            authority = administrador.getAuthority();
        }
        return authority;
    }

    public static Date convertStringToDate(String fechaString){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try{
            fecha = formato.parse(fechaString);
        }catch (ParseException pe){
            LOGGER.error(pe.getMessage());
        }
        return fecha;
    }

}
