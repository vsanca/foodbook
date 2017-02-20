package e2.isa.grupa5.rest.groceries;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity create(@RequestBody GroceriesDTO gDTO) {
		
		Groceries g = groceriesService.create(gDTO);
		
		return new ResponseEntity<>(g, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/item/new", method = RequestMethod.POST)
	public ResponseEntity createItem(@RequestBody GroceryItemDTO giDTO) {
		
		GroceryItem gi = groceryItemService.addItem(giDTO);
		
		return new ResponseEntity<>(gi, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity getAll() {
		
		return new ResponseEntity<>(groceriesRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getItems/{id}", method = RequestMethod.GET)
	public ResponseEntity getGroceriesItems(@PathVariable long id) {
		
		List<GroceryItem> giList = groceryItemService.getItemsByGroceriesId(id);
		
		return new ResponseEntity<>(giList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/open", method = RequestMethod.POST)
	public ResponseEntity open(@RequestBody long id) {
		
		Groceries g = groceriesRepository.findById(id);
		g.setStatus(GroceriesConstants.OPEN);
		g = groceriesRepository.save(g);
		
		return new ResponseEntity<>(g, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public ResponseEntity close(@RequestBody long id) {
		
		Groceries g = groceriesRepository.findById(id);
		g.setStatus(GroceriesConstants.CLOSED);
		g = groceriesRepository.save(g);
		
		return new ResponseEntity<>(g, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/expire", method = RequestMethod.POST)
	public ResponseEntity expire(@RequestBody long id) {
		
		Groceries g = groceriesRepository.findById(id);
		g.setStatus(GroceriesConstants.EXPIRED);
		g = groceriesRepository.save(g);
		
		return new ResponseEntity<>(g, HttpStatus.OK);
	}
	
}
