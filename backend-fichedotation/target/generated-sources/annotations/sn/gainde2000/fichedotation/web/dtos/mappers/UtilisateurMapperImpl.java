package sn.gainde2000.fichedotation.web.dtos.mappers;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import sn.gainde2000.fichedotation.entities.Utilisateur;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.UtilisateurDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-17T15:17:58+0000",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
@Primary
public class UtilisateurMapperImpl extends UtilisateurMapperDecorator implements UtilisateurMapper {

    @Autowired
    @Qualifier("delegate")
    private UtilisateurMapper delegate;

    @Override
    public Utilisateur map(UtilisateurDTO dto)  {
        return delegate.map( dto );
    }

    @Override
    public UtilisateurDTO map(Utilisateur utilisateur)  {
        return delegate.map( utilisateur );
    }
}
