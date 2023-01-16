package sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
//exclure les propriétés ayant des valeurs nulles / vides ou par défaut.
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuDTO {

    private Integer id;
    private String path;
    private String title;
    private String type;
    private String iconType;
    private String collapse;
    private String ab;
    private Integer priorite;
    private Set<MenuDTO> children = new HashSet<>();
}

