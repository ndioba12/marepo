package sn.gainde2000.fichedotation.web.dtos.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import sn.gainde2000.fichedotation.entities.TypeCession;
import sn.gainde2000.fichedotation.web.dtos.others.TypeCessionDTO;

/**
 * @author : ndiane (Ndioba Diane)
 */

@Mapper
@DecoratedWith(DonneesReferenceDecorator.class)
public interface DonneesReferenceMapper {
        TypeCessionDTO mapToTypeCessionDTO(TypeCession typeCession);
        TypeCession mapToTypeCession(TypeCessionDTO typeCessionDTO);
}
