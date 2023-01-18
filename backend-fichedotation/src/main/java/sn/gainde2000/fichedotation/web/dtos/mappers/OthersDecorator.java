package sn.gainde2000.fichedotation.web.dtos.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import sn.gainde2000.fichedotation.entities.Immobilisation;
import sn.gainde2000.fichedotation.entities.TypeCession;
import sn.gainde2000.fichedotation.entities.TypeImmobilisation;
import sn.gainde2000.fichedotation.web.dtos.others.AjoutAchatDTO;
import sn.gainde2000.fichedotation.web.dtos.others.TypeCessionDTO;
import sn.gainde2000.fichedotation.web.dtos.others.TypeImmobilisationDTO;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Slf4j
public abstract class OthersDecorator implements OthersMapper {
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
    public Immobilisation mapToImmobilisation(AjoutAchatDTO typeImmobilisationDTO) {
        return  othersMapper.mapToImmobilisation(typeImmobilisationDTO);
    }

}

