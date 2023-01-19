package sn.gainde2000.fichedotation.web.dtos.others;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import sn.gainde2000.fichedotation.entities.Fournisseur;
import sn.gainde2000.fichedotation.entities.Immobilisation;
import sn.gainde2000.fichedotation.entities.Marque;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
//exclure les propriétés ayant des valeurs nulles / vides ou par défaut.
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class HistoriqueLocalisationDTO implements Serializable {
    private static final long serialVersionUID = -1655762844468520309L;

    //private Integer id;
    @NotBlank(message = "La localisation ne doit pas être vide!")
    @Size(min = 2, max = 100, message = "La localisation doit être comprise entre 2 et 100!")
    private String localisation;

    @NotBlank(message = "L'initiateur ne doit pas être vide!")
    @Size(min = 2, max = 255, message = "L'initiateur doit être comprise entre 2 et 255!")
    private String initiateur;
    @NotNull
    private Date dateAffectation;
    @NotNull(message = "immobilisation ne doit pas être null!")
    private AjoutAchatDTO immobilisation;


}
