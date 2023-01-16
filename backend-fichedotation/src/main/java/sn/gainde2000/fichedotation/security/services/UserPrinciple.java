package sn.gainde2000.fichedotation.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.security.utils.AuthUtils;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.AuthenticatedUserInfosDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.MenuDTO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

/*
- UserPrinciple mettra en œuvre UserDetails .
UserPrinciple n' est pas utilisé directement par Spring Security à des ns de
sécurité.
Il stocke simplement les informations utilisateur qui sont ensuite encapsulées
dans des objets Authentication. Cela permet de stocker des informations
utilisateur non liées à la sécurité (telles que les adresses e-mail, les numéros de
téléphone, etc.).
 */
@Getter
@Setter
@Builder
public class UserPrinciple implements UserDetails, Serializable {
    private static final long serialVersionUID = -4621439128408948198L;


    private final Integer id;
    private final String name;
    private final String username;
    @JsonIgnore
    private final String password;
    //Renvoie les droits accordés à l'utilisateur. Impossible de revenir null.
    private final Collection<? extends GrantedAuthority> authorities;
    private AuthenticatedUserInfosDTO userInfos;
    private List<MenuDTO> menus;

    public UserPrinciple(Integer id, String name,
                         String username, String password,
                         Collection<? extends GrantedAuthority> authorities, AuthenticatedUserInfosDTO userInfos, List<MenuDTO> menus) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.userInfos = userInfos;
        this.menus = menus;
    }

    public static UserPrinciple build(Utilisateur user) {
        List<GrantedAuthority> authorities = Stream.of(user.getLinkedProfil()).map(profil ->
                new SimpleGrantedAuthority(profil.getCode())
        ).collect(Collectors.toList());

        List<MenuDTO> menus = AuthUtils.getMenusOfUser(user.getLinkedProfil().getMenus());
//        List<MenuDTO> menus = AuthUtils.getMenusOfUser(user.getLinkedProfil().getLinkedEntite());
        AuthenticatedUserInfosDTO userInfos = AuthUtils.getAuthenticatedUserInfos(user);

        return UserPrinciple.builder()
                .id(user.getId())
                .name(user.getPrenom() + user.getNom().toUpperCase())
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .userInfos(userInfos)
                .menus(menus)
                .build();
    }

    //Indique si le compte de l'utilisateur a expiré. Un compte expiré ne peut pas être authentifié.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //Indique si l'utilisateur est verrouillé ou déverrouillé. Un utilisateur verrouillé ne peut pas être authentifié.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //Indique si les informations d'identification (mot de passe) de l'utilisateur ont expiré.
    // Les informations d'identification expirées empêchent l'authentification.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //Indique si l'utilisateur est activé ou désactivé. Un utilisateur désactivé ne peut pas être authentifié.
    @Override
    public boolean isEnabled() {
        return userInfos.getStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }


    @Override
    public int hashCode() {
        /* ... */
        return 0;
    }


}
