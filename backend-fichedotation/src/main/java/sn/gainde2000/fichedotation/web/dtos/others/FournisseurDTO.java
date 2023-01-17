package sn.gainde2000.fichedotation.web.dtos.others;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
//exclure les propriétés ayant des valeurs nulles / vides ou par défaut.
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FournisseurDTO implements Serializable {
    private static final long serialVersionUID = -1655762844468520307L;

    private Integer id;
    @NotBlank(message = "Le nomOuRaiSociale ne doit pas être vide!")
    @Size(min = 2, max = 100, message = "Le nomOuRaiSociale doit être comprise entre 2 et 100!")
    private String nomOuRaiSociale;
    @NotBlank(message = "L'a 'adresse doit pas être vide!")
    @Size(min = 2, max = 100, message = "L'adresse doit être comprise entre 2 et 100!")
    private String adresse;
    @NotBlank(message = "Le telephone fixe  ne doit pas être vide!")
    @Size(min = 2, max = 50, message = "Le telephone fixe  doit être comprise entre 2 et 50!")
    private String telephoneFixe;

    @Size(min = 2, max = 50, message = "Le telephone mobile doit être comprise entre 2 et 100!")
    private String telephoneMobile;
    @NotBlank(message = "L'email ne doit pas être vide!")
    @Email(message = "L'email n'est pas valide!")
    private String email;
}
