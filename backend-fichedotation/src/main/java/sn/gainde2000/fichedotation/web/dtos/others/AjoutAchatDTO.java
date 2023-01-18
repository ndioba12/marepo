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

    //private Integer id;
    @NotBlank(message = "La designation ne doit pas être vide!")
    @Size(min = 2, max = 100, message = "La taille de la designation doit être comprise entre 2 et 100!")
    private String designation;

    @NotBlank(message = "Le description ne doit pas être vide!")
    @Size(min = 2, max = 255, message = "La taille de la description doit être comprise entre 2 et 255!")
    private String description;

    @NotBlank(message = "Le modele ne doit pas être vide!")
    @Size(min = 2, max = 255, message = "Le modele  doit être comprise entre 2 et 100!")
    private String modele;

    @NotBlank(message = "Le numero de serie ne doit pas être vide!")
    @Size(min = 2, max = 100, message = "Le numero de serie  doit être comprise entre 2 et 100!")
    private String numeroSerie;

    @NotBlank(message = "Le numero de serie ne doit pas être vide!")
    @Size(min = 2, max = 255, message = "Le champ accessoires   doit être comprise entre 2 et 100!")
    private String accessoires;

    @NotBlank(message = "La reference commerciale ne doit pas être vide!")
    @Size(min = 2, max = 100, message = "La reference commerciale doit être comprise entre 2 et 100!")
    private String refCommercial;

    @NotNull
    private Date dateAcquisition;

    @NotNull
    private Date dateMiseEnService;

   @NotNull
    private Double prixAcquisition;

    @NotBlank(message = "La reference interne ne doit pas être vide!")
    @Size(min = 2, max = 100, message = "La reference interne doit être comprise entre 2 et 100!")
    private String referenceInterne;

    @NotBlank(message = "L'adresse Mac interne ne doit pas être vide!")
    @Size(min = 2, max = 100, message = "L'adresse Mac  interne doit être comprise entre 2 et 100!")
    private String adresseMac;

    @NotNull
    private Integer dureeGarantie;
    @NotNull
    private Double valeurNette;
    @NotNull(message = "Le typeImmobilisation ne doit pas être null!")
    private TypeImmobilisationDTO typeImmobilisation;

    private Statut statut;
    @NotNull(message = "La marque ne doit pas être null!")
    private Marque marque;
    @NotNull(message = "Le Type maintenanceDTO ne doit pas être null!")
    private TypeMaintenance typeMaintenance;

    @NotNull(message = "Le fournisseur ne doit pas être null!")
    private Fournisseur fournisseur;
    /*  public AjoutAchatDTO(Integer id) {
        this.id = id;
    }*/
}
