package sn.gainde2000.fichedotation.web.dtos.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import sn.gainde2000.fichedotation.entities.Menu;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.MenuDTO;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Slf4j
public abstract class MenuMapperDecorator implements MenuMapper {
    private MenuMapper menuMapper;

    @Autowired
    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public MenuDTO menuToMenuDTO(Menu menu) {
        MenuDTO menuDTO = menuMapper.menuToMenuDTO(menu);
        Set<MenuDTO> menuSet = menu.getChildren().stream().map(this::menuToMenuDTO).sorted(Comparator.comparingInt(MenuDTO::getPriorite)).collect(Collectors.toCollection(LinkedHashSet::new));

        menuDTO.setChildren(menuSet);
        return menuDTO;
    }
}
