/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :11/12/2021
 */

package sn.gainde2000.fichedotation.web.dtos.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.ProfilDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UtilisateurDTO {
    private Integer id;

    @NotBlank(message = "Le nom ne doit pas être vide!")
    @Size(min = 2, max = 20, message = "La taille du nom doit être comprise entre 2 et 20!")
    private String nom;

    @NotBlank(message = "Le prénom ne doit pas être vide!")
    @Size(min = 3, max = 60, message = "La taille du prenom doit être comprise entre 3 et 60!")
    private String prenom;

    @NotBlank(message = "L'email ne doit pas être vide!")
    @Email(message = "L'email n'est pas valide!")
    private String email;

    @NotBlank(message = "Le téléphone ne doit pas être vide!")
    private String telephone;

    private String adresse;

    @NotNull(message = "Le profil ne doit pas être null!")
    private ProfilDTO linkedProfil;
}
