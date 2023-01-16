package sn.gainde2000.fichedotation.web.dtos.messages.responses.mails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
//exclure les propriétés ayant des valeurs nulles / vides ou par défaut.
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailInfosDTO implements Serializable {
    private static final long serialVersionUID = -6385990548803062852L;

    private Integer id;
    private String originalText;
    private String subject;
    private String text;
    private String destinataire;
}
