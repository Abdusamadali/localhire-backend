package com.abdus.localhirebackend.utils;


import com.abdus.localhirebackend.entity.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET = "my_super_secure_jwt_secret_key_123456";

    public SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(AppUser user){
        return Jwts.builder().subject(user.getUsername())
                .claim("userId", user.getId()).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSecretKey())
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parser()
//                .setSigningKey(SECRET.getBytes(StandardCharsets.UTF_8))
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token).getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token, AppUser user) {
        String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token).getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }
}
