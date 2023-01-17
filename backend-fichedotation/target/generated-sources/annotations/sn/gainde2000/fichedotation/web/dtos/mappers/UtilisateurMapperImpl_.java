package sn.gainde2000.fichedotation.web.dtos.mappers;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sn.gainde2000.fichedotation.entities.Profil;
import sn.gainde2000.fichedotation.entities.Profil.ProfilBuilder;
import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.entities.Utilisateur.UtilisateurBuilder;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.UtilisateurDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.UtilisateurDTO.UtilisateurDTOBuilder;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.AuthenticatedUserInfosDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.ProfilDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-17T15:17:57+0000",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class UtilisateurMapperImpl_ implements UtilisateurMapper {

    @Override
    public AuthenticatedUserInfosDTO utilisateurToAuthenticatedUserInfosDTO(Utilisateur utilisateur) {
        if ( utilisateur == null ) {
            return null;
        }

        AuthenticatedUserInfosDTO authenticatedUserInfosDTO = new AuthenticatedUserInfosDTO();

        authenticatedUserInfosDTO.setId( utilisateur.getId() );
        authenticatedUserInfosDTO.setPrenom( utilisateur.getPrenom() );
        authenticatedUserInfosDTO.setNom( utilisateur.getNom() );
        authenticatedUserInfosDTO.setEmail( utilisateur.getEmail() );
        authenticatedUserInfosDTO.setPassword( utilisateur.getPassword() );
        authenticatedUserInfosDTO.setFirstLog( utilisateur.getFirstLog() );
        authenticatedUserInfosDTO.setStatus( utilisateur.getStatus() );
        authenticatedUserInfosDTO.setAdresse( utilisateur.getAdresse() );
        authenticatedUserInfosDTO.setTelephone( utilisateur.getTelephone() );
        authenticatedUserInfosDTO.setLinkedProfil( profilToProfilDTO( utilisateur.getLinkedProfil() ) );

        return authenticatedUserInfosDTO;
    }

    @Override
    public Utilisateur authenticatedUserInfosDTOToUtilisateur(AuthenticatedUserInfosDTO infosDTO) {
        if ( infosDTO == null ) {
            return null;
        }

        UtilisateurBuilder utilisateur = Utilisateur.builder();

        utilisateur.id( infosDTO.getId() );
        utilisateur.nom( infosDTO.getNom() );
        utilisateur.prenom( infosDTO.getPrenom() );
        utilisateur.email( infosDTO.getEmail() );
        utilisateur.password( infosDTO.getPassword() );
        utilisateur.firstLog( infosDTO.getFirstLog() );
        utilisateur.status( infosDTO.getStatus() );
        utilisateur.telephone( infosDTO.getTelephone() );
        utilisateur.adresse( infosDTO.getAdresse() );
        utilisateur.linkedProfil( profilDTOToProfil( infosDTO.getLinkedProfil() ) );

        return utilisateur.build();
    }

    @Override
    public ProfilDTO profilToProfilDTO(Profil profil) {
        if ( profil == null ) {
            return null;
        }

        ProfilDTO profilDTO = new ProfilDTO();

        profilDTO.setId( profil.getId() );
        profilDTO.setCode( profil.getCode() );
        profilDTO.setLibelle( profil.getLibelle() );

        return profilDTO;
    }

    @Override
    public Profil profilDTOToProfil(ProfilDTO profilDTO) {
        if ( profilDTO == null ) {
            return null;
        }

        ProfilBuilder profil = Profil.builder();

        profil.id( profilDTO.getId() );
        profil.code( profilDTO.getCode() );
        profil.libelle( profilDTO.getLibelle() );

        return profil.build();
    }

    @Override
    public Utilisateur map(UtilisateurDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UtilisateurBuilder utilisateur = Utilisateur.builder();

        utilisateur.id( dto.getId() );
        utilisateur.nom( dto.getNom() );
        utilisateur.prenom( dto.getPrenom() );
        utilisateur.email( dto.getEmail() );
        utilisateur.telephone( dto.getTelephone() );
        utilisateur.adresse( dto.getAdresse() );
        utilisateur.linkedProfil( profilDTOToProfil( dto.getLinkedProfil() ) );

        return utilisateur.build();
    }

    @Override
    public UtilisateurDTO map(Utilisateur utilisateur) {
        if ( utilisateur == null ) {
            return null;
        }

        UtilisateurDTOBuilder utilisateurDTO = UtilisateurDTO.builder();

        utilisateurDTO.id( utilisateur.getId() );
        utilisateurDTO.nom( utilisateur.getNom() );
        utilisateurDTO.prenom( utilisateur.getPrenom() );
        utilisateurDTO.email( utilisateur.getEmail() );
        utilisateurDTO.telephone( utilisateur.getTelephone() );
        utilisateurDTO.adresse( utilisateur.getAdresse() );
        utilisateurDTO.linkedProfil( profilToProfilDTO( utilisateur.getLinkedProfil() ) );

        return utilisateurDTO.build();
    }
}
