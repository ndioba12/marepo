/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/08/2022
 */

package sn.gainde2000.fichedotation.services.implementations.achats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.fichedotation.entities.HistoriqueLocalisation;
import sn.gainde2000.fichedotation.entities.Immobilisation;
import sn.gainde2000.fichedotation.entities.TypeImmobilisation;
import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.exceptions.GenericApiException;
import sn.gainde2000.fichedotation.repositories.ImmobilisationRepository;
import sn.gainde2000.fichedotation.repositories.LocalisationRepository;
import sn.gainde2000.fichedotation.repositories.ProfilRepository;
import sn.gainde2000.fichedotation.repositories.StatutRepository;
import sn.gainde2000.fichedotation.security.utils.AuthUtils;
import sn.gainde2000.fichedotation.services.interfaces.achats.IGestionAchat;
import sn.gainde2000.fichedotation.web.dtos.mappers.OthersMapper;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.UtilisateurDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.EditMonCompteDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.AuthenticatedUserInfosDTO;
import sn.gainde2000.fichedotation.web.dtos.others.AjoutAchatDTO;
import sn.gainde2000.fichedotation.web.dtos.others.TypeImmobilisationDTO;

import java.util.*;

@Service
@AllArgsConstructor
public class GestionAchatImpl implements IGestionAchat {

    private final ImmobilisationRepository immobilisationRepository;
    private final StatutRepository statutRepository;
   // private final ProfilRepository profilRepository;
   private final LocalisationRepository localisationRepository;
    private final OthersMapper othersMapper;

    @Override
    public Response<Object> listImmobilisations() {
        List<Immobilisation> listeImmobilisations=immobilisationRepository.findAll();
        List<AjoutAchatDTO> listeAchat = new ArrayList<>();
        for(int i=0;i<listeImmobilisations.size();i++){
            listeAchat.add(othersMapper.mapToImmobilisationDTO(listeImmobilisations.get(i)));
        }
        //List<AjoutAchatDTO> tt=othersMapper.mapToImmobilisationDTO(listeImmobilisations);
        return Response.ok().setPayload(listeAchat).setMessage("Liste des immobilisations !");
    }
    @Override
    public Response<Object> getImmobilisation(Integer id) {
        Immobilisation immobilisation = immobilisationRepository.findById(id).orElseThrow(() -> new GenericApiException("immobilisation absent!"));

        return Response.ok().setPayload(immobilisation).setMessage("immobilisation retrouvé avec succès!");
        //return Response.ok().setPayload(iImmobilisationRepository.findAll()).setMessage("Liste des marques !");
    }
    //Ajout d'un nouvel materiel
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
            immobilisation.setStatut(statutRepository.findStatutByCode("AT"));
            immobilisationRepository.save(immobilisation);
            HistoriqueLocalisation historiqueLocalisation = new HistoriqueLocalisation();
            historiqueLocalisation.setId(immobilisation.getId());
            historiqueLocalisation.setLocalisation(model.getLocalisation());
            historiqueLocalisation.setInitiateur("ACHATS");
            historiqueLocalisation.setImmobilisation(immobilisation);
            localisationRepository.save(historiqueLocalisation);
            return Response.ok().setMessage("Materiel ajouté!");
    }

    @Transactional
    @Override
    public Response<Object> updateImmobilisation(Integer id,AjoutAchatDTO dto) {
        Optional<Immobilisation> optionalImmobilisation = immobilisationRepository.findById(id);
        if (optionalImmobilisation.isEmpty()) throw new GenericApiException("Immobilisation absent!");
        Immobilisation immobilisation = optionalImmobilisation.get();
        immobilisation.setDesignation(dto.getDesignation());
        immobilisation.setDescription(dto.getDescription());
        immobilisation.setModele(dto.getModele());
        immobilisation.setStatut(statutRepository.findStatutByCode("AT"));
        immobilisation.setAccessoires(dto.getAccessoires());
        immobilisation.setDateAcquisition(dto.getDateAcquisition());
        immobilisation.setPrixAcquisition(dto.getPrixAcquisition());
        immobilisation.setRefCommercial(dto.getRefCommercial());
        immobilisation.setMarque(dto.getMarque());
        immobilisation.setFournisseur(dto.getFournisseur());
        TypeImmobilisationDTO typeImmobilisationDTO=dto.getTypeImmobilisation();
        TypeImmobilisation typeImmobilisation = othersMapper.mapToTypeImmobilisation(typeImmobilisationDTO);
        immobilisation.setTypeImmobilisation(typeImmobilisation);
      Optional<HistoriqueLocalisation> historiqueLocalisationOptional = localisationRepository.findBylocalisation(id);
        HistoriqueLocalisation historiqueLocalisation = historiqueLocalisationOptional.get();
     historiqueLocalisation.setLocalisation(dto.getLocalisation());
       historiqueLocalisation.setImmobilisation(immobilisation);
        immobilisationRepository.save(immobilisation);
       /localisationRepository.save(historiqueLocalisation);
        return Response.ok().setMessage("Immobilisation modifié avec succès !");
    }

}

