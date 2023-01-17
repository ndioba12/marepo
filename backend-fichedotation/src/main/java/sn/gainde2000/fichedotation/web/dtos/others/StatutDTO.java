package sn.gainde2000.fichedotation.web.dtos.others;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
//exclure les propriétés ayant des valeurs nulles / vides ou par défaut.
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class StatutDTO implements Serializable {
    private static final long serialVersionUID = -1655762844468520302L;

    private Integer id;
    private String code;
    private String libelle;
    
}
