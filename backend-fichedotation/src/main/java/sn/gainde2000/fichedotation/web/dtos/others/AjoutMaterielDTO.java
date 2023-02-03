package sn.gainde2000.fichedotation.web.dtos.others;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
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
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
//exclure les propriétés ayant des valeurs nulles / vides ou par défaut.
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AjoutMaterielDTO implements Serializable {
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
    @NotBlank(message = "Le fournisseur ne doit pas être vide!")
    @Size(min = 2, max = 255, message = "Le champ fournisseur doit être comprise entre 2 et 100!")
    private String fournisseur;
    @Size(min = 2, max = 255, message = "Le champ accessoires   doit être comprise entre 2 et 100!")
    private String accessoires;
    @NotNull(message = "La date d'acquisition ne doit pas être vide!")
   // @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateAcquisition;

    @NotNull(message = "Le modele ne doit pas être vide!")
    private Double prixAcquisition;
    @NotNull(message = "La duree de garantie ne doit pas être vide!")
    private Integer dureeGarantie;
    @NotNull(message = "Le type immobilisation ne doit pas être vide!")
    private TypeImmobilisationDTO typeImmobilisation;
    @NotNull(message = "La categorie d'immobilisation ne doit pas être vide!")
    private CatImmobilisationDTO catImmobilisation;
    //private EtatDTO etat;

}
