/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/08/2022
 */

package sn.gainde2000.fichedotation.services.implementations.shared;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.fichedotation.entities.TypeCession;
import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.repositories.MarqueRepository;
import sn.gainde2000.fichedotation.repositories.ProfilRepository;
import sn.gainde2000.fichedotation.repositories.TypeCessionRepository;
import sn.gainde2000.fichedotation.services.interfaces.shared.IDonneeReferences;
import sn.gainde2000.fichedotation.web.dtos.mappers.DonneesReferenceMapper;
import sn.gainde2000.fichedotation.web.dtos.mappers.UtilisateurMapper;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.TypeCessionDTO;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DonneeReferencesImpl implements IDonneeReferences {
    private final ProfilRepository profilRepository;
    private final MarqueRepository marqueRepository;
    private final TypeCessionRepository typeCessionRepository;
    private final UtilisateurMapper utilisateurMapper;
    private final DonneesReferenceMapper donneesReferenceMapper;

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

    @Transactional
    @Override
    public Response<Object> saveTypeCession(TypeCessionDTO model) {
        Optional<TypeCession> optionalTypeCession = typeCessionRepository.findByCode(model.getLibelle());

        if (optionalTypeCession.isPresent()) return Response.exception().setMessage("Ce type de cession saisi est dèjà utilisé !");
        TypeCession typeCession = donneesReferenceMapper.mapToTypeCession(model);
        //TypeCession typeCession = new TypeCession();
        typeCession.setCode(model.getCode());
        typeCession.setLibelle(model.getLibelle());
        typeCessionRepository.save(typeCession);
        return Response.ok().setMessage("Type cession ajouté!");
    }
}

