package sn.gainde2000.fichedotation.web.dtos.others;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import sn.gainde2000.fichedotation.entities.Fournisseur;
import sn.gainde2000.fichedotation.entities.Marque;
import sn.gainde2000.fichedotation.entities.Statut;
import sn.gainde2000.fichedotation.entities.TypeMaintenance;

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
public class AjoutAchatDTO implements Serializable {
    private static final long serialVersionUID = -1655762844468520301L;

    private Integer id;
    @NotBlank(message = "La designation ne doit pas être vide!")
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


    @Size(min = 2, max = 255, message = "Le champ refInterne doit être comprise entre 2 et 100!")
    private String refInterne;

    @Size(min = 2, max = 100, message = "La reference commerciale doit être comprise entre 2 et 100!")
    private String refCommercial;
    @NotNull(message = "La date d'acquisition ne doit pas être vide!")
    private Date dateAcquisition;

    @NotNull(message = "Le modele ne doit pas être vide!")
    private Double prixAcquisition;
    @NotNull(message = "La duree de garantie ne doit pas être vide!")
    private Integer dureeGarantie;
    @NotNull(message = "Le type immobilisationduree de garantie ne doit pas être vide!")
    private TypeImmobilisationDTO typeImmobilisation;

 //  private Statut statut=new Statut();


    /*  public AjoutAchatDTO(Integer id) {
        this.id = id;
    }*/
}
