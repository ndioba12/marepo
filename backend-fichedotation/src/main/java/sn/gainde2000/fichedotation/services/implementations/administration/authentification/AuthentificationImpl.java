package sn.gainde2000.fichedotation.services.implementations.administration.authentification;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.fichedotation.commons.utils.PasswordGenerator;
import sn.gainde2000.fichedotation.commons.utils.PasswordValidator;
import sn.gainde2000.fichedotation.commons.utils.UtilityClass;
import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.entities.other.DisposableEmail;
import sn.gainde2000.fichedotation.exceptions.GenericApiException;
import sn.gainde2000.fichedotation.repositories.DisposableEmailRepository;
import sn.gainde2000.fichedotation.repositories.UtilisateurRepository;
import sn.gainde2000.fichedotation.security.jwt.JwtProvider;
import sn.gainde2000.fichedotation.security.services.LoginAttemptService;
import sn.gainde2000.fichedotation.security.utils.AuthUtils;
import sn.gainde2000.fichedotation.services.interfaces.administration.authentification.IAuthentification;
import sn.gainde2000.fichedotation.services.interfaces.shared.INotificationService;
import sn.gainde2000.fichedotation.web.dtos.mappers.UtilisateurMapper;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.UtilisateurDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.EditMonCompteDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.LoginFormDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.ResetOrForgetFormDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.AuthenticatedUserInfosDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.JwtResponseDTO;

import java.util.Optional;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Service
@RequiredArgsConstructor
public class AuthentificationImpl implements IAuthentification {
    private static final String LOGIN_OU_MOT_DE_PASSE_INCORRECT = "Login ou mot de passe incorrect !";
    private static final String COMPTE_BLOQUE = "Votre compte est bloqué, veuillez contacter l'administrateur !";
    public final DisposableEmailRepository disposableEmailRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final UtilisateurMapper utilisateurMapper;
    private final INotificationService notificationService;
    private final LoginAttemptService loginAttemptService;

    @Transactional
    @Override
    public Response<Object> registerUtilisateur(UtilisateurDTO utilisateurDTO) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findUtilisateurByEmail(utilisateurDTO.getEmail());

        boolean isValidEmail = UtilityClass.isValidEmail(utilisateurDTO.getEmail());
        if (!isValidEmail) throw new GenericApiException("L'email fourni est invalide !");
        else {
            String domain = utilisateurDTO.getEmail().substring(utilisateurDTO.getEmail().indexOf("@") + 1);
            Optional<DisposableEmail> optionalDisposableEmail = disposableEmailRepository.findByDomain(domain);
            if (optionalDisposableEmail.isPresent())
                throw new GenericApiException("Domaine " + domain + " non autorisé !");
        }

        if (optionalUtilisateur.isPresent())
            return Response.exception().setMessage("Cet email correspond à un compte !");

