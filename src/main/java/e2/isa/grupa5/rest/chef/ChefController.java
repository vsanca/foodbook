package e2.isa.grupa5.rest.chef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.repository.chef.ChefRepository;

/**
 * 
 * @author Boris
 *
 */
@RestController
@RequestMapping("/chef")
public class ChefController {
	
	@Autowired
	ChefRepository chefRepository;
	
	
	
	
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	public ResponseEntity getById(@PathVariable long id) {
		
		Chef ch = chefRepository.findById(id);
		
		if(ch != null) {
			return new ResponseEntity<>(ch, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
}
