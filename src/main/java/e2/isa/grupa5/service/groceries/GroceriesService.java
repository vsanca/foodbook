package e2.isa.grupa5.service.groceries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.groceries.Groceries;
import e2.isa.grupa5.model.groceries.GroceriesDTO;
import e2.isa.grupa5.repository.groceries.GroceriesRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

/**
 * 
 * @author Viktor
 *
 */
@Service
public class GroceriesService {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	GroceriesRepository groceriesRepository;
	
	
	public Groceries create(GroceriesDTO gDTO) {
		Groceries g = new Groceries();
		
		g.setFrom_date(gDTO.getFrom_date());
		g.setTo_date(gDTO.getTo_date());
		g.setStatus(gDTO.getStatus());
		g.setRestaurant(restaurantRepository.findById(gDTO.getRestaurantId()));
		
		try {
			g = groceriesRepository.save(g);
			return g;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
