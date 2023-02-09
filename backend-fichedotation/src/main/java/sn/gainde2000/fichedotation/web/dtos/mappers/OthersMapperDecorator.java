package sn.gainde2000.fichedotation.web.dtos.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import sn.gainde2000.fichedotation.entities.*;
//import sn.gainde2000.fichedotation.web.dtos.others.*;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Slf4j
public abstract class OthersMapperDecorator implements OthersMapper {
    private OtherM othersMapper;

    @Autowired
    public void setDonneesReferenceMapper(OthersMapper othersMapper) {
        this.othersMapper = othersMapper;
    }
    @Override
    public TypeCessionDTO mapToTypeCessionDTO(TypeCession typeCession) {
        return othersMapper.mapToTypeCessionDTO(typeCession);
    }
    @Override
    public TypeCession mapToTypeCession(TypeCessionDTO typeCessionDTO) {
        return  othersMapper.mapToTypeCession(typeCessionDTO);
    }

    @Override
    public TypeImmobilisationDTO mapToTypeImmobilisationDTO(TypeImmobilisation typeImmobilisation) {
        return othersMapper.mapToTypeImmobilisationDTO(typeImmobilisation);
    }
    @Override
    public TypeImmobilisation mapToTypeImmobilisation(TypeImmobilisationDTO typeImmobilisationDTO) {
        return  othersMapper.mapToTypeImmobilisation(typeImmobilisationDTO);
    }

    @Override
    public AjoutMaterielDTO mapToImmobilisationDTO(Immobilisation immobilisation) {
        return othersMapper.mapToImmobilisationDTO(immobilisation);
    }
    @Override
    public Immobilisation mapToImmobilisation(AjoutMaterielDTO immobilisationDTO) {
        return  othersMapper.mapToImmobilisation(immobilisationDTO);
    }
    @Override
    public CatImmobilisationDTO mapToCatImmobilisationDTO(CatImmobilisation catImmobilisation) {
        return othersMapper.mapToCatImmobilisationDTO(catImmobilisation);
    }
    @Override
    public CatImmobilisation mapToCatImmobilisation(CatImmobilisationDTO catImmobilisationDTO) {
        return  othersMapper.mapToCatImmobilisation(catImmobilisationDTO);
    }

    @Override
    public EtatDTO mapToEtatDTO(Etat etat) {
        return othersMapper.mapToEtatDTO(etat);
    }
    @Override
    public Etat mapToEtat(EtatDTO etatDTO) {
        return  othersMapper.mapToEtat(etatDTO);
    }

    @Override
    public FicheDotation mapToFicheDotation(AffectationFormDTO affectationFormDTO){
        return othersMapper.mapToFicheDotation(affectationFormDTO);
    }

    @Override
    public AffectationFormDTO mapToFicheDotationDTO(FicheDotation ficheDotation){
        return othersMapper.mapToFicheDotationDTO(ficheDotation);
    }

    @Override
    public ReferenceImmoDTO mapToReferenceImmoDTO(Immobilisation immobilisation){
        return othersMapper.mapToReferenceImmoDTO(immobilisation);
    }

    @Override
    public Immobilisation mapToReferenceImmo(ReferenceImmoDTO referenceImmoDTO){
        return othersMapper.mapToReferenceImmo(referenceImmoDTO);
    }



    @Override
    public TypeMaintenanceDTO mapToTypeMaintenanceDTO(TypeMaintenance typeMaintenance){
        return othersMapper.mapToTypeMaintenanceDTO(typeMaintenance);
    }

    @Override
    public TypeMaintenance mapToTypeMaintenance(TypeMaintenanceDTO typeMaintenanceDTO){
        return othersMapper.mapToTypeMaintenance(typeMaintenanceDTO);
    }

    @Override
    public EtatDTO mapToEtatDTO(Etat etat) {
        return othersMapper.mapToEtatDTO(etat);
    }
    @Override
    public Etat mapToEtat(EtatDTO etatDTO) {
        return  othersMapper.mapToEtat(etatDTO);
    }



}

