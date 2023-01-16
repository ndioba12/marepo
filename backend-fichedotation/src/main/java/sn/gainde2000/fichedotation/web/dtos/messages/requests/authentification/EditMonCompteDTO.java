package sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditMonCompteDTO {

    @NotBlank
    @Size(min = 2, max = 20, message = "La taille du nom doit être comprise entre 2 et 20 !")
    private String nom;

    @NotBlank
    @Size(min = 3, max = 60, message = "La taille du prenom doit être comprise entre 3 et 60 !")
    private String prenom;

    private String adresse;

    @NotBlank(message = "Le champ telephone est obligatoire !")
    private String telephone;
}
