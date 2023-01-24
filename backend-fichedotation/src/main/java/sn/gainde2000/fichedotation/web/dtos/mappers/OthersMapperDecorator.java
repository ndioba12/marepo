package sn.gainde2000.fichedotation.web.dtos.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import sn.gainde2000.fichedotation.entities.HistoriqueLocalisation;
import sn.gainde2000.fichedotation.entities.Immobilisation;
import sn.gainde2000.fichedotation.entities.TypeCession;
import sn.gainde2000.fichedotation.entities.TypeImmobilisation;
import sn.gainde2000.fichedotation.web.dtos.others.AjoutAchatDTO;
import sn.gainde2000.fichedotation.web.dtos.others.HistoriqueLocalisationDTO;
import sn.gainde2000.fichedotation.web.dtos.others.TypeCessionDTO;
import sn.gainde2000.fichedotation.web.dtos.others.TypeImmobilisationDTO;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Slf4j
public abstract class OthersMapperDecorator implements OthersMapper {
    private OthersMapper othersMapper;

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
    public AjoutAchatDTO mapToImmobilisationDTO(Immobilisation immobilisation) {
        return othersMapper.mapToImmobilisationDTO(immobilisation);
    }
    @Override
    public Immobilisation mapToImmobilisation(AjoutAchatDTO immobilisationDTO) {
        return  othersMapper.mapToImmobilisation(immobilisationDTO);
        /*Immobilisation immobilisation = othersMapper.mapToImmobilisation(immobilisationDTO);
        immobilisation.setDesignation(immobilisationDTO.getDesignation());
        immobilisation.setDescription(immobilisationDTO.getDescription());
        immobilisation.setMarque(immobilisationDTO.getMarque());
        immobilisation.setModele(immobilisationDTO.getModele());
        immobilisation.setFournisseur(immobilisationDTO.getFournisseur());
        immobilisation.setReferenceInterne(immobilisationDTO.getRefInterne());
        immobilisation.setRefCommercial(immobilisationDTO.getRefCommercial());
        immobilisation.setDureeGarantie(immobilisationDTO.getDureeGarantie());
        immobilisation.setDateAcquisition(immobilisationDTO.getDateAcquisition());
        immobilisation.setPrixAcquisition(immobilisationDTO.getPrixAcquisition());
        immobilisation.setAccessoires(immobilisationDTO.getAccessoires());*/
    }

    @Override
    public HistoriqueLocalisationDTO mapToHistoriqueLocalisationDTO(HistoriqueLocalisation historiqueLocalisation) {
        return othersMapper.mapToHistoriqueLocalisationDTO(historiqueLocalisation);
    }
    @Override
    public HistoriqueLocalisation mapToHistoriqueLocalisation(HistoriqueLocalisationDTO historiqueLocalisationDTO) {
        return  othersMapper.mapToHistoriqueLocalisation(historiqueLocalisationDTO);
    }

}

