package e2.isa.grupa5.rest.bartender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.users.Bartender;
import e2.isa.grupa5.repository.bartender.BartenderRepository;



/**
 * 
 * @author Boris
 *
 */
@RestController
@RequestMapping("/bartender")
public class BartenderController {
	
	@Autowired
	BartenderRepository bartenderRepository;
	
	
	
	
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	public ResponseEntity getById(@PathVariable long id) {
		
		Bartender b = bartenderRepository.findById(id);
		
		if(b != null) {
			return new ResponseEntity<>(b, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
}
