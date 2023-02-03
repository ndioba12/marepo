package sn.gainde2000.fichedotation.web.dtos.others;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

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
public class ListMaterielsDTO implements Serializable {
    private static final long serialVersionUID = -1655762844468520361L;

    private Integer id;

    private String designation;
    private String fournisseur;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateAcquisition;

    private Double prixAcquisition;
    private TypeImmobilisationDTO typeImmobilisation;
    private EtatDTO etat;

}
