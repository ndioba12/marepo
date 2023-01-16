/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/01/2022
 */

package sn.gainde2000.fichedotation.web.controllers.express;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.gainde2000.fichedotation.services.interfaces.shared.IDonneeReferences;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;

@Tag(name = "TraitementExpressController", description = "Permet de gérer les opérations express qui ne necessitent pas d'authentification")
@RestController
@RequiredArgsConstructor
@RequestMapping("/express")
public class TraitementExpressController {
    private final IDonneeReferences iDonneeReferences;

    @Operation(summary = "Endpoint pour récupérer la liste des profils utilisateurs")
    @GetMapping("/profils")
    public Response<Object> listProfil() {
        return iDonneeReferences.listProfil();
    }
}
