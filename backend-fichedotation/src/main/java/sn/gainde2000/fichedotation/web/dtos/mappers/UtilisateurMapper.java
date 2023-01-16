package sn.gainde2000.fichedotation.web.dtos.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import sn.gainde2000.fichedotation.entities.Profil;
import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.UtilisateurDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.AuthenticatedUserInfosDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.ProfilDTO;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Mapper
@DecoratedWith(UtilisateurMapperDecorator.class)
public interface UtilisateurMapper {
    AuthenticatedUserInfosDTO utilisateurToAuthenticatedUserInfosDTO(Utilisateur utilisateur);
    Utilisateur authenticatedUserInfosDTOToUtilisateur(AuthenticatedUserInfosDTO infosDTO);
    ProfilDTO profilToProfilDTO(Profil profil);
    Profil profilDTOToProfil(ProfilDTO profilDTO);
    Utilisateur map(UtilisateurDTO dto);
    UtilisateurDTO map(Utilisateur utilisateur);
}
