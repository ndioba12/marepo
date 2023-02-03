/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :11/12/2021
 */

package sn.gainde2000.fichedotation.web.dtos.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import sn.gainde2000.fichedotation.entities.Entite;
import sn.gainde2000.fichedotation.entities.Profil;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.ProfilDTO;
import sn.gainde2000.fichedotation.web.dtos.others.EntiteDTO;

import javax.annotation.Nullable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UtilisateurDTO {
    private Integer id;

    @NotBlank(message = "La matricule ne doit pas être vide!")
    private String matricule;

    @NotBlank(message = "Le nom ne doit pas être vide!")
    @Size(min = 2, max = 20, message = "La taille du nom doit être comprise entre 2 et 20!")
    private String nom;

    @NotBlank(message = "Le prénom ne doit pas être vide!")
    @Size(min = 3, max = 60, message = "La taille du prenom doit être comprise entre 3 et 60!")
    private String prenom;

    @NotBlank(message = "L'email ne doit pas être vide!")
    @Email(message = "L'email n'est pas valide!")
    private String email;

    @NotBlank(message = "La fonction ne doit pas être vide!")
    private String fonction = "Personnel"; //Personnel par defaut pour le moment

    @NotNull(message = "Le status ne doit pas être vide")
    private Boolean status = true; //Statut par defaut: true

    @NotNull(message = "Le profil ne doit pas être null!")
    private ProfilDTO linkedProfil;

    @NotNull(message = "L'entité ne doit pas être null!")
    private EntiteDTO linkedEntite;

}
