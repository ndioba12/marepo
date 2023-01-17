package sn.gainde2000.fichedotation.web.dtos.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import sn.gainde2000.fichedotation.entities.TypeCession;
import sn.gainde2000.fichedotation.web.dtos.others.TypeCessionDTO;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Slf4j
public abstract class DonneesReferenceDecorator implements DonneesReferenceMapper {
    private DonneesReferenceMapper donneesReferenceMapper;

    @Autowired
    public void setDonneesReferenceMapper(DonneesReferenceMapper donneesReferenceMapper) {
        this.donneesReferenceMapper = donneesReferenceMapper;
    }

    @Override
    public TypeCessionDTO mapToTypeCessionDTO(TypeCession typeCession) {
        return donneesReferenceMapper.mapToTypeCessionDTO(typeCession);
    }
        @Override
        public TypeCession mapToTypeCession(TypeCessionDTO typeCessionDTO) {
            return  donneesReferenceMapper.mapToTypeCession(typeCessionDTO);
        }
    }

