package com.okavango.parkingapi.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class JWTUtils {

    //prifixo do token
    public static final String JWT_BEARER = "Bearer ";

    //nome do cabeçalho de autorização
    public static final String JWT_AUTHORIZATION = "Authorization";

    // chave com 32 caracteres (esse e o minimo exigido)
    public static final String SECRET_KEY = "0123456789-0123456789-0123456789";

    // tempo de expiracao do token
    public static final Long EXPIRE_DAYS = 0L;
    public static final Long EXPIRE_HOURS = 0L;
    public static final Long EXPIRE_MINUTES = 10L;

    private JWTUtils() {
    }

    // criptografia da chave quando da geracao do token
    private static javax.crypto.SecretKey generateKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // expiracao do token
    private static Date toExpireDate(Date start) {
        LocalDateTime localDateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = localDateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    // gerador de token
    public static JWToken createToken(String username, String role) {

        // data de criacao do token
        Date issuedAt = new Date();
        Date limit = toExpireDate(issuedAt);

        // instrucoes para a geracao do token
        String token = Jwts.builder()
                .header().add("typ", "JWT").and()
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(limit)
                .signWith(generateKey())
                .claim("role", role)
                .compact();
        return new JWToken(token);
    }

    // retornar o payload
    private static Claims getClaimsFromToken(String token) {

        try {
            return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(refactorToken(token)).getPayload();

        } catch (JwtException ex) {
            log.error(String.format("Invalid token %s", ex.getMessage()));
        }
        return null;
    }

    // recuperar o username dentro do token
    public  static String getUserNameFromToke(String token){
        return getClaimsFromToken(token).getSubject();
    }


    // testar validade do token
    public static boolean isTokenValid(String token) {

        try {
            Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(refactorToken(token));
            return true;

        } catch (JwtException ex) {
            log.error(String.format("Invalid token %s", ex.getMessage()));
        }
        return false;
    }

    // remover a instrucao Bearer no corpo do token
    private static String refactorToken(String token){
        if (token.contains(JWT_BEARER)){
            return  token.substring(JWT_BEARER.length());
        }
        return token;
    }

}
