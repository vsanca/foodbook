package e2.isa.grupa5.service.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.restaurant.Menu;
import e2.isa.grupa5.model.restaurant.MenuDTO;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.repository.restaurant.MenuRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

/**
 * 
 * @author Viktor
 *
 */
@Service
public class MenuService {
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	public Menu create(MenuDTO menuDTO) {
		Menu menu = new Menu();
		
		Restaurant r = restaurantRepository.findById(menuDTO.getRestaurantId());
		
		if(r != null) {
			menu.setRestaurant(r);
			
			try {
				menuRepository.save(menu);
				return menu;
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return null;
	}
}
