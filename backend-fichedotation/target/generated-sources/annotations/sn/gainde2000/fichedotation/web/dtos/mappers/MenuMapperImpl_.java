package sn.gainde2000.fichedotation.web.dtos.mappers;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sn.gainde2000.fichedotation.entities.Menu;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.MenuDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-17T15:17:58+0000",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class MenuMapperImpl_ implements MenuMapper {

    @Override
    public MenuDTO menuToMenuDTO(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        MenuDTO menuDTO = new MenuDTO();

        menuDTO.setId( menu.getId() );
        menuDTO.setPath( menu.getPath() );
        menuDTO.setTitle( menu.getTitle() );
        menuDTO.setType( menu.getType() );
        menuDTO.setIconType( menu.getIconType() );
        menuDTO.setCollapse( menu.getCollapse() );
        menuDTO.setAb( menu.getAb() );
        menuDTO.setPriorite( menu.getPriorite() );
        menuDTO.setChildren( menuSetToMenuDTOSet( menu.getChildren() ) );

        return menuDTO;
    }

    protected Set<MenuDTO> menuSetToMenuDTOSet(Set<Menu> set) {
        if ( set == null ) {
            return null;
        }

        Set<MenuDTO> set1 = new HashSet<MenuDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Menu menu : set ) {
            set1.add( menuToMenuDTO( menu ) );
        }

        return set1;
    }
}
