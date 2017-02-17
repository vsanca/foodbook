package e2.isa.grupa5.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.repository.GuestRepository;
import e2.isa.grupa5.service.guest.GuestService;

@RestController
@RequestMapping("/rest/test")
public class TestController {

    @Autowired
    private GuestRepository guestRepository;
    
    
    @RequestMapping(method = RequestMethod.GET, value= "/fill-database")
	public ResponseEntity<?> getProfilePageInfo() {
		
    	Guest g1 = new Guest();
    	g1.setActive(true);
    	g1.setAddress("Test ADresa 1");
    	g1.setEmail("email1@email.com");
    	g1.setName("Name1");
    	g1.setSurname("Surname1");
    	g1.setPassword("Password1");
    
    	Guest g2 = new Guest();
    	g2.setActive(true);
    	g2.setAddress("Test ADresa 2");
    	g2.setEmail("email2@email.com");
    	g2.setName("Name2");
    	g2.setSurname("Surname2");
    	g2.setPassword("Password2");
    	
    	guestRepository.save(g1);
    	guestRepository.save(g2);
    	g1.getFriends().add(g2);
    	g2.getFriends().add(g1);
    	
    	guestRepository.save(g1);
    	guestRepository.save(g2);
    	
    	
    	
    	
    	return new ResponseEntity<>(HttpStatus.OK);
	}

	
}
