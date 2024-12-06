package uz.pdp.WebAuto.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JWTService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${spring.jwt.accessTokenExpiration}")
    private String accessTokenExpiration;

    @Value("${spring.jwt.refreshTokenExpiration}")
    private String refreshTokenExpiration;

    public String extractUsername(@NotNull String token) {
        return extractClaims(token).getSubject();
    }

    public String accessToken(@NotNull String username, Set<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String refreshToken(@NotNull String username, Set<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Date extractExpiration(@NotNull String token) {
        return extractClaims(token).getExpiration();
    }

    public Claims extractClaims(@NotNull String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public Boolean isTokenExpired(@NotNull String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean isValid(@NotNull String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Set<String> extractRoles(String token) {
        Claims claims = extractClaims(token);
        List<?> roles = (List<?>) claims.get("roles");
        return roles.stream().map(Object::toString).collect(Collectors.toSet());
    }
}
