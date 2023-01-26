package sn.gainde2000.fichedotation.web.controllers.achats;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.fichedotation.services.interfaces.achats.IGestionAchat;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.UtilisateurDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.AjoutAchatDTO;
import sn.gainde2000.fichedotation.web.dtos.others.ModificationAchatDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Tag(name = "AchatController", description = "Permet de gérer tous les traitements du profil achat")
@RestController
@RequiredArgsConstructor
public class AchatController {
    private final IGestionAchat iGestionAchat;

    @Operation(summary = "Endpoint pour récupérer la liste des immobilisations. [ACH]")
    @PreAuthorize("hasAnyAuthority('ACH')")
    @GetMapping("/listImmobilisations")
    public Response<Object> listImmobilisations() {
        return iGestionAchat.listImmobilisations();
    }

    @Operation(summary = "Endpoint pour ajouter une nouvelle immobilisation [ACH]")
    @PreAuthorize("hasAnyAuthority('ACH')")
    @PostMapping("/addImmobilisation")
    public Response<Object> addImmobilisation(@Valid @RequestBody AjoutAchatDTO model) {
        return iGestionAchat.addImmobilisation(model);
    }

    @Operation(summary = "Endpoint pour modifier les informations d'un utilisateur. ['ADM', 'SAD']")
   @PreAuthorize("hasAnyAuthority('ACH')")
    @PutMapping("/editImmobilisation/{id}")
    public Response<Object> updateImmobilisation(@PathVariable @NotNull Integer id, @RequestBody @Valid ModificationAchatDTO dto) {
        return iGestionAchat.updateImmobilisation(id, dto);
    }

    @Operation(summary = "Endpoint pour supprimer un utilisateur. ['ADM']")
    @PreAuthorize("hasAnyAuthority('ACH')")
    @DeleteMapping("/deleteImmobilisation/{id}")
    public Response<Object> deleteImmobilisation(@PathVariable @NotNull Integer id) {
        return iGestionAchat.deleteImmobilisation(id);
    }

    @Operation(summary = "Endpoint pour lister les utilisateurs. ['ADM', 'SUP','SAD']")
    @PreAuthorize("hasAnyAuthority('ACH')")
    @GetMapping("/listImmobilisations/{id}")
    public Response<Object> getImmobilisation(@PathVariable Integer id) {
        return iGestionAchat.getImmobilisation(id);
    }

}
