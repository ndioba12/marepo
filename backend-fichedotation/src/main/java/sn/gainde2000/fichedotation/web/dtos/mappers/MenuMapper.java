package sn.gainde2000.fichedotation.web.dtos.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import sn.gainde2000.fichedotation.entities.Menu;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.MenuDTO;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Mapper
@DecoratedWith(MenuMapperDecorator.class)
public interface MenuMapper {
    MenuDTO menuToMenuDTO(Menu menu);
}
