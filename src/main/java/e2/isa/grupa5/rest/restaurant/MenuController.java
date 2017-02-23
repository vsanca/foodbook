package e2.isa.grupa5.rest.restaurant;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.restaurant.Menu;
import e2.isa.grupa5.model.restaurant.MenuItem;
import e2.isa.grupa5.model.restaurant.MenuItemDTO;
import e2.isa.grupa5.repository.restaurant.MenuItemRepository;
import e2.isa.grupa5.repository.restaurant.MenuRepository;
import e2.isa.grupa5.service.restaurant.MenuItemService;

/**
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/restaurant")
public class MenuController {
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	MenuItemRepository menuItemRepository;
	
	@Autowired
	MenuItemService menuItemService;
	
	
	@RequestMapping(value = "/menu/{id_restaurant}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getRestaurantMenu(@PathVariable long id_restaurant) {
		
		for(Menu m : menuRepository.findAll()) {
			
			if(m.getRestaurant().getId() == id_restaurant) {
				return new ResponseEntity<>(m, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/menuItem/create", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity createItem(@RequestBody MenuItemDTO miDTO) {
		
		MenuItem mi = menuItemService.create(miDTO);
		
		if(mi != null) {
			return new ResponseEntity<>(mi, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/menuItem/all/{id_restaurant}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getRestaurantMenuItems(@PathVariable long id_restaurant) {
		
		Menu menu = new Menu();
		Set<MenuItem> menuItems = new HashSet<>();
		
		for (Menu m : menuRepository.findAll()) {
            if (m.getRestaurant().getId() == id_restaurant) {
                menu = m;
                break;
            }
        }
		
        for (MenuItem mi : menuItemRepository.findAll()){
            if (mi.getMenu().getId() == menu.getId()){
                menuItems.add(mi);
            }
        }
        
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
	}
}
