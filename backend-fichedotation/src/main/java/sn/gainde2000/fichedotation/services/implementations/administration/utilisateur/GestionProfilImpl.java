package sn.gainde2000.fichedotation.services.implementations.administration.utilisateur;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.gainde2000.fichedotation.commons.utils.PasswordValidator;
import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.exceptions.GenericApiException;
import sn.gainde2000.fichedotation.repositories.UtilisateurRepository;
import sn.gainde2000.fichedotation.security.utils.AuthUtils;
import sn.gainde2000.fichedotation.services.interfaces.administration.utilisateur.IGestionProfil;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.EditMonCompteDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.UpdatePasswordDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.AuthenticatedUserInfosDTO;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GestionProfilImpl implements IGestionProfil {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder encoder;

    @Override
    public Response<Object> getConnectedUser() {
        AuthenticatedUserInfosDTO infosDTO = AuthUtils.getAuthenticatedUser().orElseThrow(() -> new GenericApiException("L'utilisateur ne s'est pas authentifié"));
        return Response.ok().setPayload(infosDTO).setMessage("Utilisateur recupere avec succès !");
    }

    @Override
    @Transactional
    public Response<Object> updateUserProfil(EditMonCompteDTO dto) {
        AuthenticatedUserInfosDTO infosDTO = AuthUtils.getAuthenticatedUser().orElseThrow(() -> new GenericApiException("L'utilisateur ne s'est pas authentifié"));
        Utilisateur connectedUser = utilisateurRepository.findById(infosDTO.getId()).orElseThrow(() -> new GenericApiException("Utilisateur introuvable !"));
        connectedUser.setNom(dto.getNom());
        connectedUser.setAdresse(dto.getAdresse());
        connectedUser.setPrenom(dto.getPrenom());
        connectedUser.setTelephone(dto.getTelephone());

        utilisateurRepository.save(connectedUser);

        return Response.ok().setMessage("Profil modifié avec succès !");
    }

    @Transactional
    @Override
    public Response<Object> updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        if (!new PasswordValidator().validate(updatePasswordDTO.getNewPassword()))
            return Response.badRequest().setMessage("Le mot de passe n'est pas valide !");

        AuthenticatedUserInfosDTO infosDTO = AuthUtils.getAuthenticatedUser().orElseThrow(() -> new GenericApiException("L'utilisateur ne s'est pas authentifié"));
        Optional<Utilisateur> user = utilisateurRepository.findById(infosDTO.getId());

        if (user.isPresent()) {
            Utilisateur utilisateur = user.get();
            if (!encoder.matches(updatePasswordDTO.getPrevPassword(), utilisateur.getPassword())) {
                return Response.badRequest().setMessage("L'ancien mot de passe est incorrect !");
            }

            if (encoder.matches(updatePasswordDTO.getNewPassword(), utilisateur.getPassword())) {
                return Response.badRequest().setMessage("Le nouveau et l'ancien mot de passe sont identiques !");
            }

            if (!updatePasswordDTO.getConfPassword().equalsIgnoreCase(updatePasswordDTO.getNewPassword())) {
                return Response.badRequest().setMessage("Les deux mot de passe ne correspondent pas !");
            }

            utilisateur.setPassword(encoder.encode(updatePasswordDTO.getNewPassword()));
            utilisateurRepository.save(utilisateur);
            return Response.ok().setMessage("Mot de passe modifié avec succés!");
        }
        return Response.notFound().setMessage("Utilisateur introuvable!");
    }
}
