package e2.isa.grupa5.service.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantArea;
import e2.isa.grupa5.model.restaurant.RestaurantAreaDTO;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.repository.restaurant.RestaurantAreaRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

/**
 * Funkcionalnost 2.3:
 * - kreiranje konfiguracije sedenja u smislu regiona restorana
 * 
 * @author Viktor
 *
 */
@Service
public class RestaurantAreaService {
	
	@Autowired
	RestaurantAreaRepository restaurantAreaRepository;
	
	@Autowired
	RestaurantManagerRepository restaurantManagerRepository;
	
	@Autowired
	RestaurantRepository restaurantRespository;
	
	
	public RestaurantArea create(RestaurantAreaDTO raDTO) {
		
		RestaurantManager rm = restaurantManagerRepository.findById(raDTO.getRestaurantManagerId());
		Restaurant r = rm.getRestaurant();
		
		RestaurantArea area = new RestaurantArea();
		area.setName(raDTO.getName());
		area.setRestaurant(r);
		
		try {
			area = restaurantAreaRepository.save(area);
			return area;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<RestaurantArea> getRestaurantAreas(Restaurant restaurant) {
		return restaurantAreaRepository.findByRestaurant_Id(restaurant.getId());
	}
}
