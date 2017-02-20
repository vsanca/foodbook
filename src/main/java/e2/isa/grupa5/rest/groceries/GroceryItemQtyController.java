package e2.isa.grupa5.rest.groceries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.groceries.GroceryItemQty;
import e2.isa.grupa5.model.groceries.GroceryItemQtyDTO;
import e2.isa.grupa5.repository.groceries.GroceryItemQuantityRepository;
import e2.isa.grupa5.service.groceries.GroceryItemQuantityService;

/**
 * {@link GroceryItemQty} REST services.
 * 
 * Funkcionalnost 2.3:
 * - objavljivanje spiska namirnica 
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/groceries/quantity")
public class GroceryItemQtyController {
	
	@Autowired
	GroceryItemQuantityRepository groceryItemQuantityRepository;
	
	@Autowired
	GroceryItemQuantityService groceryItemQuantityService;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity getAll() {
		
		return new ResponseEntity<>(groceryItemQuantityRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity create(@RequestBody GroceryItemQtyDTO giqDTO) {
		
		GroceryItemQty qty = groceryItemQuantityService.create(giqDTO);
		return new ResponseEntity<>(qty, HttpStatus.OK);
	}
	
}
