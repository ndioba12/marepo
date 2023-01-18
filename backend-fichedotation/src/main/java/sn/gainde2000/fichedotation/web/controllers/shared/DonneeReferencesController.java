/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :15/12/2021
 */

package sn.gainde2000.fichedotation.web.controllers.shared;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sn.gainde2000.fichedotation.entities.TypeCession;
import sn.gainde2000.fichedotation.services.interfaces.shared.IDonneeReferences;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.UtilisateurDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.TypeCessionDTO;

import javax.validation.Valid;

@Tag(name = "DonneeReferencesController", description = "Permet d'obtenir et de gérer les données de références")
@RestController
@RequiredArgsConstructor
public class DonneeReferencesController {
    private final IDonneeReferences iDonneeReferences;

    @Operation(summary = "Endpoint pour récupérer la liste des profils utilisateurs")
    @GetMapping("/profils")
    public Response<Object> listProfil() {
        return iDonneeReferences.listProfil();
    }

    @Operation(summary = "Endpoint pour récupérer la liste des marques")
    @GetMapping("/marques")
    public Response<Object> listMarque() {
        return iDonneeReferences.listMarque();
    }
    @Operation(summary = "Endpoint pour récupérer la liste des types cessions")
    @GetMapping("/typeCessions")
    public Response<Object> listypeCession() {
        return iDonneeReferences.listypeCession();
    }

    @Operation(summary = "Endpoint pour ajouter un nouvel type cession")
    @PostMapping("/savetypeCession")
    public Response<Object> saveTypeCession(@Valid @RequestBody TypeCessionDTO model) {
        return iDonneeReferences.addTypeCession(model);
    }

}
