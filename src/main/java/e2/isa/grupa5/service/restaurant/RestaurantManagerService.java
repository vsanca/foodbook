package e2.isa.grupa5.service.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.model.users.RestaurantManagerDTO;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.service.UserService;

/**
 * Funkcionalnost 2.9 i 2.5:
 * - kreiranje menadžera restorana
 * - ažuriranje njihovih profila
 * 
 * @author Viktor
 *
 */
@Service
public class RestaurantManagerService {

	@Autowired
	RestaurantManagerRepository restaurantManagerRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired 
	UserService userService;
	
	
	public RestaurantManager findById(long id) {
		return restaurantManagerRepository.findById(id);
	}
	
	public RestaurantManager create(RestaurantManagerDTO rmDTO) {
		RestaurantManager restaurantManager = new RestaurantManager();
		
		restaurantManager = setFromDTO(restaurantManager, rmDTO);
		
		return restaurantManager;
	}
	
	/**
	 * Helper method for setting {@link RestaurantManager} from DTO object.
	 * 
	 * @param rm
	 * @param rmDTO
	 * @return
	 */
	private RestaurantManager setFromDTO(RestaurantManager rm, RestaurantManagerDTO rmDTO) {
		try {
			userService.copyData(rm, rmDTO);
			
			Restaurant r = restaurantRepository.findById(rmDTO.getId());
			
			if(r != null) {
				rm.setRestaurant(r);
				
				RestaurantManager rmPersistent = restaurantManagerRepository.save(rm);
				restaurantRepository.save(r);
				
				return rmPersistent;
			} else {
				return null;
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public RestaurantManager edit(RestaurantManagerDTO rmDTO) {
		RestaurantManager current = restaurantManagerRepository.findByEmail(rmDTO.getEmail());
		
		userService.setVariableAttributes(current, rmDTO);
		
		try {
			current = restaurantManagerRepository.save(current);
			return current;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
