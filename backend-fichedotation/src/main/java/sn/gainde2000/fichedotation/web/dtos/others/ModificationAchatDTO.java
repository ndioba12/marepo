package sn.gainde2000.fichedotation.web.dtos.others;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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
public class ModificationAchatDTO implements Serializable {
    private static final long serialVersionUID = -1655762844468520301L;

    //private Integer id;

    @Size(min = 2, max = 100, message = "La taille de la designation doit être comprise entre 2 et 100!")
    private String designation;

    @Size(min = 2, max = 255, message = "La taille de la description doit être comprise entre 2 et 255!")
    private String description;

    @Size(min = 2, max = 255, message = "Le modele  doit être comprise entre 2 et 100!")
    private String modele;
    @Size(min = 2, max = 255, message = "La marque  doit être comprise entre 2 et 100!")
    private String marque;

    @Size(min = 2, max = 255, message = "Le champ fournisseur doit être comprise entre 2 et 100!")
    private String fournisseur;
    @Size(min = 2, max = 255, message = "Le champ accessoires   doit être comprise entre 2 et 100!")
    private String accessoires;


    @Size(min = 2, max = 255, message = "Le champ referenceInterne doit être comprise entre 2 et 100!")
    private String referenceInterne;

    @Size(min = 2, max = 100, message = "La reference commerciale doit être comprise entre 2 et 100!")
    private String refCommercial;

    private Date dateAcquisition;


    private Double prixAcquisition;

    private Integer dureeGarantie;

    private TypeImmobilisationDTO typeImmobilisation;

    private CatImmobilisationDTO catImmobilisation;

}
