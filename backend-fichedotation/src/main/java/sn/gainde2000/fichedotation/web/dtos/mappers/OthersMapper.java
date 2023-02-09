package sn.gainde2000.fichedotation.web.dtos.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import sn.gainde2000.fichedotation.entities.*;
import sn.gainde2000.fichedotation.web.dtos.others.*;

import java.lang.reflect.Type;

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


        StatutDTO mapToStatutDTO(Statut statut);

        Statut mapToStatut( StatutDTO  statutDTO);

        EtatDTO mapToEtatDTO(Etat etat);

        Etat mapToEtat( EtatDTO etatDTO);

        FicheDotation mapToFicheDotation(AffectationFormDTO affectationFormDTO);

        AffectationFormDTO mapToFicheDotationDTO(FicheDotation ficheDotation);

        ReferenceImmoDTO mapToReferenceImmoDTO(Immobilisation immobilisation);

        Immobilisation mapToReferenceImmo(ReferenceImmoDTO referenceImmoDTO);

        TypeMaintenanceDTO mapToTypeMaintenanceDTO(TypeMaintenance typeMaintenance);

        TypeMaintenance mapToTypeMaintenance(TypeMaintenanceDTO typeMaintenanceDTO);



}
