package sn.gainde2000.fichedotation.web.dtos.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import sn.gainde2000.fichedotation.entities.Entite;
import sn.gainde2000.fichedotation.entities.Profil;
import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.AuthenticatedUserInfosDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.ProfilDTO;
import sn.gainde2000.fichedotation.web.dtos.others.EntiteDTO;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Slf4j
public abstract class UtilisateurMapperDecorator implements UtilisateurMapper {
    private UtilisateurMapper utilisateurMapper;

    @Autowired
    public void setUtilisateurMapper(UtilisateurMapper utilisateurMapper) {
        this.utilisateurMapper = utilisateurMapper;
    }

    @Override
    public AuthenticatedUserInfosDTO utilisateurToAuthenticatedUserInfosDTO(Utilisateur utilisateur) {
        AuthenticatedUserInfosDTO infosDTO = utilisateurMapper.utilisateurToAuthenticatedUserInfosDTO(utilisateur);
        infosDTO.setPrenomNom(utilisateur.getPrenom() + " " + utilisateur.getNom().toUpperCase());
        infosDTO.setLinkedProfil(profilToProfilDTO(utilisateur.getLinkedProfil()));
        infosDTO.setLinkedEntite(entiteToEntiteDTO(utilisateur.getLinkedEntite()));

        return infosDTO;
    }

    @Override
    public Utilisateur authenticatedUserInfosDTOToUtilisateur(AuthenticatedUserInfosDTO infosDTO) {
        return utilisateurMapper.authenticatedUserInfosDTOToUtilisateur(infosDTO);
    }

    @Override
    public ProfilDTO profilToProfilDTO(Profil profil) {
        return utilisateurMapper.profilToProfilDTO(profil);
    }

    @Override
    public Profil profilDTOToProfil(ProfilDTO profilDTO) {
        return utilisateurMapper.profilDTOToProfil(profilDTO);
    }

    @Override
    public EntiteDTO entiteToEntiteDTO(Entite entite) {
        return utilisateurMapper.entiteToEntiteDTO(entite);
    }

    @Override
    public Entite entiteDTOToEntite(EntiteDTO entiteDTO) {
        return utilisateurMapper.entiteDTOToEntite(entiteDTO);
    }
}
