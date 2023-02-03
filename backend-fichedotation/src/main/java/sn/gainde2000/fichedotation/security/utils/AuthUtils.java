package sn.gainde2000.fichedotation.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sn.gainde2000.fichedotation.entities.Menu;
import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.security.services.UserPrinciple;
import sn.gainde2000.fichedotation.web.dtos.mappers.MenuMapper;
import sn.gainde2000.fichedotation.web.dtos.mappers.UtilisateurMapper;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.AuthenticatedUserInfosDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.MenuDTO;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Component
public class AuthUtils {
    private static final String REQUEST_HEADER_NAME = "Authorization";
    private static final String AUTH_HEADER_BEARER_NAME = "Bearer ";
    private static final String[] IP_HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"

            // you can add more matching headers here ...
    };

    private static UtilisateurMapper utilisateurMapper;
    private static MenuMapper menuMapper;

    @Autowired
    private AuthUtils( UtilisateurMapper utilisateurMapper, MenuMapper menuMapper) {
        AuthUtils.utilisateurMapper = utilisateurMapper;
        AuthUtils.menuMapper = menuMapper;
    }

    public static Optional<AuthenticatedUserInfosDTO> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
            return Optional.of(userPrincipal.getUserInfos());
        } else return Optional.empty();
    }

    public static List<MenuDTO> getMenusOfUser(Set<Menu> menus) throws NoResultException {
        return menus.stream()
                .map(menuMapper::menuToMenuDTO)
                .sorted(Comparator.comparingInt(MenuDTO::getPriorite))
                .collect(Collectors.toList());
    }

    public static AuthenticatedUserInfosDTO getAuthenticatedUserInfos(Utilisateur utilisateur) {
        return utilisateurMapper.utilisateurToAuthenticatedUserInfosDTO(utilisateur);
    }

    public static String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader(REQUEST_HEADER_NAME);

        if (authHeader != null && authHeader.startsWith(AUTH_HEADER_BEARER_NAME)) {
            return authHeader.replace(AUTH_HEADER_BEARER_NAME, "");
        }

        return null;
    }

    public static String getIpAddr(HttpServletRequest request) {
        for (String header : IP_HEADERS) {
            String value = request.getHeader(header);
            if (value == null || value.isEmpty()) {
                continue;
            }
            String[] parts = value.split("\\s*,\\s*");
            return parts[0];
        }
        return request.getRemoteAddr();
    }

}
