package e2.isa.grupa5.rest.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.restaurant.MenuDTO;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantDTO;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.service.restaurant.MenuService;
import e2.isa.grupa5.service.restaurant.RestaurantService;

/**
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	RestaurantManagerRepository restaurantManagerRepository;
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	MenuService menuService;
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity create(@RequestBody RestaurantDTO rDTO) {
		
		Restaurant r = restaurantService.addRestaurant(rDTO);
		
		MenuDTO mDTO = new MenuDTO();
		mDTO.setRestaurantId(r.getId());
		menuService.create(mDTO);
		
		if(r != null) {
			return new ResponseEntity<>(r, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity update(@RequestBody RestaurantDTO rDTO) {
		
		Restaurant r = restaurantService.editRestaurant(rDTO);
		
		if(r != null) {
			return new ResponseEntity<>(r, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	public ResponseEntity getById(@PathVariable long id) {
		
		Restaurant r = restaurantRepository.findById(id);
		
		if(r != null) {
			return new ResponseEntity<>(r, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity getAll() {
		
		List<Restaurant> all = restaurantRepository.findAll();
		List<RestaurantDTO> rDTOs = new ArrayList<>();
		
		for(Restaurant r : all) {
			RestaurantDTO rDTO = new RestaurantDTO(r);
			
			rDTOs.add(rDTO);
		}
		
		return new ResponseEntity<>(rDTOs, HttpStatus.OK);
	}
	
	
}
