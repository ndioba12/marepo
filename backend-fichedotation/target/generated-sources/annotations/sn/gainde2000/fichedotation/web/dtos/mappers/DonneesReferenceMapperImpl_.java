package sn.gainde2000.fichedotation.web.dtos.mappers;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sn.gainde2000.fichedotation.entities.TypeCession;
import sn.gainde2000.fichedotation.entities.TypeCession.TypeCessionBuilder;
import sn.gainde2000.fichedotation.web.dtos.others.TypeCessionDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-17T15:17:58+0000",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class DonneesReferenceMapperImpl_ implements DonneesReferenceMapper {

    @Override
    public TypeCessionDTO mapToTypeCessionDTO(TypeCession typeCession) {
        if ( typeCession == null ) {
            return null;
        }

        TypeCessionDTO typeCessionDTO = new TypeCessionDTO();

        typeCessionDTO.setId( typeCession.getId() );
        typeCessionDTO.setCode( typeCession.getCode() );
        typeCessionDTO.setLibelle( typeCession.getLibelle() );

        return typeCessionDTO;
    }

    @Override
    public TypeCession mapToTypeCession(TypeCessionDTO typeCessionDTO) {
        if ( typeCessionDTO == null ) {
            return null;
        }

        TypeCessionBuilder typeCession = TypeCession.builder();

        typeCession.id( typeCessionDTO.getId() );
        typeCession.code( typeCessionDTO.getCode() );
        typeCession.libelle( typeCessionDTO.getLibelle() );

        return typeCession.build();
    }
}
