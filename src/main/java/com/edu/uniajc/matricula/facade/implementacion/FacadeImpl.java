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
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

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
                JsonObject objectUsuario = objeto.get("usuario").getAsJsonObject();
                try{
                    Usuario usuario = usuarioService.buscarUsuarioByUsuarioandPassword(objectUsuario.get("usuario").getAsString(), objectUsuario.get("password").getAsString());
                    LOGGER.info("JSE --> El id del usuario es "+usuario.getId());
                    LOGGER.info("JSE --> El usuario es "+usuario.getUsuario());
                    LOGGER.info("JSE --> El password es "+usuario.getPassword());
                    Persona persona = Utilidades.validarObjeto(usuario) ? personaService.buscarPersonaByUsuario(usuario) : null;
                    Autenticacion autenticacion = Utilidades.validarObjeto(persona) ?
                        Utilidades.buildAutenticacion(jwtUtil.generateToken(usuario), Utilidades.buscarAuhtority(persona).getRol()) : null;
                    return Utilidades.validarResponse(autenticacion, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_CREAR_CARRERA: {
                try{
                    JsonObject objectCarrera = objeto.get("carrera").getAsJsonObject();
                    Carrera carrera = carreraService.crearCarrera(Utilidades.buildCarrera(objectCarrera));
                    return Utilidades.validarResponse(carrera, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_CARRERA_BY_ID: {
                try{
                    Carrera carrera = carreraService.buscarCarreraById(objeto.get("objeto").getAsLong());
                    return Utilidades.validarResponse(carrera, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_CARRERA: {
                try{
                    Carrera carrera = carreraService.eliminarCarrera(objeto.get("objeto").getAsLong());
                    return Utilidades.validarResponse(carrera, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_CREAR_AUTHORITY: {
                try{
                    Authority authority = authorityService.crearAuthority(Utilidades.buildAuthority(objeto));
                    return Utilidades.validarResponse(authority, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_AUTHORITY: {
                try{
                    Authority authority = authorityService.buscarAuthorityById(objeto.get("objeto").getAsLong());
                    return Utilidades.validarResponse(authority, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_AUTHORITIES: {
                try{
                    List<Authority> authorities = authorityService.buscarAuhtorities();
                    Listas listas = new Listas();
                    listas.setListaObjetos(authorities);
                    return Utilidades.validarListaResponse(listas, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_AUTHORITY: {
                try{
                    Authority authority = authorityService.eliminarAuthority(objeto.get("objeto").getAsLong());
                    return Utilidades.validarResponse(authority, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_CREAR_ESTUDIANTE: {
                try{
                    LOGGER.info("El objeto es "+objeto);
                    JsonObject objectPersona = objeto.get("persona").getAsJsonObject();
                    LOGGER.info("El objetoPersona es "+objectPersona);
                    JsonObject objectUsuario = objectPersona.get("usuario").getAsJsonObject();
                    Usuario usuario = usuarioService.crearUsuario(Utilidades.buildUsuario(objectUsuario));
                    LOGGER.info("El objetcoUsuario es "+objectUsuario);
                    JsonObject objectEstudiante = objectPersona.get("estudiante").getAsJsonObject();
                    LOGGER.info("El objetoEstudiante es "+objectEstudiante);
                    JsonObject objectCarrera = objectEstudiante.get("carrera").getAsJsonObject();
                    LOGGER.info("El objetoCarera es "+objectCarrera);
                    JsonObject objectAuthority = objectEstudiante.get("authority").getAsJsonObject();
                    LOGGER.info("El objectAuthority es "+objectAuthority);
                    String nombreCarrera = objectCarrera.get("nombreCarrera").getAsString();
                    Carrera carrera = carreraService.buscarCarreraByNombre(nombreCarrera);
                    LOGGER.info("El idCarrera es "+carrera.getId());
                    LOGGER.info("El nombreCarrera es "+carrera.getNombreCarrera());
                    String rolAuthority = objectAuthority.get("rol").getAsString();
                    Authority authority = authorityService.buscarAuthorityByRol(rolAuthority);
                    LOGGER.info("El idAuthority es "+authority.getId());
                    LOGGER.info("El rolAuthority es "+authority.getRol());
                    Estudiante estudiante = estudianteService.crearEstudiante(Utilidades.buildEstudiante(objectEstudiante, carrera, authority));
                    JsonObject objectTipoDocumento = objectPersona.get("tipoDocumento").getAsJsonObject();
                    LOGGER.info("JSE --> El objeto de tipo documento es "+ objectTipoDocumento);
                    String codigoDocumento = objectTipoDocumento.get("codigo").getAsString();
                    TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoByCodigo(codigoDocumento);
                    LOGGER.info("JSE --> El id del tipoDocumento es "+tipoDocumento.getId());
                    Persona persona = personaService.crearPersona(Utilidades.buildPersona(objectPersona, tipoDocumento,estudiante, null, null , usuario));
                    return Utilidades.validarResponse(persona, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_ESTUDIANTE_BY_ID: {
                try{
                    Estudiante estudiante = estudianteService.buscarEstudianteById(objeto.get("objeto").getAsLong());
                    Persona persona = personaService.buscarPersonaByEstudiante(estudiante);
                    return Utilidades.validarResponse(persona, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_BUSCAR_ESTUDIANTE_BY_CODIGO: {
                try{
                    Estudiante estudiante = estudianteService.buscarEstudianteByCodigo(objeto.get("objeto").getAsString());
                    Persona persona = personaService.buscarPersonaByEstudiante(estudiante);
                    return Utilidades.validarResponse(persona, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_ESTUDIANTE_BY_ID: {
                try{
                    Estudiante estudiante = estudianteService.eliminarEstudianteById(objeto.get("objeto").getAsLong());
                    Persona persona = personaService.eliminarPersonaByEstudiante(estudiante);
                    return Utilidades.validarResponse(persona, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_ELIMINAR_ESTUDIANTE_BY_CODIGO: {
                try{
                    Estudiante estudiante = estudianteService.buscarEstudianteByCodigo(objeto.get("objeto").getAsString());
                    Persona persona = personaService.eliminarPersonaByEstudiante(estudiante);
                    return Utilidades.validarResponse(persona, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
                    return ResponseEntity.status(500).body(respuesta);
                }
            }
            case Constantes.OPE_CREAR_TD: {
                try{
                    JsonObject objectTipoDocumento = objeto.get("tipoDocumento").getAsJsonObject();
                    TipoDocumento tipoDocumento = tipoDocumentoService.crearTipoDocumento(Utilidades.buildTipoDocumento(objectTipoDocumento));
                    return Utilidades.validarResponse(tipoDocumento, respuesta);
                }catch (Exception ex){
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage());
                    respuesta = Utilidades.buildRespuesta(Constantes.CODIGO_FALLO, Constantes.FALLO, ex.getMessage(), respuesta);
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
