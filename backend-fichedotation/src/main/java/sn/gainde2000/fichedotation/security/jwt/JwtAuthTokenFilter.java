package sn.gainde2000.fichedotation.security.jwt;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import sn.gainde2000.fichedotation.security.services.UserDetailsServiceImpl;
import sn.gainde2000.fichedotation.security.utils.AuthUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

/*
- JwtAuthTokenFilter s' étend OncePerRequestFilter .
org.springframework.web.filter.OncePerRequestFilter s' exécute une fois
par requête. Il s' agit d' une classe de base de ltre utilisée pour garantir une
exécution unique par envoi de requête. Il fournit une méthode doFilterInternal
avec HttpServletRequest et des HttpServletResponse arguments.
À l' intérieur de la JwtAuthTokenFilter classe, la doFilterInternal méthode:
* obtenir le jeton JWT de l' en-tête
* valider JWT
* analyser le nom d' utilisateur à partir du JWT validé
* charger les données de la table des utilisateurs, puis créer un objetd' authentication
* définir l' objet d' authentication sur contexte de sécurité
 */
public class JwtAuthTokenFilter extends OncePerRequestFilter {
    public static final Logger LOGGER = LogManager.getLogger(JwtAuthTokenFilter.class);

    @Autowired
    private JwtProvider tokenProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

            String jwt = AuthUtils.getJwt(request);
            if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
                //on extrait les infos de l'utilisateur
                String username = tokenProvider.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //on crée une Authentication token
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);


            }
        } catch (Exception e) {
            LOGGER.log(Level.valueOf("context"), e);

        }

        filterChain.doFilter(request, response);
    }

}
