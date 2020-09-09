package com.edu.uniajc.matricula.security;

import com.edu.uniajc.matricula.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    public String secret;

    @Value("${token.validate}")
    public long tokenValidate;

    public String getUsernameFromToken(String token) throws IOException {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws IOException {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) throws IOException {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public Date getExpirationDateFromToken(String token) throws IOException {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token) throws IOException {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(Usuario usuario) throws IOException {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, usuario.getUsuario());
    }

    public String doGenerateToken(Map<String, Object> claims, String subject) throws IOException {
        return Jwts.builder()
                .setId("softtekJWT")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidate * 1000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean validateToken(String token, Usuario usuario) throws IOException {
        final String username = getUsernameFromToken(token);
        return username.equals(usuario.getUsuario()) && !isTokenExpired(token);
    }

}
