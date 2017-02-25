package e2.isa.grupa5.rest.waiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import e2.isa.grupa5.model.users.Waiter;
import e2.isa.grupa5.repository.waiter.WaiterRepository;

/**
 * 
 * @author Boris
 *
 */
@RestController
@RequestMapping("/waiter")
public class WaiterController {
	
	@Autowired
	WaiterRepository waiterRepository;
	
	
	
	
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	public ResponseEntity getById(@PathVariable long id) {
		
		Waiter w = waiterRepository.findById(id);
		
		if(w != null) {
			return new ResponseEntity<>(w, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
}
