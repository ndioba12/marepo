package sn.gainde2000.fichedotation.web.dtos.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import sn.gainde2000.fichedotation.entities.*;
import sn.gainde2000.fichedotation.web.dtos.others.*;

/**
 * @author : ndiane (Ndioba Diane)
 */

@Mapper
@DecoratedWith(OthersMapperDecorator.class)
public interface OthersMapper {

        TypeCessionDTO mapToTypeCessionDTO(TypeCession typeCession);

        TypeCession mapToTypeCession(TypeCessionDTO ttypeCessionDTO);
        TypeImmobilisationDTO mapToTypeImmobilisationDTO(TypeImmobilisation typeImmobilisation);

        TypeImmobilisation mapToTypeImmobilisation(TypeImmobilisationDTO typeImmobilisationDTO);

        AjoutMaterielDTO mapToImmobilisationDTO(Immobilisation immobilisation);

        Immobilisation mapToImmobilisation(AjoutMaterielDTO immobilisationDTO);
       CatImmobilisationDTO mapToCatImmobilisationDTO(CatImmobilisation catImmobilisation);

        CatImmobilisation mapToCatImmobilisation(CatImmobilisationDTO catImmobilisationDTO);

       EtatDTO mapToEtatDTO(Etat etat);

    Etat mapToEtat( EtatDTO etatDTO);
}

