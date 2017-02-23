package e2.isa.grupa5.rest.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.model.users.RestaurantManagerDTO;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.service.restaurant.RestaurantManagerService;

/**
 * REST services for handling {@link RestaurantManager}s.
 * 
 * Funkcionalnost 2.9:
 * - registracija menadžera restorana
 * - ažuriranje i pregled informacija profila
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/user")
public class RestaurantManagerController {
	
	@Autowired
	RestaurantManagerService restaurantManagerService;
	
	@Autowired
	RestaurantManagerRepository restaurantManagerRepository;
	
	
	@RequestMapping(value = "/rmanager/create", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity create(@RequestBody RestaurantManagerDTO rmDTO) {
		
		RestaurantManager rm = restaurantManagerService.create(rmDTO);
		rm.setActive(true);
		rm.setPassword_set(false);
		
		if(rm != null) {
			return new ResponseEntity<>(rm, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/rmanager/update", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity update(@RequestBody RestaurantManagerDTO rmDTO) {
		
		RestaurantManager rm = restaurantManagerService.edit(rmDTO);
		
		if(rm != null) {
			return new ResponseEntity<>(rm, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/rmanager/profile/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity get(@PathVariable long id) {
		
		RestaurantManager rm = restaurantManagerRepository.findById(id);
		
		if(rm != null) {
			return new ResponseEntity<>(rm, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	}
}
