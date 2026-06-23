package lk.tourism.tourism_api_2026.account.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lk.tourism.tourism_api_2026.api_secrets.JwtSecretConfigs;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class CustomJwtUtil {

    private final JwtSecretConfigs jwtSecretConfigs;

    public String generateToken(UserDetails userDetails) {
        // Expect a single role like ROLE_ADMIN in authorities
        String role = userDetails.getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_GUEST");

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(
                        ZonedDateTime.now().plusYears(50).toInstant()
                ))
                .signWith(
                        Keys.hmacShaKeyFor(jwtSecretConfigs.getSecretKey().getBytes()),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecretConfigs.getSecretKey().getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
        // In a real app, also check expiration and possibly more constraints.
    }

}