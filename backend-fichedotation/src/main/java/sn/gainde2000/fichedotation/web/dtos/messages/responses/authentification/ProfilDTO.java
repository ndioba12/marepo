package sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
//exclure les propriétés ayant des valeurs nulles / vides ou par défaut.
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ProfilDTO implements Serializable {
    private static final long serialVersionUID = -1655762844468520300L;

    private Integer id;
    private String code;
    private String libelle;

    public ProfilDTO(Integer id) {
        this.id = id;
    }
}
