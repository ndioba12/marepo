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

        AjoutAchatDTO mapToImmobilisationDTO(Immobilisation immobilisation);

        Immobilisation mapToImmobilisation(AjoutAchatDTO immobilisationDTO);

        HistoriqueLocalisationDTO mapToHistoriqueLocalisationDTO(HistoriqueLocalisation historiqueLocalisation);

        HistoriqueLocalisation mapToHistoriqueLocalisation(HistoriqueLocalisationDTO historiqueLocalisationDTO);
}
