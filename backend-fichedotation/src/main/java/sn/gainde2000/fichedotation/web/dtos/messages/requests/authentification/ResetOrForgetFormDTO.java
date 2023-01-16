package sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetOrForgetFormDTO {
    @NotBlank(message = "Le champ login est obligatoire")
    //   @Size(min=3, max = 60, message = "La taille du login doit être comprise entre 6 et 40")
    private String login;

    @NotBlank
    //@Size(min = 6)
    // @Size(min = 6, max = 40, message = "La taille du mot de passe doit être comprise entre 6 et 40")
    private String password;

    @NotBlank
    // @Size(min = 6)
    // @Size(min = 6, max = 40, message = "La taille du nouveau mot de passe doit être comprise entre 6 et 40")
    private String newPassword;
}
