package sn.gainde2000.fichedotation.web.controllers.gestionsMateriels;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.fichedotation.services.interfaces.GestionMareriels.IGestionMateriels;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.AjoutMaterielDTO;
import sn.gainde2000.fichedotation.web.dtos.others.ModifMaterielDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Tag(name = "GestionMaterielsController", description = "Permet de gérer tous les traitements du profil achat")
@RestController
@RequiredArgsConstructor
public class GestionMaterielsController {
    private final IGestionMateriels iGestionMateriels;

  /*  @Operation(summary = "Endpoint pour récupérer la liste des immobilisations. [ACH]")
    @PreAuthorize("hasAnyAuthority('ACH')")
    @GetMapping("/listImmobilisations")
    public Response<Object> listImmobilisations() {
        return iGestionMateriels.listImmobilisations();
    }*/

    //Liste des immobilisations
    @Operation(summary = "Endpoint pour lister les utilisateurs. ['ADM', 'SUP','SAD']")
    @PreAuthorize("hasAnyAuthority('ACH')")
    @GetMapping("/listImmobilisations")
    public Response<Object> listImmobilisations(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "filter", defaultValue = "") String filter
    ) {
        return iGestionMateriels.listImmobilisations(page, size, filter);
    }





    @Operation(summary = "Endpoint pour ajouter une nouvelle immobilisation [ACH]")
    @PreAuthorize("hasAnyAuthority('ACH')")
    @PostMapping("/addImmobilisation")
    public Response<Object> addImmobilisation(@Valid @RequestBody AjoutMaterielDTO model) {
        return iGestionMateriels.addImmobilisation(model);
    }

    @Operation(summary = "Endpoint pour modifier les informations d'un utilisateur. ['ADM', 'SAD']")
   @PreAuthorize("hasAnyAuthority('ACH')")
    @PutMapping("/editImmobilisation/{id}")
    public Response<Object> updateImmobilisation(@PathVariable @NotNull Integer id, @RequestBody @Valid ModifMaterielDTO dto) {
        return iGestionMateriels.updateImmobilisation(id, dto);
    }

    @Operation(summary = "Endpoint pour supprimer un utilisateur. ['ADM']")
    @PreAuthorize("hasAnyAuthority('ACH')")
    @DeleteMapping("/deleteImmobilisation/{id}")
    public Response<Object> deleteImmobilisation(@PathVariable @NotNull Integer id) {
        return iGestionMateriels.deleteImmobilisation(id);
    }

    @Operation(summary = "Endpoint pour lister les utilisateurs. ['ADM', 'SUP','SAD']")
    @PreAuthorize("hasAnyAuthority('ACH')")
    @GetMapping("/listImmobilisations/{id}")
    public Response<Object> getImmobilisation(@PathVariable Integer id) {
        return iGestionMateriels.getImmobilisation(id);
    }

}
