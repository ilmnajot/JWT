package com.example.jwt.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    long expirationTime = 25555_000_000L;
    String secret = "sdlkfjshlfdjhlaskdjfaslkdjfbaslkdjfbsakdj.fnaskdj.fNKSFNMSDFSDF";

    public String generateToken(String username) {
        Date expireDate = new Date(System.currentTimeMillis() + expirationTime);
        return Jwts

                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        String username = Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }
}

//    public static void main(String[] args) {
//        String token = generateToken("userlogini");
//        System.out.println(token);
//    }

