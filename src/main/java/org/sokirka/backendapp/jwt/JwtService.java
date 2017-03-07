package org.sokirka.backendapp.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.sokirka.backendapp.entities.Role;
import org.sokirka.backendapp.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
            user.setPassWord((String) body.get("role"));

            return user;
        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUserName());
        claims.put("passWord", user.getPassWord());
        claims.put("userId", user.getId() + "");
        claims.put("role", new Role());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
