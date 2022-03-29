package co.udea.edu.mobe.oauth.social;

import co.udea.edu.mobe.entity.ClientEntity;
import co.udea.edu.mobe.model.ClientModel;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    String secret;

    @Value("${jwt.expiration}")
    int expiration;

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    public String generateToken(Authentication authentication) {
        ClientModel clientModel = (ClientModel) authentication.getPrincipal();
        return Jwts.builder().setSubject(clientModel.getEmail()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration)).compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException | UnsupportedJwtException | ExpiredJwtException | IllegalArgumentException | SignatureException e) {
            logger.error("Error comprobando el token");
            e.printStackTrace();
        }
        return false;
    }

}
