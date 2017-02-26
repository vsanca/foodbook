package e2.isa.grupa5.rest.restaurant;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantArea;
import e2.isa.grupa5.model.restaurant.RestaurantTable;
import e2.isa.grupa5.model.restaurant.RestaurantTableDTO;
import e2.isa.grupa5.model.users.Waiter;
import e2.isa.grupa5.service.restaurant.RestaurantTableService;

/**
 * Funkcionalnost 2.3:
 * - upravljanje rasporedom sedenja
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/restaurants/tables")
public class RestaurantTableController {
	
	@Autowired
	RestaurantTableService restaurantTableService;
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity create(@RequestBody RestaurantTableDTO rtDTO) {
		
		RestaurantTable rt = restaurantTableService.create(rtDTO);
		
		if(rt != null) {
			return new ResponseEntity<>(rt, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getForRestaurant/{rId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getForRestaurant(@PathVariable long rId) {
		
		return new ResponseEntity<>(restaurantTableService.findAllByRestaurantId(rId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getForManager/{mId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getForManager(@PathVariable long mId) {
		
		return new ResponseEntity<>(restaurantTableService.findAllByManagerId(mId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update/{mId}", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity completeUpdateAndReturn(@PathVariable long mId, @RequestBody RestaurantTableDTO[] tDTOs){
		
		System.out.println("STOLOVI DTO:");
		System.out.println(tDTOs.length);
		for(int i=0; i<tDTOs.length; i++) {
			System.out.println("id: "+tDTOs[i].getId());
			System.out.println("name: "+tDTOs[i].getName());
			System.out.println("seats: "+tDTOs[i].getSeats());
			System.out.println("area: "+tDTOs[i].getArea());
			System.out.println("fabric: "+tDTOs[i].getFabricTable());
		}
		
		restaurantTableService.updateAllTables(Arrays.asList(tDTOs));
		
		return new ResponseEntity<>(restaurantTableService.findAllByManagerId(mId), HttpStatus.OK);
	}
	
	
}
