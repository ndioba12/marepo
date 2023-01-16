package sn.gainde2000.fichedotation.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.repositories.UtilisateurRepository;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

/*
Il est simple à mettre en œuvre UserDetailsService et facile pour nous de
récupérer les informations d' authentication à l' aide d' une stratégie de
persistance:
- UserDetailsServiceImpl met en œuvre UserDetailsService et remplace la
loadUserByUsername() méthode.
loadUserByUsername recherche un enregistrement dans les tables de la base de
données des utilisateurs pour créer un UserDetails objet pour
l' authentication.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final HttpServletRequest request;
    private final UtilisateurRepository userRepository;
    private final LoginAttemptService loginAttemptService;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (loginAttemptService.isBlocked(username)) {
            throw new InternalAuthenticationServiceException("Vous avez dépassé le nombre de tentatives autorisées pour la connexion !");
        }

        Utilisateur user = userRepository.findUtilisateurByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Utilisateur introuvable avec -> nom d'utilisateur ou e-mail: " + username));

        return UserPrinciple.build(user);
    }
}
