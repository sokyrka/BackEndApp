package org.sokirka.backendapp.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.sokirka.backendapp.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

import java.util.UUID;

/**
 * @author Eugine Sokirka
 */
@Service
public class JwtService {

    @Value("${jwt.token.secret}")
    private String secret;

    public User parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            User user = new User();
            user.setUserName(body.getSubject());
            user.setPassWord((String) body.get("passWord"));

            return user;
        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    public String generateToken(User user) {
        Date date = new Date();
        long time = date.getTime() + 60000l;
        Claims claims = Jwts
                .claims()
                .setSubject(user.getUserName())
                .setExpiration(new Date(time));
        claims.put("id", UUID.randomUUID().toString());
        claims.put("passWord", user.getPassWord());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
