package sn.gainde2000.fichedotation.security.jwt;

import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.fichedotation.security.services.UserPrinciple;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.mails.MailConnexionInfosDTO;

import java.util.Date;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

/*
- JwtProvider est une classe util -> il implémente des fonctions utiles:
générer un jeton JWT
valider un jeton JWT
analyser le nom d' utilisateur du jeton JWT
 */
@Component
public class JwtProvider {
    public static final Logger LOGGER = LogManager.getLogger(JwtProvider.class);


    @Value("${starterkit.app.jwtSecret}")
    private String jwtSecret;

    @Value("${starterkit.app.jwtExpiration}")
    private int jwtExpiration;

    @Transactional
    public String generateJwtToken(Authentication authentication) {

        //l'utilisateur courant qui s'est authentifié
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (long) jwtExpiration * 60 * 1000))
                .claim("menu", userPrincipal.getMenus())
                .claim("infos", userPrincipal.getUserInfos())
//                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Transactional
    public String generateJwtRefreshToken(String token) {

        Claims claims = getUserBodyFromJwtToken(token);
        //l'utilisateur courant qui s'est authentifié
        //  UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(claims.getSubject())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration * 60 * 1000))
                .claim("menu", claims.get("menu"))
                .claim("infos", claims.get("infos"))
//                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateJwtMailToken(MailConnexionInfosDTO infos) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration * 60 * 1000))
                .claim("infos", infos)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            //LOGGER.log(Level.valueOf("context"), e);
            return false;
        }

    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public String getProfilFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().get("infos").toString();
    }

    public Claims getUserBodyFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}
