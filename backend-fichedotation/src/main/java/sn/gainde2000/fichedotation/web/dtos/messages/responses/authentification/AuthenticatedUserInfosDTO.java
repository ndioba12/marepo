package sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import sn.gainde2000.fichedotation.web.dtos.others.EntiteDTO;

import java.io.Serializable;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@ToString
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
//exclure les propriétés ayant des valeurs nulles / vides ou par défaut.
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticatedUserInfosDTO implements Serializable {
    private static final long serialVersionUID = 3336712090424782155L;


    private Integer id;
    private String prenomNom;
    private String prenom;
    private String nom;
    private String matricule;
    private String email;
    private String password;
    private Boolean firstLog;
    private Boolean status;
    private String adresse;
    private String telephone;
    private String telephone1;
    private String fonction;
    private ProfilDTO linkedProfil;
    private EntiteDTO linkedEntite;


}
