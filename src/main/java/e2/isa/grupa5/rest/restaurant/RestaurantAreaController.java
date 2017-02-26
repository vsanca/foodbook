package e2.isa.grupa5.rest.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.restaurant.RestaurantArea;
import e2.isa.grupa5.model.restaurant.RestaurantAreaDTO;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.model.users.Waiter;
import e2.isa.grupa5.repository.waiter.WaiterRepository;
import e2.isa.grupa5.service.restaurant.RestaurantAreaService;
import e2.isa.grupa5.service.restaurant.RestaurantManagerService;


/**
 * Funkcionalnost 2.3:
 * - Upravljanje segmentima restorana
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/restaurants/areas")
public class RestaurantAreaController {
	
	@Autowired
	RestaurantManagerService restaurantManagerService;
	
	@Autowired
	RestaurantAreaService restaurantAreaService;
	
	@Autowired
	WaiterRepository waiterRepository;
	
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity create(@RequestBody RestaurantAreaDTO raDTO) {
		
		RestaurantArea ra = restaurantAreaService.create(raDTO);
		
		if(ra != null) {
			return new ResponseEntity<>(ra, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/manager/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity get(@PathVariable long id) {
		RestaurantManager rm = restaurantManagerService.findById(id);
		
		List<RestaurantArea> ra = restaurantAreaService.getRestaurantAreas(rm.getRestaurant());
		
		return new ResponseEntity<>(ra, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/waiter/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getAreasByWaiterId(@PathVariable long id) {
		Waiter wt = waiterRepository.findById(id);
		
		List<RestaurantArea> ra = restaurantAreaService.getRestaurantAreas(wt.getRestaurant());
		
		if(ra != null) {
			return new ResponseEntity<>(ra, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
}
