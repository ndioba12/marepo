package sn.gainde2000.fichedotation.web.controllers.administration.utilisateurs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sn.gainde2000.fichedotation.services.interfaces.administration.utilisateur.IGestionProfil;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.EditMonCompteDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.UpdatePasswordDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;

import javax.validation.Valid;

@Tag(name = "GestionProfilController", description = "Permet de gérer le profil de l'utilisateur connecte")
@RestController
public class GestionProfilController {
    private final IGestionProfil iGestionProfil;


    public GestionProfilController(IGestionProfil iGestionProfil) {
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
