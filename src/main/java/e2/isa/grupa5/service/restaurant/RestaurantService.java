package e2.isa.grupa5.service.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.grade.GradeDTO;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantDTO;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.service.grade.GradeService;

/**
 * Services available for {@link Restaurant}.
 * 
 * Funkcionalnost 2.3 i 2.9:
 * - kreiranje restorana
 * - ažuriranje restorana
 * 
 * @author Viktor
 *
 */
@Service
public class RestaurantService {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	/**
	 * Create new restaurant.
	 * 
	 * @param restaurantDTO
	 * @return
	 */
	public Restaurant addRestaurant(RestaurantDTO restaurantDTO) {
		Restaurant r = new Restaurant();
		
		updateRestaurant(r, restaurantDTO);
		
		try {
			r = restaurantRepository.save(r);
			return r;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Update existing restaurant.
	 * 
	 * @param restaurant
	 * @return
	 */
	public Restaurant editRestaurant(RestaurantDTO restaurantDTO) {
		Restaurant r = restaurantRepository.findById(restaurantDTO.getId());
		
		updateRestaurant(r, restaurantDTO);
		
		try {
			r = restaurantRepository.save(r);
			return r;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Update restaurant helper method for editing {@link Restaurant} instances.
	 * 
	 * @param restaurant
	 * @param restaurantDTO
	 */
	public void updateRestaurant(Restaurant restaurant, RestaurantDTO restaurantDTO) {
		restaurant.setName(restaurantDTO.getName());
		restaurant.setDescription(restaurantDTO.getDescription());
		restaurant.setAddress(restaurantDTO.getAddress());
		restaurant.setEmail(restaurantDTO.getEmail());
		restaurant.setPhone(restaurantDTO.getPhone());
	}
	
	
	public double calculateAtmosphereGrade(List<GradeDTO> restaurantGrades) {
		
		double res = 0;
		
		if(restaurantGrades == null){
			return res;
		}
		
		for(GradeDTO gDTO : restaurantGrades) {
			res += gDTO.getAtmosphereGrade();
		}
		
		if(restaurantGrades.size() == 0) {
			return 0;
		} else {
			return res/((double)restaurantGrades.size());
		}
	}
	
	public double calculateMealGrade(List<GradeDTO> restaurantGrades) {
		
		double res = 0;
		
		if(restaurantGrades == null){
			return res;
		}
		
		for(GradeDTO gDTO : restaurantGrades) {
			res += gDTO.getMealGrade();
		}
		
		if(restaurantGrades.size() == 0) {
			return 0;
		} else {
			return res/((double)restaurantGrades.size());
		}
	}

	public double calculateWaiterGrade(List<GradeDTO> restaurantGrades) {
	
	double res = 0;
	
	if(restaurantGrades == null){
		return res;
	}
	
	for(GradeDTO gDTO : restaurantGrades) {
		res += gDTO.getWaiterGrade();
	}
	
	if(restaurantGrades.size() == 0) {
		return 0;
	} else {
		return res/((double)restaurantGrades.size());
	}
}
}
