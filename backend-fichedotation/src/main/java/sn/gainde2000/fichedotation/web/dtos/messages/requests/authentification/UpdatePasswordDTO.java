package sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePasswordDTO {
    @NotBlank
    private String prevPassword;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String confPassword;


}
