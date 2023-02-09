/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/08/2022
 */

package sn.gainde2000.fichedotation.services.implementations.gestionMateriels;

import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.fichedotation.entities.*;
import sn.gainde2000.fichedotation.exceptions.GenericApiException;
import sn.gainde2000.fichedotation.repositories.EtatRepository;
import sn.gainde2000.fichedotation.repositories.ImmobilisationRepository;
import sn.gainde2000.fichedotation.repositories.StatutRepository;
import sn.gainde2000.fichedotation.services.interfaces.GestionMareriels.IGestionMateriels;
import sn.gainde2000.fichedotation.web.dtos.mappers.OthersMapper;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GestionMaterielsImpl implements IGestionMateriels {

    private final ImmobilisationRepository immobilisationRepository;
    private final StatutRepository statutRepository;
   private final EtatRepository etatRepository;

    private final OthersMapper othersMapper;


    //Liste des immobilisations
    @Override
    public Response<Object> listImmobilisations(int page, int size, String filter) {
        BooleanBuilder builder = new BooleanBuilder();

        Page<Immobilisation> immobilisationPage;
        if (StringUtils.isNotBlank(filter)) {
            builder.andAnyOf(
                    QImmobilisation.immobilisation.designation.containsIgnoreCase(filter),
                    QImmobilisation.immobilisation.typeImmobilisation.libelle.containsIgnoreCase(filter),
                    QImmobilisation.immobilisation.fournisseur.containsIgnoreCase(filter),
                    QImmobilisation.immobilisation.prixAcquisition.stringValue().contains(filter),
                    QImmobilisation.immobilisation.dateAcquisition.stringValue().contains(filter),
                    QImmobilisation.immobilisation.statut.libelle.containsIgnoreCase(filter)
            );
        }

        immobilisationPage = Objects.nonNull(builder.getValue()) ? immobilisationRepository.findAll(builder.getValue(), PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"))) : immobilisationRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));

        Response.PageMetadata pageMetadata = Response.PageMetadata.builder()
                .size(immobilisationPage.getSize())
                .number(immobilisationPage.getNumber())
                .totalElements(immobilisationPage.getTotalElements())
                .totalPages(immobilisationPage.getTotalPages())
                .build();

        return Response
                .ok()
                .setPayload(
                        immobilisationPage.getContent()
                                .stream()
                                .map(immobilisation ->
                                        ListMaterielsDTO.builder()
                                                .id(immobilisation.getId())
                                                .designation(immobilisation.getDesignation())
                                                .typeImmobilisation(othersMapper.mapToTypeImmobilisationDTO(immobilisation.getTypeImmobilisation()))
                                                .fournisseur(immobilisation.getFournisseur())
                                                .prixAcquisition(immobilisation.getPrixAcquisition())
                                                .dateAcquisition(immobilisation.getDateAcquisition())
                                                .etat(othersMapper.mapToEtatDTO(immobilisation.getEtat()))
                                                .build()
                                )
                                .collect(Collectors.toList())
                )
                .setMetadata(pageMetadata).setMessage("Liste des utilisateurs !");
    }


    @Override
    public Response<Object> getImmobilisation(Integer id) {
        Immobilisation immobilisation = immobilisationRepository.findById(id).orElseThrow(() -> new GenericApiException("immobilisation absent!"));
        AjoutMaterielDTO dto= othersMapper.mapToImmobilisationDTO(immobilisation);
        return Response.ok().setPayload(dto).setMessage("immobilisation retrouvé avec succès!");
    }
    //Ajout d'un nouvel materiel
    @Transactional
    @Override
    public Response<Object> addImmobilisation(AjoutMaterielDTO model) {

            Optional<Immobilisation> optionalImmobilisation = immobilisationRepository.findByDesignation(model.getDesignation());

            if (optionalImmobilisation.isPresent())
                return Response.exception().setMessage("Cette designation est saisi est dèjà utilisé !");
            Immobilisation immobilisation = othersMapper.mapToImmobilisation(model);
            immobilisation.setStatut(statutRepository.findById(0).get());
            immobilisation.setEtat(etatRepository.findById(0).get());
            immobilisationRepository.save(immobilisation);
            return Response.ok().setMessage("Materiel ajouté!");
    }

    @Transactional
    @Override
    public Response<Object> updateImmobilisation(Integer id, ModifMaterielDTO dto) {

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

