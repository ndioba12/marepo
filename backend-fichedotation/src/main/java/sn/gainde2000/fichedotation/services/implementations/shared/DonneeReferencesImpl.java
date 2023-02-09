/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/08/2022
 */

package sn.gainde2000.fichedotation.services.implementations.shared;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.fichedotation.entities.Entite;

import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.repositories.*;
import sn.gainde2000.fichedotation.entities.TypeCession;

import sn.gainde2000.fichedotation.services.interfaces.shared.IDonneeReferences;
import sn.gainde2000.fichedotation.web.dtos.mappers.OthersMapper;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.EntiteDTO;

import java.util.Optional;
import sn.gainde2000.fichedotation.web.dtos.others.TypeCessionDTO;


@Service
@AllArgsConstructor
@Slf4j
public class DonneeReferencesImpl implements IDonneeReferences {
    private final ProfilRepository profilRepository;

    private final StatutRepository statutRepository;

    private final TypeCessionRepository typeCessionRepository;

    private final OthersMapper othersMapper;

    private final EntiteRepository entiteRepository;

    private final TypeImmobilisationRepository typeImmobilisationRepository;
    private final CatImmobilisationRepository catImmobilisationRepository;
    //private final UtilisateurMapper utilisateurMapper;


    private final EtatRepository etatRepository;

    private final TypeMaintenanceRepository typeMaintenanceRepository;


    @Override
    public Response<Object> listProfil() {
        return Response.ok().setPayload(profilRepository.findAll()).setMessage("Liste des profils !");
    }


    @Override
    public Response<Object> listEntite() {
        return Response.ok().setPayload(entiteRepository.findAll()).setMessage("Liste des entités ou départements !");
    }

    @Transactional
    @Override
    public Response<Object> saveEntite(EntiteDTO model) {
        Optional<Entite> optionalEntite = entiteRepository.findByCode(model.getLibelle());

        if (optionalEntite.isPresent()) {
            return Response.exception().setMessage("Cette entité saisi est dèjà utilisé !");
        }else {
            Entite entite = new Entite();
            entite.setCode(model.getCode());
            log.info(model.getCode());
            entite.setLibelle(model.getLibelle());
            entiteRepository.save(entite);
            return Response.ok().setMessage("Entité ajouté!");
        }

    }


    @Override
    public Response<Object> listypeCession() {
        return Response.ok().setPayload(typeCessionRepository.findAll()).setMessage("Liste des types cessions !");
    }
    @Override
    public Response<Object> listStatut() {
        return Response.ok().setPayload(statutRepository.findAll()).setMessage("Liste des types statuts !");
    }

    @Override
    public Response<Object> listEtat() {
        return Response.ok().setPayload(etatRepository.findAll()).setMessage("Liste des types etats !");
    }

    public Response<Object> listeTypeMaintenances(){
        return Response.ok().setPayload(typeMaintenanceRepository.findAll()).setMessage("Liste des types de maintenances !");
    }

    @Transactional
    @Override
    public Response<Object> addTypeCession(TypeCessionDTO model) {
        Optional<TypeCession> optionalTypeCession = typeCessionRepository.findByCode(model.getLibelle());

        if (optionalTypeCession.isPresent()) return Response.exception().setMessage("Ce type de cession saisi est dèjà utilisé !");
        TypeCession typeCession = othersMapper.mapToTypeCession(model);
       // TypeCession typeCession = new TypeCession();
       // typeCession.setCode(model.getCode());
      //  typeCession.setLibelle(model.getLibelle());
        typeCessionRepository.save(typeCession);
        return Response.ok().setMessage("Type cession ajouté!");

    }

    @Override
    public Response<Object> listTypeMateriels() {
        return Response.ok().setPayload(typeImmobilisationRepository.findAll()).setMessage("Liste des type de materiels !");
    }
    @Override
    public Response<Object> listCatMateriels() {
        return Response.ok().setPayload(catImmobilisationRepository.findAll()).setMessage("Liste des categories de materiels !");
    }
}

