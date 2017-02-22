package e2.isa.grupa5.rest.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.repository.restaurant.ItemTypeRepository;

/**
 * Future expansions on different menu item types.
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/menu")
public class ItemTypeController {
	
	@Autowired
	ItemTypeRepository itemTypeRepository;
	
	@RequestMapping(value = "/itemTypes/all", method = RequestMethod.GET)
	public ResponseEntity getAll() {
		return new ResponseEntity<>(itemTypeRepository.findAll(), HttpStatus.OK);
	}

}