        Utilisateur utilisateur = utilisateurMapper.map(utilisateurDTO);
        String generatedPassword = PasswordGenerator.GenerateRandomString();
        utilisateur.setPassword(encoder.encode(generatedPassword));
        utilisateur.setFirstLog(true);
        utilisateur.setStatus(true);
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        notificationService.createNewUserNotification(LoginFormDTO.builder().login(savedUtilisateur.getEmail()).password(generatedPassword).build(), "FIRST_CONNEXION", true);
        return Response.ok().setPayload(savedUtilisateur).setMessage("Un lien de connexion vous a été envoyé par mail");
    }

    @Override
    public Response<Object> authenticateUser(LoginFormDTO loginRequest) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String jwt = jwtProvider.generateJwtToken(authentication);
            JwtResponseDTO response = new JwtResponseDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());

            loginAttemptService.loginSucceeded(loginRequest.getLogin());
            return Response.ok().setPayload(response).setMessage("Authentification réussie");
        } catch (BadCredentialsException e) {
            loginAttemptService.loginFailed(loginRequest.getLogin());
            return Response.wrongCredentials().setMessage(LOGIN_OU_MOT_DE_PASSE_INCORRECT);
        } catch (DisabledException e) {
            return Response.disabledAccount().setMessage(COMPTE_BLOQUE);
        }
    }

    @Transactional
    @Override
    public Response<Object> authenticateUserWithFirstUrlConnexion(ResetOrForgetFormDTO formRequest) {
        try {
            if (new PasswordValidator().validate(formRequest.getNewPassword())) {
                Optional<Utilisateur> resp = utilisateurRepository.findUtilisateurByEmail(formRequest.getLogin());
                if (resp.isEmpty())
                    return Response.notFound().setMessage("Utilisateur introuvable avec -> login ou e-mail: " + formRequest.getLogin());

                Utilisateur user = resp.get();
                if (Boolean.FALSE.equals(user.getFirstLog()))
                    return Response.badRequest().setMessage("La réinitialisation du mot de passe a été déjà faite.");

                if (Boolean.FALSE.equals(user.getStatus()))
                    return Response.disabledAccount().setMessage(COMPTE_BLOQUE);

                Response<Object> response = updatePassword(formRequest);
                if (response.getStatus().equals(Response.Status.OK)) {
                    LoginFormDTO loginForm = LoginFormDTO.builder().login(formRequest.getLogin()).password(formRequest.getNewPassword()).build();
                    //  return Response.ok().setMessage("Veuillez-vous connecter avec vos nouveaux identifiants");
                    return authenticateUser(loginForm);
                }

                return response;
            }

            return Response.ok().setMessage("Mot de passe non robuste. Il doit contenir au moins 8 caractères avec un majuscule, un caractère spécial et un chiffre.");
        } catch (BadCredentialsException e) {
            loginAttemptService.loginFailed(formRequest.getLogin());
            return Response.wrongCredentials().setMessage(LOGIN_OU_MOT_DE_PASSE_INCORRECT);
        }
    }

    @Transactional
    @Override
    public Response<Object> authenticateUserWithForgetPasswordUrlConnexion(ResetOrForgetFormDTO formRequest) {
        if (new PasswordValidator().validate(formRequest.getNewPassword())) {
            Optional<Utilisateur> resp = utilisateurRepository.findUtilisateurByEmail(formRequest.getLogin());
            if (resp.isEmpty())
                Response.notFound().setMessage("Utilisateur introuvable avec -> login ou e-mail: " + formRequest.getLogin());

            if (resp.isPresent()) {
                Utilisateur user = resp.get();
                if (Boolean.FALSE.equals(user.getStatus()))
                    return Response.disabledAccount().setMessage(COMPTE_BLOQUE);
            }

            Response<Object> response = updatePassword(formRequest);

            if (response.getStatus().equals(Response.Status.OK)) {
                LoginFormDTO loginForm = LoginFormDTO.builder().login(formRequest.getLogin()).password(formRequest.getNewPassword()).build();
                //return Response.ok().setMessage("Veuillez-vous connecter avec vos nouveaux identifiants");

                return authenticateUser(loginForm);
            }

            return response;
        }
        return Response.ok().setMessage("Mot de passe non robuste. Il doit contenir au moins 8 caractères avec un majuscule, un caractère spécial et un chiffre.");
    }

    @Transactional
    @Override
    public Response<Object> updatePassword(ResetOrForgetFormDTO form) {
        if (!new PasswordValidator().validate(form.getNewPassword()))
            return Response.badRequest().setMessage("Le mot de passe n'est pas valide !");

        Optional<Utilisateur> resp = utilisateurRepository.findUtilisateurByEmail(form.getLogin());

        if (resp.isPresent()) {
            Utilisateur utilisateur = resp.get();
            if (encoder.matches(form.getNewPassword(), utilisateur.getPassword()))
                return Response.badRequest().setMessage("Le nouveau et l'ancien mot de passe sont identiques !");

            // if (!encoder.matches(form.getNewPassword(), utilisateur.getPassword())) throw new GenericApiException("L'ancien mot de fourni ne correspond au mot de passe généré par le système !");

            utilisateur.setPassword(encoder.encode(form.getNewPassword()));
            utilisateur.setFirstLog(false);

            Utilisateur updatedUser = utilisateurRepository.saveAndFlush(utilisateur);
            return Response.ok().setMessage("Mot de passe modifié avec succès !").setPayload(updatedUser);
        }
        return Response.notFound().setMessage("Utilisateur introuvable ou l'ancien mot de passe est incorrect !");
    }

    @Transactional
    @Override
    public Response<Object> initPassword(String login) {
        Optional<Utilisateur> resp = utilisateurRepository.findUtilisateurByEmail(login);
        if (resp.isPresent()) {
            Utilisateur utilisateur = resp.get();
            if (Boolean.FALSE.equals(utilisateur.getStatus()))
                return Response.disabledAccount().setMessage(COMPTE_BLOQUE);

            String newPassword = PasswordGenerator.GenerateRandomString();

            utilisateur.setPassword(encoder.encode(newPassword));
            Utilisateur updatedUser = utilisateurRepository.save(utilisateur);

            notificationService.forgetPasswordNotification(LoginFormDTO.builder().login(utilisateur.getEmail()).password(newPassword).build(), "RESET_PASSWORD");
            return Response.ok().setMessage("Un lien de réinitialisation vous a été envoyé par mail !").setPayload(updatedUser);
        }
        return Response.notFound().setMessage("Le login fournit n'existe pas !");
    }

    @Transactional
    @Override
    public Response<Object> editUserInfos(EditMonCompteDTO req) {
        AuthenticatedUserInfosDTO infosDTO = AuthUtils.getAuthenticatedUser().orElseThrow(() -> new GenericApiException("L'utilisateur ne s'est pas authentifié"));

        Optional<Utilisateur> resp = utilisateurRepository.findById(infosDTO.getId());
        if (resp.isPresent()) {
            Utilisateur user = resp.get();
            user.setPrenom(req.getPrenom());
            user.setNom(req.getNom());
            user.setTelephone(req.getTelephone());
            user.setAdresse(req.getAdresse());

            Utilisateur updatedUser = utilisateurRepository.save(user);
            return Response.ok().setMessage("Profil modifié avec succès").setPayload(updatedUser);
        }

        return Response.notFound().setMessage("Cet utilisateur n'existe pas dans la plateforme !");
    }

    @Transactional
    @Override
    public Response<Object> updatePasswordFromInterface(ResetOrForgetFormDTO form) {
        try {
            if (!new PasswordValidator().validate(form.getNewPassword()))
                return Response.badRequest().setMessage("Le mot de passe n'est pas valide !");

            Optional<Utilisateur> resp = utilisateurRepository.findUtilisateurByEmail(form.getLogin());
            if (resp.isPresent() && !encoder.matches(form.getPassword(), resp.get().getPassword()))
                return Response.badRequest().setMessage("Le mot de passe fournit ne correspond pas à l'ancien !");

            else if (resp.isPresent()) {
                Utilisateur utilisateur = resp.get();
                if (encoder.matches(form.getNewPassword(), utilisateur.getPassword()))
                    return Response.badRequest().setMessage("Le nouveau et l'ancien mot de passe sont identiques !");

                utilisateur.setPassword(encoder.encode(form.getNewPassword()));
                Utilisateur updatedUser = utilisateurRepository.save(utilisateur);
                return Response.ok().setMessage("Mot de passe modifié avec succès !").setPayload(updatedUser);
            }
            return Response.notFound().setMessage("Utilisateur introuvable ou l'ancien mot de passe est incorrect !");
        } catch (Exception e) {
            return Response.badRequest().setMessage("Erreur lors du changement du mot de passe !");
        }
    }

    @Override
    public Response<Object> checkIfEmailIsUsed(String email) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findUtilisateurByEmail(email);
        if (utilisateur.isPresent())
            return Response.duplicateEmail().setPayload(utilisateur.get()).setMessage("Cet email correspond à un compte !");
        else return Response.ok().setMessage("Aucun compte n'est lié à cet email !");
    }
}
