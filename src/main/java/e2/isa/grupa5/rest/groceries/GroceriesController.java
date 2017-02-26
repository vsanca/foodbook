package e2.isa.grupa5.rest.groceries;

import java.util.ArrayList;
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

import e2.isa.grupa5.model.groceries.Groceries;
import e2.isa.grupa5.model.groceries.GroceriesConstants;
import e2.isa.grupa5.model.groceries.GroceriesDTO;
import e2.isa.grupa5.model.groceries.GroceryItem;
import e2.isa.grupa5.model.groceries.GroceryItemDTO;
import e2.isa.grupa5.repository.groceries.GroceriesRepository;
import e2.isa.grupa5.service.groceries.GroceriesService;
import e2.isa.grupa5.service.groceries.GroceryItemService;

/**
 * {@link Groceries} REST services.
 * 
 * Funkcionalnost 2.3:
 * - kreiranje i upravljanje ponudama za namirnice
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/groceries")
public class GroceriesController {
	
	@Autowired
	GroceriesRepository groceriesRepository;
	
	@Autowired
	GroceryItemService groceryItemService;
	
	@Autowired
	GroceriesService groceriesService;
	
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity create(@RequestBody GroceriesDTO gDTO) {
		
		Groceries g = groceriesService.create(gDTO);
		
		return new ResponseEntity<>(g, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/item/new", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity createItem(@RequestBody GroceryItemDTO giDTO) {
		
		GroceryItem gi = groceryItemService.addItem(giDTO);
		
		return new ResponseEntity<>(gi, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getAll() {
		
		return new ResponseEntity<>(groceriesRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllForRestaurant/{rId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getAllForRestaurant(@PathVariable long rId) {
		
		List<Groceries> groceries = new ArrayList<>();
		
		for(Groceries g : groceriesRepository.findAll()) {
			if(g.getRestaurant().getId() == rId) {
				groceries.add(g);
			}
		}
		
		return new ResponseEntity<>(groceries, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getItems/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getGroceriesItems(@PathVariable long id) {
		
		List<GroceryItem> giList = groceryItemService.getItemsByGroceriesId(id);
		
		return new ResponseEntity<>(giList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/open", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity open(@RequestBody long id) {
		
		Groceries g = groceriesRepository.findById(id);
		g.setStatus(GroceriesConstants.OPEN);
		g = groceriesRepository.save(g);
		
		return new ResponseEntity<>(g, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/close", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity close(@RequestBody long id) {
		
		Groceries g = groceriesRepository.findById(id);
		g.setStatus(GroceriesConstants.CLOSED);
		g = groceriesRepository.save(g);
		
		return new ResponseEntity<>(g, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/expire", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity expire(@RequestBody long id) {
		
		Groceries g = groceriesRepository.findById(id);
		g.setStatus(GroceriesConstants.EXPIRED);
		g = groceriesRepository.save(g);
		
		return new ResponseEntity<>(g, HttpStatus.OK);
	}
	
}
