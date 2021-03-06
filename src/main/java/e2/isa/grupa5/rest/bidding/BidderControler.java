package e2.isa.grupa5.rest.bidding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.users.Bidder;
import e2.isa.grupa5.model.users.BidderDTO;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.model.users.RestaurantManagerDTO;
import e2.isa.grupa5.model.users.UserRoles;
import e2.isa.grupa5.repository.bidding.BidderRepository;
import e2.isa.grupa5.service.UserService;
import e2.isa.grupa5.service.bidding.BidderService;

/**
 * REST services for {@link Bidder}s.
 * 
 * Funkcionalnost 2.5:
 * - ažuriranje podataka ponuđača
 * - ažuriranje lozinke
 * 
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/user")
public class BidderControler {
	
	@Autowired
	BidderRepository bidderRepository;
	
	@Autowired
	BidderService bidderService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@RequestMapping(value = "/bidder/create", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity create(@RequestBody BidderDTO bDTO) {
		
		Bidder b = new Bidder();
		userService.copyData(b, bDTO);
		
		b.setRole(UserRoles.BIDDER);
		b.setActive(true);
		b.setPassword_set(false);
		
		b = bidderRepository.save(b);

		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bidder/update", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity update(@RequestBody BidderDTO bidderDTO) {
		
		Bidder b = bidderService.updateData(bidderDTO);
		
		if(b != null) {
			return new ResponseEntity<>(b, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@RequestMapping(value = "/bidder/profile/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity get(@PathVariable long id) {
		
		Bidder b = bidderRepository.findById(id);
		
		if(b != null) {
			return new ResponseEntity<>(b, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value = "/bidder/changePassword/", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity changePassword(@RequestBody BidderDTO bDTO) {
		
		Bidder b = bidderRepository.findById(bDTO.getId());
				
		if(b != null) {
			
			try {
				if(passwordEncoder.matches(bDTO.getOldPassword(), b.getPassword()) && bDTO.getNewPassword()!=null && bDTO.getNewPassword()!="") {
					b.setPassword(passwordEncoder.encode(bDTO.getNewPassword()));
					b.setPassword_set(true);
					
					b = bidderRepository.save(b);
					
					return new ResponseEntity<>(HttpStatus.OK);
					
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	}
	
	
}
