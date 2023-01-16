package sn.gainde2000.fichedotation.services.implementations.administration.utilisateur;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.fichedotation.commons.utils.PasswordGenerator;
import sn.gainde2000.fichedotation.commons.utils.UtilityClass;
import sn.gainde2000.fichedotation.entities.QUtilisateur;
import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.entities.other.DisposableEmail;
import sn.gainde2000.fichedotation.exceptions.GenericApiException;
import sn.gainde2000.fichedotation.repositories.DisposableEmailRepository;
import sn.gainde2000.fichedotation.repositories.UtilisateurRepository;
import sn.gainde2000.fichedotation.services.interfaces.administration.utilisateur.IUtilisateur;
import sn.gainde2000.fichedotation.services.interfaces.shared.INotificationService;
import sn.gainde2000.fichedotation.web.dtos.mappers.UtilisateurMapper;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.UtilisateurDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.LoginFormDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;

import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class GestionUtilisateurImpl implements IUtilisateur {

    public final DisposableEmailRepository disposableEmailRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;
    private final INotificationService notificationService;
    private final PasswordEncoder encoder;

    @Transactional
    @Override
    public Response<Object> saveUtilisateur(UtilisateurDTO dto) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findUtilisateurByEmail(dto.getEmail());

        if (optionalUtilisateur.isPresent()) return Response.exception().setMessage("L'email saisi est dèjà utilisé !");

        this.validateProdvidedEmail(dto.getEmail());

        Utilisateur utilisateur = utilisateurMapper.map(dto);
        utilisateur.setPassword(encoder.encode(PasswordGenerator.GenerateRandomString()));
        utilisateur.setFirstLog(true);
        utilisateur.setStatus(true);
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        notificationService.createNewUserNotification(LoginFormDTO.builder().login(savedUtilisateur.getEmail()).password(savedUtilisateur.getPassword()).build(), "FIRST_CONNEXION", false);

        return Response.ok().setMessage("Merci de vérifier votre email, un lien de connexion vous sera envoyé!");
    }

    @Override
    public Response<Object> listUtilisateur(int page, int size, String filter) {
        BooleanBuilder builder = new BooleanBuilder();

        Page<Utilisateur> utilisateurPage;
        if (StringUtils.isNotBlank(filter)) {
            builder.andAnyOf(
                    QUtilisateur.utilisateur.prenom.containsIgnoreCase(filter),
                    QUtilisateur.utilisateur.nom.containsIgnoreCase(filter),
                    QUtilisateur.utilisateur.linkedProfil.libelle.containsIgnoreCase(filter),
                    QUtilisateur.utilisateur.email.containsIgnoreCase(filter),
                    QUtilisateur.utilisateur.adresse.containsIgnoreCase(filter)
            );
        }

        utilisateurPage = Objects.nonNull(builder.getValue()) ? utilisateurRepository.findAll(builder.getValue(), PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"))) : utilisateurRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));

        Response.PageMetadata pageMetadata = Response.PageMetadata.builder()
                .size(utilisateurPage.getSize())
                .number(utilisateurPage.getNumber())
                .totalElements(utilisateurPage.getTotalElements())
                .totalPages(utilisateurPage.getTotalPages())
                .build();

        return Response.ok().setPayload(utilisateurPage.getContent()).setMetadata(pageMetadata).setMessage("Liste des utilisateurs !");
    }

    @Override
    public Response<Object> getUtilisateur(Integer id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new GenericApiException("Utilisateur absent!"));

        return Response.ok().setPayload(utilisateur).setMessage("Utilisateur retrouvé avec succès!");
    }

    @Transactional
    @Override
    public Response<Object> editUtilisateur(Integer id, UtilisateurDTO dto) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

        if (optionalUtilisateur.isEmpty()) throw new GenericApiException("Utilisateur absent!");
        Utilisateur currentUser = optionalUtilisateur.get();
        currentUser.setNom(dto.getNom());
        currentUser.setPrenom(dto.getPrenom());
        currentUser.setTelephone(dto.getTelephone());
        currentUser.setAdresse(dto.getAdresse());
        utilisateurRepository.save(currentUser);
        return Response.ok().setMessage("Utilisateur modifié avec succès !");
    }

    @Transactional
    @Override
    public Response<Object> changeStatus(Integer id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new GenericApiException("Aucun utilisateur avec cet id"));
        utilisateur.setStatus(!utilisateur.getStatus());
        Utilisateur updatedUtilisateur = utilisateurRepository.save(utilisateur);
        notificationService.activationOrDeactivationOfAUser(updatedUtilisateur.getEmail(), updatedUtilisateur.getStatus());
        String message = Boolean.TRUE.equals(updatedUtilisateur.getStatus()) ? "Compte activé avec succès !" : "Compte désactivé avec succès !";
        return Response.ok().setPayload(id).setMessage(message);
    }

    @Transactional
    @Override
    public Response<Object> deleteUtilisateur(Integer id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);
        if (optionalUtilisateur.isPresent()) utilisateurRepository.deleteById(optionalUtilisateur.get().getId());
        else throw new GenericApiException("Utilisateur introuvable");
        return Response.ok().setPayload(id).setMessage("Utilisateur supprimé avec succès !");
    }

    private void validateProdvidedEmail(String email) {
        boolean isValidEmail = UtilityClass.isValidEmail(email);
        if (!isValidEmail) throw new GenericApiException("L'email fourni est invalide !");
        else {
            String domain = email.substring(email.indexOf("@") + 1);
            Optional<DisposableEmail> optionalDisposableEmail = disposableEmailRepository.findByDomain(domain);
            if (optionalDisposableEmail.isPresent())
                throw new GenericApiException("Domaine " + domain + " non autorisé !");
        }
    }
}
