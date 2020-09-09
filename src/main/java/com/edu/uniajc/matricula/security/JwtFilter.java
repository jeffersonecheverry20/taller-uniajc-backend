package com.edu.uniajc.matricula.security;

import com.edu.uniajc.matricula.entity.*;
import com.edu.uniajc.matricula.service.*;
import com.edu.uniajc.matricula.service.implementacion.AuthorityServiceImpl;
import com.edu.uniajc.matricula.service.implementacion.EstudianteServiceImpl;
import com.edu.uniajc.matricula.service.implementacion.UsuarioServiceImpl;
import com.edu.uniajc.matricula.util.Utilidades;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private IEstudianteService estudianteService;

    @Autowired
    private IAdministradorService administradorService;

    @Autowired
    private IProfesorService profesorService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPersonaService personaService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            token = requestTokenHeader.substring(7);
            try{
                username = jwtUtil.getUsernameFromToken(token);
            }catch (IllegalArgumentException ie){
                LOGGER.info(ie.getMessage());
            }catch (ExpiredJwtException je){
                LOGGER.info(je.getMessage());
            }
        } else {
            LOGGER.info("El token no inicia con Bearer");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            Usuario usuario = null;
            try{
                usuario = usuarioService.buscarUsuarioByUsuario(username);
                LOGGER.info("JSE --> El usuario es "+usuario.getUsuario());
                if(usuario != null && jwtUtil.validateToken(token, usuario)) {
                    List<Authority> authorities = new ArrayList<>();
                    Persona persona = personaService.buscarPersonaByUsuario(usuario);
                    Authority authority = Utilidades.buscarAuhtority(persona);
                    authorities.add(authority);
                    List<GrantedAuthority> grantedAuthorities = authorities.stream()
                            .map(a -> new SimpleGrantedAuthority(a.getRol()))
                            .collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, username, grantedAuthorities);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }catch (Exception ex){
                LOGGER.info(ex.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }


}
