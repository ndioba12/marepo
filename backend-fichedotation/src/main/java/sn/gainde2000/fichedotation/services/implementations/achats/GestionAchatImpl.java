/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/08/2022
 */

package sn.gainde2000.fichedotation.services.implementations.achats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.fichedotation.entities.Immobilisation;
import sn.gainde2000.fichedotation.exceptions.GenericApiException;
import sn.gainde2000.fichedotation.repositories.ImmobilisationRepository;
import sn.gainde2000.fichedotation.repositories.ProfilRepository;
import sn.gainde2000.fichedotation.services.interfaces.achats.IGestionAchat;
import sn.gainde2000.fichedotation.web.dtos.mappers.OthersMapper;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.AjoutAchatDTO;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GestionAchatImpl implements IGestionAchat {

    private final ImmobilisationRepository immobilisationRepository;
    private final ProfilRepository profilRepository;
    private final OthersMapper othersMapper;

    @Override
    public Response<Object> listImmobilisations() {
        return Response.ok().setPayload(immobilisationRepository.findAll()).setMessage("Liste des immobilisations !");
    }
    @Override
    public Response<Object> getImmobilisation(Integer id) {
        Immobilisation immobilisation = immobilisationRepository.findById(id).orElseThrow(() -> new GenericApiException("immobilisation absent!"));

        return Response.ok().setPayload(immobilisation).setMessage("immobilisation retrouvé avec succès!");
        //return Response.ok().setPayload(iImmobilisationRepository.findAll()).setMessage("Liste des marques !");
    }
    @Transactional
    @Override
    public Response<Object> addImmobilisation(AjoutAchatDTO model) {
       /* AuthenticatedUserInfosDTO infosDTO = AuthUtils.getAuthenticatedUser().orElseThrow(() -> new GenericApiException("L'utilisateur ne s'est pas authentifié"));
        if(infosDTO.getLinkedProfil().getCode().equals(profilRepository.findByCode("").get().getCode()))
        {*/
            Optional<Immobilisation> optionalImmobilisation = immobilisationRepository.findByDesignation(model.getDesignation());

            if (optionalImmobilisation.isPresent())
                return Response.exception().setMessage("Cette designation est saisi est dèjà utilisé !");
            //Immobilisation immobilisation = new Immobilisation();
            Immobilisation immobilisation = othersMapper.mapToImmobilisation(model);
            //typeCession.setCode(model.getCode());
            immobilisationRepository.save(immobilisation);
            return Response.ok().setMessage("Materiel ajouté!");

     /*   }
        else{
            return Response.unauthorized().setMessage("Vous n'etes pas autorisé à effectuer cette operation");
        }*/
    }

}

