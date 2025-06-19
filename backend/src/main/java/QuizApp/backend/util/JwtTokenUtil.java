package QuizApp.backend.util;

import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(UserDetails userdetails){
        return Jwts.builder()
        .setSubject(userdetails.getUsername())
        .claim("authorities",
            userdetails.getAuthorities().stream().map(
                GrantedAuthority::getAuthority
            ).collect(Collectors.toList())
        )
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+ expiration))
        .signWith(getSecretKey(),SignatureAlgorithm.HS256)
        .compact();
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder()
        .setSigningKey(getSecretKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false ;
        }
    }
}
