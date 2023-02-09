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
import sn.gainde2000.fichedotation.services.interfaces.shared.IDonneeReferences;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.EntiteDTO;
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

    @Operation(summary = "Endpoint pour récupérer la liste des entités ou départements de Gainde2000")
    @GetMapping("/entites")
    public Response<Object> listEntite() {
        return iDonneeReferences.listEntite();
    }

    @Operation(summary = "Endpoint pour sauvegarder une nouvelle entité ou département de Gainde2000")
    @PostMapping("/saveEntite")
    public Response<Object> saveEntite(@RequestBody  EntiteDTO dto) {
        return iDonneeReferences.saveEntite(dto);
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

    @Operation(summary = "Endpoint pour récupérer la liste des statuts")
    @GetMapping("/statuts")
    public Response<Object> listStatut() {
        return iDonneeReferences.listStatut();
    }


    @Operation(summary = "Endpoint pour récupérer la liste des type de materiels")
    @GetMapping("/listTypeMateriels")
    public Response<Object> listTypeMateriels() {
        return iDonneeReferences.listTypeMateriels();

    }

    @Operation(summary = "Endpoint pour récupérer la liste des categories de materiels")
    @GetMapping("/listCatMateriels")
    public Response<Object> listCatMateriels() {
        return iDonneeReferences.listCatMateriels();

    }

    @Operation(summary = "Endpoint pour récupérer la liste des etats")
    @GetMapping("/etats")
    public Response<Object> listEtat() {
        return iDonneeReferences.listEtat();
    }

    @Operation(summary = "Endpoint pour récupérer la liste des types de maintenances")
    @GetMapping("/typeMaintenances")
    public Response<Object> listeTypeMaintenances() {
        return iDonneeReferences.listeTypeMaintenances();
    }

}
