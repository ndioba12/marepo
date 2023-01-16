package sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

/*
- l' JwtResponse objet sera retourné par le serveur SpringBoot une fois
l' authentication réussie, il contient:
Jeton JWT
Type de schéma de jeton
Nom d' utilisateur
Tableau des autorités de l' utilisateur
 */
@Getter
@Setter
public class JwtResponseDTO implements Serializable {
    private static final long serialVersionUID = 1198657217675348413L;


    private final Collection<? extends GrantedAuthority> authorities;
    private String token;
    private String type = "Bearer";
    private String username;

    public JwtResponseDTO(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = accessToken;
        this.username = username;
        this.authorities = authorities;
    }
}
