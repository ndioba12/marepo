/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/08/2022
 */

package sn.gainde2000.fichedotation.services.implementations.achats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.fichedotation.entities.CatImmobilisation;
import sn.gainde2000.fichedotation.entities.Immobilisation;
import sn.gainde2000.fichedotation.entities.TypeImmobilisation;
import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.exceptions.GenericApiException;
import sn.gainde2000.fichedotation.repositories.EtatRepository;
import sn.gainde2000.fichedotation.repositories.ImmobilisationRepository;
import sn.gainde2000.fichedotation.repositories.StatutRepository;
import sn.gainde2000.fichedotation.services.interfaces.achats.IGestionAchat;
import sn.gainde2000.fichedotation.web.dtos.mappers.OthersMapper;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.AjoutAchatDTO;
import sn.gainde2000.fichedotation.web.dtos.others.CatImmobilisationDTO;
import sn.gainde2000.fichedotation.web.dtos.others.ModificationAchatDTO;
import sn.gainde2000.fichedotation.web.dtos.others.TypeImmobilisationDTO;

import java.util.*;

@Service
@AllArgsConstructor
public class GestionAchatImpl implements IGestionAchat {

    private final ImmobilisationRepository immobilisationRepository;
    private final StatutRepository statutRepository;
   private final EtatRepository etatRepository;

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
        AjoutAchatDTO dto= othersMapper.mapToImmobilisationDTO(immobilisation);
        return Response.ok().setPayload(dto).setMessage("immobilisation retrouvé avec succès!");
    }
    //Ajout d'un nouvel materiel
    @Transactional
    @Override
    public Response<Object> addImmobilisation(AjoutAchatDTO model) {

            Optional<Immobilisation> optionalImmobilisation = immobilisationRepository.findByDesignation(model.getDesignation());

            if (optionalImmobilisation.isPresent())
                return Response.exception().setMessage("Cette designation est saisi est dèjà utilisé !");
            Immobilisation immobilisation = othersMapper.mapToImmobilisation(model);
            immobilisation.setStatut(statutRepository.findByCode("002").get());
            immobilisation.setEtat(etatRepository.findByCode("ET1").get());
            immobilisationRepository.save(immobilisation);
            return Response.ok().setMessage("Materiel ajouté!");
    }

    @Transactional
    @Override
    public Response<Object> updateImmobilisation(Integer id, ModificationAchatDTO dto) {

        Optional<Immobilisation> optionalImmobilisation = immobilisationRepository.findById(id);

        if (optionalImmobilisation.isEmpty()) throw new GenericApiException("Immobilisation absent!");
        Immobilisation immobilisation = optionalImmobilisation.get();
        if (dto.getDesignation() != null) {
            immobilisation.setDesignation(dto.getDesignation());
        }
        immobilisation.setDescription(dto.getDescription());
        if (dto.getModele() != null) {
            immobilisation.setModele(dto.getModele());
        }
        if (dto.getModele() != null) {
            immobilisation.setModele(dto.getModele());
        }
        if (dto.getAccessoires() != null) {
            immobilisation.setAccessoires(dto.getAccessoires());
        }
        if (dto.getDateAcquisition() != null) {
            immobilisation.setDateAcquisition(dto.getDateAcquisition());
        }
        if (dto.getPrixAcquisition() != null) {
            immobilisation.setPrixAcquisition(dto.getPrixAcquisition());
        }
        if (dto.getMarque() != null) {
            immobilisation.setMarque(dto.getMarque());
        }
        if (dto.getFournisseur() != null) {
            immobilisation.setFournisseur(dto.getFournisseur());
        }
        if (dto.getDureeGarantie() != null) {
            immobilisation.setDureeGarantie(dto.getDureeGarantie());
        }
        if (dto.getTypeImmobilisation() != null) {
            TypeImmobilisationDTO typeImmobilisationDTO = dto.getTypeImmobilisation();
            TypeImmobilisation typeImmobilisation = othersMapper.mapToTypeImmobilisation(typeImmobilisationDTO);
            immobilisation.setTypeImmobilisation(typeImmobilisation);
        }
        if (dto.getCatImmobilisation() != null) {
            CatImmobilisationDTO catImmobilisationDTO = dto.getCatImmobilisation();
            CatImmobilisation catImmobilisation = othersMapper.mapToCatImmobilisation(catImmobilisationDTO);
            immobilisation.setCatImmobilisation(catImmobilisation);
        }
        immobilisationRepository.save(immobilisation);
        return Response.ok().setMessage("Immobilisation modifié avec succès !");
    }

    @Transactional
    @Override
    public Response<Object> deleteImmobilisation(Integer id) {
        Optional<Immobilisation> optionalimmobilisation= immobilisationRepository.findById(id);
        if (optionalimmobilisation.isPresent()) immobilisationRepository.deleteById(optionalimmobilisation.get().getId());
        else throw new GenericApiException("Immobilisation introuvable");
        return Response.ok().setPayload(id).setMessage("Immobilisation"+id+" supprimé avec succès !");
    }


}

