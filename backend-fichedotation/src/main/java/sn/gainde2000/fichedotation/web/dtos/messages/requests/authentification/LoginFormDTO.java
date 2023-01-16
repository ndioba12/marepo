package sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginFormDTO {
    @NotBlank
    @Email
    //  @Size(min=3, max = 60)
    private String login;

    @NotBlank
    //   @Size(min = 6)
    private String password;
}
