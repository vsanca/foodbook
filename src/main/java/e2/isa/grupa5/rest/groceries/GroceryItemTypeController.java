package e2.isa.grupa5.rest.groceries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.groceries.GroceryItemType;
import e2.isa.grupa5.model.groceries.GroceryItemTypeDTO;
import e2.isa.grupa5.repository.groceries.GroceryItemTypeRepository;
import e2.isa.grupa5.service.groceries.GroceryItemTypeService;

/**
 * {@link GroceryItemType} REST services.
 * 
 * Funkcionalnost 2.3:
 * - kreiranje spiska namirnica
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/groceries/type")
public class GroceryItemTypeController {

	@Autowired
	GroceryItemTypeRepository groceryItemTypeRepository;
	
	@Autowired
	GroceryItemTypeService groceryItemTypeService;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getAll() {
		return new ResponseEntity<>(groceryItemTypeRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity create(@RequestBody GroceryItemTypeDTO gitDTO) {
		
		GroceryItemType git = groceryItemTypeService.create(gitDTO);
		return new ResponseEntity<>(git, HttpStatus.OK);
	}
}
