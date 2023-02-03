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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


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
    @Transactional
    public Response<Object> saveUtilisateursFromExcel(List<UtilisateurDTO> usersList){

        //L'idée est d'ignorer tous les utilisateurs déjà enregistré dans la base de données.


        List<Utilisateur> users = utilisateurRepository.findAll();

        if(users.isEmpty()){
            for (UtilisateurDTO userDTO:usersList){
                Utilisateur newUser = utilisateurMapper.map(userDTO);
                newUser.setPassword(encoder.encode(PasswordGenerator.GenerateRandomString()));
                newUser.setFirstLog(true);
                newUser.setStatus(true);
                Utilisateur savedUser = utilisateurRepository.save(newUser);


                //notificationService.createNewUserNotification(LoginFormDTO.builder().login(savedUser.getEmail()).password(savedUser.getPassword()).build(),"FIRST_CONNEXION", false);
            }
            return Response.ok().setMessage("Les utilisateurs ont été chargés. Chaque utilisateur a reçu un lien de connexion à la plateforme par mail !");
        }

        List<String> userMails = users
                .stream()
                .map(user -> user.getEmail())
                .collect(Collectors.toList());

        //Map des UtilisateursDTO en Utilisateur, puis filtrage des utilisateurs à charger dont leur email n'est pas présent dans la liste des utilisateurs dans la base de données
        List<Utilisateur> usersToSave = usersList.stream()
                .map(userDTO -> {
                            Utilisateur mappedUser = utilisateurMapper.map(userDTO);
                            mappedUser.setFirstLog(true);
                            mappedUser.setPassword(encoder.encode(PasswordGenerator.GenerateRandomString()));
                            return mappedUser;
                        }
                )
                .filter(newUser -> !userMails.contains(newUser.getEmail()))
                .collect(Collectors.toList());

        if(usersToSave.isEmpty()) return Response.exception().setMessage("Tous ces utilisateurs dans le fichier ont déjà été chargés!");

        for(Utilisateur newUser:usersToSave){
            newUser.setPassword(encoder.encode(PasswordGenerator.GenerateRandomString()));
            newUser.setFirstLog(true);
            newUser.setStatus(true);
            Utilisateur savedUser = utilisateurRepository.save(newUser);
            // notificationService.createNewUserNotification(LoginFormDTO.builder().login(savedUser.getEmail()).password(savedUser.getPassword()).build(),"FIRST_CONNEXION", false);
        }
        return Response.ok().setMessage("Les utilisateurs ont été chargés. Chaque utilisateur a reçu un lien de connexion à la plateforme par mail !");
    }

    @Override
    public Response<Object> listUtilisateur(int page, int size, String filter) {
        BooleanBuilder builder = new BooleanBuilder();

        Page<Utilisateur> utilisateurPage;
        if (StringUtils.isNotBlank(filter)) {
            builder.andAnyOf(
                    QUtilisateur.utilisateur.matricule.containsIgnoreCase(filter),
                    QUtilisateur.utilisateur.prenom.containsIgnoreCase(filter),
                    QUtilisateur.utilisateur.nom.containsIgnoreCase(filter),
                    QUtilisateur.utilisateur.linkedProfil.libelle.containsIgnoreCase(filter),
                    QUtilisateur.utilisateur.email.containsIgnoreCase(filter),
                    QUtilisateur.utilisateur.linkedEntite.libelle.containsIgnoreCase(filter)
            );
        }

        utilisateurPage = Objects.nonNull(builder.getValue()) ? utilisateurRepository.findAll(builder.getValue(), PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"))) : utilisateurRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));


        Response.PageMetadata pageMetadata = Response.PageMetadata.builder()
                .size(utilisateurPage.getSize())
                .number(utilisateurPage.getNumber())
                .totalElements(utilisateurPage.getTotalElements())
                .totalPages(utilisateurPage.getTotalPages())
                .build();

        ;

        return Response
                .ok()
                .setPayload(
                        utilisateurPage.getContent()
                        .stream()
                        .map(utilisateur ->
                             UtilisateurDTO.builder()
                                     .id(utilisateur.getId())
                                     .matricule(utilisateur.getMatricule())
                                     .nom(utilisateur.getNom())
                                     .prenom(utilisateur.getPrenom())
                                     .email(utilisateur.getEmail())
                                     .status(utilisateur.getStatus())
                                     .linkedProfil(utilisateurMapper.map(utilisateur).getLinkedProfil())
                                     .linkedEntite(utilisateurMapper.map(utilisateur).getLinkedEntite())
                                     .build()
                        )
                        .collect(Collectors.toList())
                )
                .setMetadata(pageMetadata).setMessage("Liste des utilisateurs !");
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
        Utilisateur inComingUser = utilisateurMapper.map(dto);

        currentUser.setMatricule(inComingUser.getMatricule());
        currentUser.setNom(inComingUser.getNom());
        currentUser.setPrenom(inComingUser.getPrenom());
        currentUser.setEmail(inComingUser.getEmail());
        currentUser.setLinkedEntite(inComingUser.getLinkedEntite());
        currentUser.setLinkedProfil(inComingUser.getLinkedProfil());

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
