package sn.gainde2000.fichedotation.web.controllers.administration.utilisateurs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.fichedotation.services.interfaces.administration.utilisateur.IUtilisateur;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.UtilisateurDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Tag(name = "GestionUtilisateurController", description = "Permet de gérer les opérations sur les utilisateurs")
@RestController
@RequiredArgsConstructor
public class GestionUtilisateurController {
    private final IUtilisateur iUtilisateur;

    /**
     * autorise uniquement pour les profils suivants
     **/
    @Operation(summary = "Endpoint pour lister les utilisateurs. ['ADM', 'SUP','SAD']")
    @PreAuthorize("hasAnyAuthority('ADM','SAD')")
    @GetMapping("/listUtilisateur")
    public Response<Object> getListUtilisateur(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "filter", defaultValue = "") String filter
    ) {
        return iUtilisateur.listUtilisateur(page, size, filter);
    }

    /**
     * autorise uniquement pour les profils suivants
     **/

    @Operation(summary = "Endpoint pour lister les utilisateurs. ['ADM', 'SUP','SAD']")
    @PreAuthorize("hasAnyAuthority('ADM','SAD')")
    @GetMapping("/listUtilisateur/{id}")
    public Response<Object> getUtilisateur(@PathVariable Integer id) {
        return iUtilisateur.getUtilisateur(id);
    }

    /**
     * autorise uniquement pour l'ADMINISTRATEUR
     **/
    @Operation(summary = "Endpoint pour ajouter un utilisateur. ['ADM', 'SAD']")
    @PreAuthorize("hasAnyAuthority('SAD')")
    @PostMapping("/saveUtilisateur")
    public Response<Object> saveUtilisateur(@RequestBody @Valid UtilisateurDTO dto) {
        return iUtilisateur.saveUtilisateur(dto);
    }

    /**
     * autorise uniquement pour l'ADMINISTRATEUR
     **/
    @Operation(summary = "Endpoint pour charger dans la base de données les utilisateurs depuis un fichier excel. ['ADM', 'SAD']")
    @PreAuthorize("hasAnyAuthority('SAD')")
    @PostMapping("/saveUsersFromExcel")
    public Response<Object> saveUtilisateursFromExcel(@RequestBody @Valid List<UtilisateurDTO> usersList){
        return iUtilisateur.saveUtilisateursFromExcel(usersList);
    }

    /**
     * autorise uniquement pour l'ADMINISTRATEUR
     **/
    @Operation(summary = "Endpoint pour modifier les informations d'un utilisateur. ['ADM', 'SAD']")
    @PreAuthorize("hasAnyAuthority('SAD')")
    @PutMapping("/editUtilisateur/{id}")
    public Response<Object> editUtilisateur(@PathVariable @NotNull Integer id, @RequestBody @Valid UtilisateurDTO dto) {
        return iUtilisateur.editUtilisateur(id, dto);
    }

    /**
     * autorise uniquement pour l'ADMINISTRATEUR
     **/
    @Operation(summary = "Endpoint pour activer ou desactiver un compte utilisateur. ['ADM']")
    @PreAuthorize("hasAnyAuthority('SAD')")
    @PutMapping("/changeStatus/{id}")
    public Response<Object> changeStatus(@PathVariable @NotNull Integer id) {
        return iUtilisateur.changeStatus(id);
    }

    /**
     * autorise uniquement pour l'ADMINISTRATEUR
     **/
    @Operation(summary = "Endpoint pour supprimer un utilisateur. ['ADM']")
    @PreAuthorize("hasAnyAuthority('SAD')")
    @DeleteMapping("/deleteUtilisateur/{id}")
    public Response<Object> deleteUtilisateur(@PathVariable @NotNull Integer id) {
        return iUtilisateur.deleteUtilisateur(id);
    }
}
