package sn.gainde2000.fichedotation.web.dtos.others;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.NumberFormat;
import sn.gainde2000.fichedotation.entities.TypeMaintenance;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.ProfilDTO;

import javax.persistence.Column;
import javax.validation.constraints.Email;
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
public class ImmobilisationDTO implements Serializable {
    private static final long serialVersionUID = -1655762844468520301L;

    private Integer id;
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

    @NotBlank(message = "La date d'Acquisitione ne doit pas être vide!")
    private Date dateAcquisition;

    @NotBlank(message = "La date de mise en service ne doit pas être vide!")
    private Date dateMiseEnService;

    @NotBlank(message = "Le prix d'acquisition ne doit pas être vide!")
  //  @NumberFormat
    private Double prixAcquisition;

    @NotBlank(message = "La reference interne ne doit pas être vide!")
    @Size(min = 2, max = 100, message = "La reference interne doit être comprise entre 2 et 100!")
    private String referenceInterne;

    @NotBlank(message = "L'adresse Mac interne ne doit pas être vide!")
    @Size(min = 2, max = 100, message = "L'adresse Mac  interne doit être comprise entre 2 et 100!")
    private String adresseMac;

    @NotBlank(message = "La duree de Garantie ne doit pas être vide!")
    private Integer dureeGarantie;
    @NotBlank(message = "valeur nette ne doit pas être vide!")
    private Double valeurNette;
    @NotNull(message = "Le typeImmobilisation ne doit pas être null!")
    private TypeImmobilisationDTO typeImmobilisation;

    private StatutDTO statutDTO;
    @NotNull(message = "La marque ne doit pas être null!")
    private MarqueDTO marqueDTO;
    @NotNull(message = "Le Type maintenanceDTO ne doit pas être null!")
    private TypeMaintenanceDTO typeMaintenanceDTO;
    @NotNull(message = "Le Type maintenanceDTO ne doit pas être null!")
    private TypeMaintenanceDTO typeMaintenanceDTO;


    /*  public ImmobilisationDTO(Integer id) {
        this.id = id;
    }*/
}
