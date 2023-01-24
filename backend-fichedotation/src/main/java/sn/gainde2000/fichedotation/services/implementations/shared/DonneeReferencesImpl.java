/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/08/2022
 */

package sn.gainde2000.fichedotation.services.implementations.shared;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.fichedotation.entities.TypeCession;
import sn.gainde2000.fichedotation.repositories.MarqueRepository;
import sn.gainde2000.fichedotation.repositories.ProfilRepository;
import sn.gainde2000.fichedotation.repositories.StatutRepository;
import sn.gainde2000.fichedotation.repositories.TypeCessionRepository;
import sn.gainde2000.fichedotation.services.interfaces.shared.IDonneeReferences;
import sn.gainde2000.fichedotation.web.dtos.mappers.OthersMapper;
import sn.gainde2000.fichedotation.web.dtos.mappers.UtilisateurMapper;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.TypeCessionDTO;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DonneeReferencesImpl implements IDonneeReferences {
    private final ProfilRepository profilRepository;
    private final MarqueRepository marqueRepository;
    private final StatutRepository statutRepository;
    private final TypeCessionRepository typeCessionRepository;
    //private final UtilisateurMapper utilisateurMapper;
    private final OthersMapper othersMapper;

    @Override
    public Response<Object> listProfil() {
        return Response.ok().setPayload(profilRepository.findAll()).setMessage("Liste des profils !");
    }
    @Override
    public Response<Object> listMarque() {
        return Response.ok().setPayload(marqueRepository.findAll()).setMessage("Liste des marques !");
    }

    @Override
    public Response<Object> listypeCession() {
        return Response.ok().setPayload(typeCessionRepository.findAll()).setMessage("Liste des types cessions !");
    }
    @Override
    public Response<Object> listStatut() {
        return Response.ok().setPayload(statutRepository.findAll()).setMessage("Liste des types statuts !");
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
}

