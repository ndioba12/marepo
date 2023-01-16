/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/08/2022
 */

package sn.gainde2000.fichedotation.services.implementations.shared;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sn.gainde2000.fichedotation.repositories.MarqueRepository;
import sn.gainde2000.fichedotation.repositories.ProfilRepository;
import sn.gainde2000.fichedotation.services.interfaces.shared.IDonneeReferences;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;

@Service
@AllArgsConstructor
public class DonneeReferencesImpl implements IDonneeReferences {
    private final ProfilRepository profilRepository;
    private final MarqueRepository marqueRepository;

    @Override
    public Response<Object> listProfil() {
        return Response.ok().setPayload(profilRepository.findAll()).setMessage("Liste des profils !");
    }
    @Override
    public Response<Object> listMarque() {
        return Response.ok().setPayload(marqueRepository.findAll()).setMessage("Liste des marques !");
    }
}
