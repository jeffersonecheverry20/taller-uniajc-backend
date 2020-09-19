package com.edu.uniajc.matricula.facade.implementacion;

import com.edu.uniajc.matricula.entity.*;
import com.edu.uniajc.matricula.facade.Facade;
import com.edu.uniajc.matricula.modelo.Autenticacion;
import com.edu.uniajc.matricula.modelo.Listas;
import com.edu.uniajc.matricula.modelo.Respuesta;
import com.edu.uniajc.matricula.security.JwtUtil;
import com.edu.uniajc.matricula.service.*;
import com.edu.uniajc.matricula.util.Constantes;
import com.edu.uniajc.matricula.util.Utilidades;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FacadeImpl implements Facade {

    private static final Logger LOGGER = LoggerFactory.getLogger(FacadeImpl.class);

    @Autowired
    private ICarreraService carreraService;

    @Autowired
    private IAuthorityService authorityService;

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IEstudianteService estudianteService;

    @Autowired
    private ITipoDocumentoService tipoDocumentoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IProfesorService profesorService;

    @Autowired
    private IAdministradorService administradorService;

    @Autowired
    private ICursoService cursoService;

    @Autowired
    private INotaService notaService;

    @Autowired
    private IMatriculaAcademicaService matriculaAcademicaService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity operation(String json) {
        LOGGER.info("JSE --> El json es "+ json);
        JsonObject objeto = new JsonParser().parse(json).getAsJsonObject();
        String operacion = objeto.get("operacion").getAsString();
        LOGGER.info("JSE --> La operacion es "+ operacion);
        Respuesta respuesta = Respuesta.builder().build();
        ResponseEntity responseEntity;

        switch (operacion) {
            case Constantes.OPE_LOGIN: {
                JsonObject objectUsuario = objeto.get("objeto").getAsJsonObject();
                try{
                    Usuario usuario = usuarioService.buscarUsuarioByUsuarioandPassword(objectUsuario.get("usuario").getAsString(), objectUsuario.get("password").getAsString());
                    Persona persona = Utilidades.validarObjeto(usuario) ? personaService.buscarPersonaByUsuario(usuario) : null;
                    Autenticacion autenticacion = Utilidades.validarObjeto(persona) ?
                        Utilidades.buildAutenticacion(jwtUtil.generateToken(usuario), Utilidades.buscarAuhtority(persona).getRol()) : null;
                    return Utilidades.validarResponse(autenticacion, respuesta, Constantes.LOGEADO, Constantes.PASSWORDANDUSUARIO);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Autenticacion.class.getName(), Constantes.LOGEADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_CREAR_CARRERA: {
                try{
                    JsonObject objectCarrera = objeto.get("objeto").getAsJsonObject();
                    Carrera carrera = carreraService.crearCarrera(Utilidades.buildCarrera(objectCarrera));
                    return Utilidades.validarResponse(carrera, respuesta, Constantes.CREADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Carrera.class.getName(), Constantes.CREADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_CARRERA_BY_ID: {
                try{
                    Carrera carrera = carreraService.buscarCarreraById(objeto.get("objeto").getAsLong());
                    return Utilidades.validarResponse(carrera, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Carrera.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_CARRERAS: {
                try{
                    List<Carrera> carreras = carreraService.buscarCarreras();
                    Listas listas = new Listas();
                    listas.add(carreras);
                    return Utilidades.validarListaResponse(listas, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Carrera.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_CARRERA: {
                try{
                    Carrera carrera = carreraService.eliminarCarrera(objeto.get("objeto").getAsLong());
                    return Utilidades.validarResponse(carrera, respuesta, Constantes.ELIMINADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Carrera.class.getName(), Constantes.ELIMINADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_CREAR_AUTHORITY: {
                try{
                    JsonObject objectAuthority = objeto.get("objeto").getAsJsonObject();
                    Authority authority = authorityService.crearAuthority(Utilidades.buildAuthority(objectAuthority));
                    return Utilidades.validarResponse(authority, respuesta, Constantes.CREADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Authority.class.getName(), Constantes.CREADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_AUTHORITY: {
                try{
                    Authority authority = authorityService.buscarAuthorityById(objeto.get("objeto").getAsLong());
                    return Utilidades.validarResponse(authority, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Authority.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_AUTHORITIES: {
                try{
                    List<Authority> authorities = authorityService.buscarAuhtorities();
                    Listas listas = new Listas();
                    listas.setListaObjetos(authorities);
                    return Utilidades.validarListaResponse(listas, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Authority.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_AUTHORITY: {
                try{
                    Authority authority = authorityService.eliminarAuthority(objeto.get("objeto").getAsLong());
                    return Utilidades.validarResponse(authority, respuesta, Constantes.ELIMINADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Authority.class.getName(), Constantes.ELIMINADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_CREAR_ESTUDIANTE: {
                try{
                    LOGGER.info("El objeto es "+objeto);
                    JsonObject objectPersona = objeto.get("objeto").getAsJsonObject();
                    JsonObject objectUsuario = objectPersona.get("usuario").getAsJsonObject();
                    Usuario usuario = usuarioService.crearUsuario(Utilidades.buildUsuario(objectUsuario));
                    JsonObject objectEstudiante = objectPersona.get("estudiante").getAsJsonObject();
                    JsonObject objectCarrera = objectEstudiante.get("carrera").getAsJsonObject();
                    JsonObject objectAuthority = objectEstudiante.get("authority").getAsJsonObject();
                    String nombreCarrera = objectCarrera.get("nombreCarrera").getAsString();
                    Carrera carrera = carreraService.buscarCarreraByNombre(nombreCarrera);
                    String rolAuthority = objectAuthority.get("rol").getAsString();
                    Authority authority = authorityService.buscarAuthorityByRol(rolAuthority);
                    Estudiante estudiante = estudianteService.crearEstudiante(Utilidades.buildEstudiante(objectEstudiante, carrera, authority));
                    JsonObject objectTipoDocumento = objectPersona.get("tipoDocumento").getAsJsonObject();
                    String codigoDocumento = objectTipoDocumento.get("codigo").getAsString();
                    TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoByCodigo(codigoDocumento);
                    Persona persona = personaService.crearPersona(Utilidades.buildPersona(objectPersona, tipoDocumento,estudiante, null, null , usuario));
                    return Utilidades.validarResponse(persona, respuesta, Constantes.CREADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Estudiante.class.getName(), Constantes.CREADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_ESTUDIANTE_BY_ID: {
                try{
                    LOGGER.info("JSE --> El id es "+objeto.get("objeto").getAsLong());
                    Estudiante estudiante = estudianteService.buscarEstudianteById(objeto.get("objeto").getAsLong());
                    Persona persona = personaService.buscarPersonaByEstudiante(estudiante);
                    persona.setFechaNacimiento(Utilidades.formatDate(persona.getFechaNacimiento()));
                    LOGGER.info("JSE --> La fecha formateada es "+persona.getFechaNacimiento());
                    return Utilidades.validarResponse(persona, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Estudiante.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_ESTUDIANTE_BY_CODIGO: {
                try{
                    Estudiante estudiante = estudianteService.buscarEstudianteByCodigo(objeto.get("objeto").getAsString());
                    Persona persona = personaService.buscarPersonaByEstudiante(estudiante);
                    return Utilidades.validarResponse(persona, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Estudiante.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_ESTUDIANTES: {
                try{
                    List<Estudiante> estudiantes = estudianteService.buscarEstudiantes();
                    List<Persona> personas = estudiantes != null && !estudiantes.isEmpty() ?
                            estudiantes.stream().map(e -> {
                                try{
                                    Persona persona = personaService.buscarPersonaByEstudiante(e);
                                    persona.setFechaNacimiento(Utilidades.formatDate(persona.getFechaNacimiento()));
                                    LOGGER.info("JSE --> La fecha formateada es "+persona.getFechaNacimiento());
                                    return persona;
                                }catch (Exception ex){
                                    ex.printStackTrace();
                                    LOGGER.info(ex.getMessage());
                                }
                                return null;
                            }).collect(Collectors.toList()) : null;
                    Listas listas = new Listas();
                    listas.setListaObjetos(personas);
                    return Utilidades.validarListaResponse(listas, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Estudiante.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_ESTUDIANTE_BY_ID: {
                try{
                    Estudiante estudiante = estudianteService.buscarEstudianteById(objeto.get("objeto").getAsLong());
                    Persona persona = Utilidades.validarObjeto(estudiante) ? personaService.buscarPersonaByEstudiante(estudiante) : null;
                    if(Utilidades.validarObjeto(persona)){
                        personaService.eliminarPersonaByEstudiante(estudiante);
                        estudianteService.eliminarEstudianteById(estudiante.getId());
                        usuarioService.eliminarUsuarioById(persona.getUsuario().getId());
                    }
                    return Utilidades.validarResponse(persona, respuesta, Constantes.ELIMINADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Estudiante.class.getName(), Constantes.ELIMINADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_ESTUDIANTE_BY_CODIGO: {
                try{
                    Estudiante estudiante = estudianteService.buscarEstudianteByCodigo(objeto.get("objeto").getAsString());
                    Persona persona = Utilidades.validarObjeto(estudiante) ? personaService.buscarPersonaByEstudiante(estudiante) : null;
                    if(Utilidades.validarObjeto(persona)){
                        personaService.eliminarPersonaByEstudiante(estudiante);
                        estudianteService.eliminarEstudianteByCodigo(estudiante.getCodigoEstudiante());
                        usuarioService.eliminarUsuarioById(persona.getUsuario().getId());
                    }
                    return Utilidades.validarResponse(persona, respuesta, Constantes.ELIMINADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Estudiante.class.getName(), Constantes.ELIMINADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_CREAR_TD: {
                try{
                    JsonObject objectTipoDocumento = objeto.get("objeto").getAsJsonObject();
                    TipoDocumento tipoDocumento = tipoDocumentoService.crearTipoDocumento(Utilidades.buildTipoDocumento(objectTipoDocumento));
                    return Utilidades.validarResponse(tipoDocumento, respuesta, Constantes.CREADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, TipoDocumento.class.getName(), Constantes.CREADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_TD_BY_ID: {
                try{
                    LOGGER.info("JSE --> El id es "+objeto.get("objeto").getAsLong());
                    TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoById(objeto.get("objeto").getAsLong());
                    return Utilidades.validarResponse(tipoDocumento, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, TipoDocumento.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_TDS: {
                try{
                    List<TipoDocumento> tipoDocumentos = tipoDocumentoService.buscarTipoDocumentos();
                    Listas listas = new Listas();
                    listas.add(tipoDocumentos);
                    return Utilidades.validarListaResponse(listas, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, TipoDocumento.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_TD: {
                try{
                    LOGGER.info("JSE --> El id es "+objeto.get("objeto").getAsLong());
                    TipoDocumento tipoDocumento = tipoDocumentoService.eliminarTipoDocumento(objeto.get("objeto").getAsLong());
                    return Utilidades.validarResponse(tipoDocumento, respuesta, Constantes.ELIMINADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, TipoDocumento.class.getName(), Constantes.ELIMINADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_CREAR_PROFESOR: {
                try{
                    LOGGER.info("El objeto es "+objeto);
                    JsonObject objectPersona = objeto.get("objeto").getAsJsonObject();
                    LOGGER.info("El objetoPersona es "+objectPersona);
                    JsonObject objectUsuario = objectPersona.get("usuario").getAsJsonObject();
                    Usuario usuario = usuarioService.crearUsuario(Utilidades.buildUsuario(objectUsuario));
                    LOGGER.info("El objetcoUsuario es "+objectUsuario);
                    JsonObject objectProfesor = objectPersona.get("profesor").getAsJsonObject();
                    LOGGER.info("El objetoProfesor es "+objectProfesor);
                    JsonObject objectAuthority = objectProfesor.get("authority").getAsJsonObject();
                    LOGGER.info("El objectAuthority es "+objectAuthority);
                    String rolAuthority = objectAuthority.get("rol").getAsString();
                    Authority authority = authorityService.buscarAuthorityByRol(rolAuthority);
                    LOGGER.info("El idAuthority es "+authority.getId());
                    LOGGER.info("El rolAuthority es "+authority.getRol());
                    Profesor profesor = profesorService.crearProfesor(Utilidades.buildProfesor(objectProfesor, authority));
                    JsonObject objectTipoDocumento = objectPersona.get("tipoDocumento").getAsJsonObject();
                    LOGGER.info("JSE --> El objeto de tipo documento es "+ objectTipoDocumento);
                    String codigoDocumento = objectTipoDocumento.get("codigo").getAsString();
                    TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoByCodigo(codigoDocumento);
                    LOGGER.info("JSE --> El id del tipoDocumento es "+tipoDocumento.getId());
                    Persona persona = personaService.crearPersona(Utilidades.buildPersona(objectPersona, tipoDocumento,null, null, profesor , usuario));
                    return Utilidades.validarResponse(persona, respuesta, Constantes.CREADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Profesor.class.getName(), Constantes.CREADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            } case Constantes.OPE_BUSCAR_PROFESOR_BY_ID: {
                try{
                    LOGGER.info("JSE --> El id es "+objeto.get("objeto").getAsLong());
                    Profesor profesor = profesorService.buscarProfesorById(objeto.get("objeto").getAsLong());
                    Persona persona = personaService.buscarPersonaByProfesor(profesor);
                    return Utilidades.validarResponse(persona, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Profesor.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_PROFESOR_BY_CODIGO: {
                try{
                    LOGGER.info("JSE --> El id es "+objeto.get("objeto").getAsLong());
                    Profesor profesor = profesorService.buscarProfesorByCodigo(objeto.get("objeto").getAsString());
                    Persona persona = personaService.buscarPersonaByProfesor(profesor);
                    return Utilidades.validarResponse(persona, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Profesor.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_PROFESORES: {
                try{
                    List<Profesor> profesores = profesorService.buscarProfesores();
                    List<Persona> personas = profesores != null && !profesores.isEmpty() ?
                            profesores.stream().map(p -> {
                                try{
                                    return personaService.buscarPersonaByProfesor(p);
                                }catch (Exception ex){

                                }
                                return null;
                            }).collect(Collectors.toList()) : null;
                    Listas listas = new Listas();
                    listas.add(personas);
                    return Utilidades.validarListaResponse(listas, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Profesor.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_PROFESOR_BY_ID: {
                try{
                    Profesor profesor = profesorService.buscarProfesorById(objeto.get("objeto").getAsLong());
                    Persona persona = Utilidades.validarObjeto(profesor) ? personaService.buscarPersonaByProfesor(profesor) : null;
                    if(Utilidades.validarObjeto(persona)){
                        personaService.eliminarPersonaByProfesor(profesor);
                        profesorService.eliminarProfesorById(profesor.getId());
                        usuarioService.eliminarUsuarioById(persona.getUsuario().getId());
                    }
                    return Utilidades.validarResponse(persona, respuesta, Constantes.ELIMINADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Profesor.class.getName(), Constantes.ELIMINADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_PROFESOR_BY_CODIGO: {
                try{
                    Profesor profesor = profesorService.buscarProfesorByCodigo(objeto.get("objeto").getAsString());
                    Persona persona = Utilidades.validarObjeto(profesor) ? personaService.buscarPersonaByProfesor(profesor) : null;
                    if(Utilidades.validarObjeto(persona)){
                        personaService.eliminarPersonaByProfesor(profesor);
                        profesorService.eliminarProfesorByCodigo(profesor.getCodigoProfesor());
                        usuarioService.eliminarUsuarioById(persona.getUsuario().getId());
                    }
                    return Utilidades.validarResponse(persona, respuesta, Constantes.ELIMINADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Profesor.class.getName(), Constantes.ELIMINADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_CREAR_ADMINISTRADOR: {
                try{
                    LOGGER.info("El objeto es "+objeto);
                    JsonObject objectPersona = objeto.get("objeto").getAsJsonObject();
                    LOGGER.info("El objetoPersona es "+objectPersona);
                    JsonObject objectUsuario = objectPersona.get("usuario").getAsJsonObject();
                    Usuario usuario = usuarioService.crearUsuario(Utilidades.buildUsuario(objectUsuario));
                    LOGGER.info("El objetcoUsuario es "+objectUsuario);
                    JsonObject objectAdministrador = objectPersona.get("administrador").getAsJsonObject();
                    LOGGER.info("El objetoProfesor es "+objectAdministrador);
                    JsonObject objectAuthority = objectAdministrador.get("authority").getAsJsonObject();
                    LOGGER.info("El objectAuthority es "+objectAuthority);
                    String rolAuthority = objectAuthority.get("rol").getAsString();
                    Authority authority = authorityService.buscarAuthorityByRol(rolAuthority);
                    LOGGER.info("El idAuthority es "+authority.getId());
                    LOGGER.info("El rolAuthority es "+authority.getRol());
                    Administrador administrador = administradorService.crearAdministrador(Utilidades.buildAdministrador(objectAdministrador, authority));
                    JsonObject objectTipoDocumento = objectPersona.get("tipoDocumento").getAsJsonObject();
                    LOGGER.info("JSE --> El objeto de tipo documento es "+ objectTipoDocumento);
                    String codigoDocumento = objectTipoDocumento.get("codigo").getAsString();
                    TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoByCodigo(codigoDocumento);
                    LOGGER.info("JSE --> El id del tipoDocumento es "+tipoDocumento.getId());
                    Persona persona = personaService.crearPersona(Utilidades.buildPersona(objectPersona, tipoDocumento,null, administrador, null, usuario));
                    return Utilidades.validarResponse(persona, respuesta, Constantes.CREADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Administrador.class.getName(), Constantes.CREADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            } case Constantes.OPE_BUSCAR_ADMINISTRADOR_BY_ID: {
                try{
                    LOGGER.info("JSE --> El id es "+objeto.get("objeto").getAsLong());
                    Administrador administrador = administradorService.buscarAdministradorById(objeto.get("objeto").getAsLong());
                    //LOGGER.info("JSE --> El estudiante es "+administrador.toString());
                    Persona persona = personaService.buscarPersonaByAdministrador(administrador);
                    //LOGGER.info("JSE --> La persona es "+persona);
                    return Utilidades.validarResponse(persona, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Administrador.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_ADMINISTRADOR_BY_CODIGO: {
                try{
                    LOGGER.info("JSE --> El id es "+objeto.get("objeto").getAsLong());
                    Administrador administrador = administradorService.buscarAdministradorByCodigo(objeto.get("objeto").getAsString());
                    Persona persona = personaService.buscarPersonaByAdministrador(administrador);
                    return Utilidades.validarResponse(persona, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Administrador.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_ADMINISTRADORES: {
                try{
                    List<Administrador> administradores = administradorService.buscarAdministradores();
                    List<Persona> personas = administradores != null && !administradores.isEmpty() ?
                            administradores.stream().map(a -> {
                                try{
                                    return personaService.buscarPersonaByAdministrador(a);
                                }catch (Exception ex){

                                }
                                return null;
                            }).collect(Collectors.toList()) : null;
                    Listas listas = new Listas();
                    listas.add(personas);
                    return Utilidades.validarListaResponse(listas, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Administrador.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_ADMINISTRADOR_BY_ID: {
                try{
                    Administrador administrador = administradorService.buscarAdministradorById(objeto.get("objeto").getAsLong());
                    Persona persona = Utilidades.validarObjeto(administrador) ? personaService.buscarPersonaByAdministrador(administrador) : null;
                    if(Utilidades.validarObjeto(persona)){
                        personaService.eliminarPersonaByAdministrador(administrador);
                        administradorService.eliminarAdministradorById(administrador.getId());
                        usuarioService.eliminarUsuarioById(persona.getUsuario().getId());
                    }
                    return Utilidades.validarResponse(persona, respuesta, Constantes.ELIMINADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Administrador.class.getName(), Constantes.ELIMINADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_ADMINISTRADOR_BY_CODIGO: {
                try{
                    Administrador administrador = administradorService.buscarAdministradorByCodigo(objeto.get("objeto").getAsString());
                    Persona persona = Utilidades.validarObjeto(administrador) ? personaService.buscarPersonaByAdministrador(administrador) : null;
                    if(Utilidades.validarObjeto(persona)){
                        personaService.eliminarPersonaByAdministrador(administrador);
                        profesorService.eliminarProfesorByCodigo(administrador.getCodigoAdministrador());
                        usuarioService.eliminarUsuarioById(persona.getUsuario().getId());
                    }
                    return Utilidades.validarResponse(persona, respuesta, Constantes.ELIMINADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Administrador.class.getName(), Constantes.ELIMINADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_CREAR_CURSO: {
                try{
                    JsonObject objectCurso = objeto.get("objeto").getAsJsonObject();
                    Curso curso = cursoService.crearCurso(Utilidades.buildCurso(objectCurso));
                    return Utilidades.validarResponse(curso, respuesta, Constantes.CREADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Curso.class.getName(), Constantes.CREADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_CURSO_BY_ID: {
                try{
                    LOGGER.info("JSE --> El id es "+objeto.get("objeto").getAsLong());
                    Curso curso = cursoService.buscarCursoById(objeto.get("objeto").getAsLong());
                    return Utilidades.validarResponse(curso, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Curso.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_CURSO_BY_CODIGO: {
                try{
                    LOGGER.info("JSE --> El id es "+objeto.get("objeto").getAsString());
                    Curso curso = cursoService.buscarCursoByCodigo(objeto.get("objeto").getAsString());
                    return Utilidades.validarResponse(curso, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Curso.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_CURSOS: {
                try{
                    List<Curso> cursos = cursoService.buscarCursos();
                    Listas listas =  new Listas();
                    listas.add(cursos);
                    return Utilidades.validarListaResponse(listas, respuesta, Constantes.ENCONTRADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Curso.class.getName(), Constantes.ENCONTRADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_CURSO_BY_ID: {
                try{
                    LOGGER.info("JSE --> El id es "+objeto.get("objeto").getAsLong());
                    Curso curso = cursoService.eliminarCursoById(objeto.get("objeto").getAsLong());
                    return Utilidades.validarResponse(curso, respuesta, Constantes.ELIMINADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Curso.class.getName(), Constantes.ELIMINADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_CURSO_BY_CODIGO: {
                try{
                    LOGGER.info("JSE --> El id es "+objeto.get("objeto").getAsString());
                    Curso curso = cursoService.eliminarCursoByCodigo(objeto.get("objeto").getAsString());
                    return Utilidades.validarResponse(curso, respuesta, Constantes.ELIMINADO, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Curso.class.getName(), Constantes.ELIMINADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_MATRICULAR_ESTUDIANTE: {
                try{
                    //JsonObject
                    JsonObject objectCurso = objeto.get("curso").getAsJsonObject();
                    JsonObject objectEstudiante = objeto.get("estudiante").getAsJsonObject();

                    //Curso
                    String codigoCurso = objectCurso.get("codigoCurso").getAsString();
                    Curso curso = cursoService.buscarCursoByCodigo(codigoCurso);

                    //Estudiante
                    String codigoEstudiante = objectEstudiante.get("codigoEstudiante").getAsString();
                    Estudiante estudiante = estudianteService.buscarEstudianteByCodigo(codigoEstudiante);

                    //Nota
                    Nota nota = notaService.crearNota(Utilidades.buildNota(0.0, 0.0, 0.0, 0.0, 0.0, 0.0));

                    //Matricula Academica
                    MatriculaAcademica matriculaAcademica = matriculaAcademicaService.crearMatriculaAcademica(Utilidades.buildMatriculaAcademica(curso, estudiante, false, nota, null));
                    return Utilidades.validarResponse(matriculaAcademica, respuesta, Constantes.MATRICULA, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, MatriculaAcademica.class.getName(), Constantes.MATRICULA, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_PROFESOR_BY_CURSO: {
                try{
                    String codigoCurso = objeto.get("codigoCurso").getAsString();
                    Curso curso = cursoService.buscarCursoByCodigo(codigoCurso);
                    List<MatriculaAcademica> academicaListas = Utilidades.validarObjeto(curso) ? matriculaAcademicaService.buscarMatriculaAcademicaByCurso(curso) : null;
                    Listas listas = new Listas();
                    listas.add(academicaListas);
                    Profesor profesor = Utilidades.validarListaObjetos(listas) ? academicaListas.get(0).getProfesor() : null;
                    return Utilidades.validarResponse(profesor, respuesta, Constantes.MATRICULA, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, MatriculaAcademica.class.getName(), Constantes.CREADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_ESTUDIANTE_BY_CURSO: {
                try{

                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_ESTUDIANTES_BY_CURSO: {
                try{
                    String codigoCurso = objeto.get("codigoCurso").getAsString();
                    Curso curso = cursoService.buscarCursoByCodigo(codigoCurso);
                    List<MatriculaAcademica> matriculaAcademicas = matriculaAcademicaService.buscarMatriculaAcademicaByCurso(curso);
                    List<Estudiante> estudiantes = matriculaAcademicas != null && !matriculaAcademicas.isEmpty() ?
                            matriculaAcademicas.stream().map(ma -> ma.getEstudiante()).collect(Collectors.toList()) : null;
                    Listas listas = new Listas();
                    listas.setListaObjetos(estudiantes);
                    return Utilidades.validarListaResponse(listas, respuesta, Constantes.MATRICULA, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Profesor.class.getName(), Constantes.MATRICULA, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ASIGNAR_PROFESOR_CURSO: {
                try{
                    JsonObject objectCurso = objeto.get("curso").getAsJsonObject();
                    JsonObject objectProfesor = objeto.get("profesor").getAsJsonObject();
                    String codigoCurso = objectCurso.get("codigoCurso").getAsString();
                    String codigoProfesor = objectProfesor.get("codigoProfesor").getAsString();
                    Curso curso = cursoService.buscarCursoByCodigo(codigoCurso);
                    Profesor profesor = profesorService.buscarProfesorByCodigo(codigoProfesor);
                    LOGGER.info("El id del profesor es "+profesor.getId());
                    List<MatriculaAcademica> matriculaAcademicas = matriculaAcademicaService.buscarMatriculaAcademicaByCurso(curso);
                    matriculaAcademicas = matriculaAcademicas.stream().map(m -> {
                        try{
                            m.setProfesor(profesor);
                            return matriculaAcademicaService.crearMatriculaAcademica(m);
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                        return null;
                    }).collect(Collectors.toList());
                    Listas listas = new Listas();
                    listas.add(matriculaAcademicas);
                    return Utilidades.validarListaResponse(listas, respuesta, Constantes.MATRICULA, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Profesor.class.getName(), Constantes.MATRICULA, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_REASIGNAR_PROFESOR_CURSO: {

            }
            case Constantes.OPE_INGRESAR_NOTAS: {
                try{
                    JsonObject objectCurso = objeto.get("curso").getAsJsonObject();
                    JsonObject objectEstudiante = objeto.get("estudiante").getAsJsonObject();
                    Curso curso = cursoService.buscarCursoByCodigo(objectCurso.get("codigoCurso").getAsString());
                    Estudiante estudiante = estudianteService.buscarEstudianteByCodigo(objectEstudiante.get("codigoEstudiante").getAsString());
                    MatriculaAcademica matriculaAcademica = Utilidades.validarObjeto(curso) && Utilidades.validarObjeto(estudiante) ?
                            matriculaAcademicaService.buscarMatriculaAcademicaByCursoAndEstudiante(curso, estudiante) : null;
                    Nota nota = Utilidades.validarObjeto(matriculaAcademica) ? notaService.buscarNotaById(matriculaAcademica.getId()) : null;
                    nota = Utilidades.validarObjeto(nota) ? notaService.crearNota(nota) : null;
                    boolean aprobado = Utilidades.validarObjeto(nota) ? notaService.validarAprobacion(nota.getId()) : false ;
                    if(Utilidades.validarObjeto(matriculaAcademica)) matriculaAcademicaService.actualizarAprobacion(matriculaAcademica, aprobado);
                    return Utilidades.validarResponse(matriculaAcademica, respuesta, Constantes.MATRICULA, Constantes.OBJETO_NULL);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, String.format(Constantes.MENSAJE, Profesor.class.getName(), Constantes.CREADO, ex.getMessage()), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            default: {
                break;
            }
        }

        return null;
    }

}
