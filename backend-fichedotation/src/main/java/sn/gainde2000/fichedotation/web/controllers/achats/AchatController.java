package sn.gainde2000.fichedotation.web.controllers.achats;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sn.gainde2000.fichedotation.exceptions.GenericApiException;
import sn.gainde2000.fichedotation.security.utils.AuthUtils;
import sn.gainde2000.fichedotation.services.interfaces.administration.utilisateur.IGestionProfil;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.EditMonCompteDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.UpdatePasswordDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.AuthenticatedUserInfosDTO;

import javax.validation.Valid;

@Tag(name = "AchatController", description = "Permet de gérer tous les traitements du profil achat")
@RestController
public class AchatController {
    private final IGestionProfil iGestionProfil;


    public AchatController(IGestionProfil iGestionProfil) {
        this.iGestionProfil = iGestionProfil;
    }


    @Operation(summary = "Permet de récuperer l'utilisateur connecté")
    @GetMapping("/getConnectedUser")
    public Response<Object> getConnectedUser() {
        return iGestionProfil.getConnectedUser();
    }

    @Operation(summary = " Permet de modifier les informations de mon profil")
    @PutMapping("/updateProfil")
    public Response<Object> updateUserProfil(@RequestBody @Valid EditMonCompteDTO dto) {
        return this.iGestionProfil.updateUserProfil(dto);
    }

    @Operation(summary = "Permet de mettre à jour le mot de passe")
    @PutMapping("/updatePassword")
    public Response<Object> updatePassword(@RequestBody @Valid UpdatePasswordDTO updatePasswordDTO) {
        return this.iGestionProfil.updatePassword(updatePasswordDTO);
    }
}
    AuthenticatedUserInfosDTO infosDTO = AuthUtils.getAuthenticatedUser().orElseThrow(() -> new GenericApiException("L'utilisateur ne s'est pas authentifié"));