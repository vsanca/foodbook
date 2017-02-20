package e2.isa.grupa5.rest.bidding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.users.Bidder;
import e2.isa.grupa5.model.users.BidderDTO;
import e2.isa.grupa5.model.users.UserRoles;
import e2.isa.grupa5.repository.bidding.BidderRepository;
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
	
	
	@RequestMapping(value = "/bidder/create", method = RequestMethod.POST)
	public ResponseEntity create(@RequestBody Bidder bidder) {
		
		bidder.setRole(UserRoles.BIDDER);
		bidder.setActive(false);
		
		bidder = bidderRepository.save(bidder);

		return new ResponseEntity<>(bidder, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bidder/update", method = RequestMethod.POST)
	public ResponseEntity update(@RequestBody BidderDTO bidderDTO) {
		
		Bidder b = bidderService.updateData(bidderDTO);
		
		if(b != null) {
			return new ResponseEntity<>(b, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@RequestMapping(value = "/bidder/profile/{id}", method = RequestMethod.GET)
	public ResponseEntity get(@PathVariable long id) {
		
		Bidder b = bidderRepository.findById(id);
		
		if(b != null) {
			return new ResponseEntity<>(b, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/bidder/updatePassword", method = RequestMethod.POST) 
	public ResponseEntity updatePassword(@RequestBody BidderDTO bDTO) {
		
		Bidder b = bidderRepository.findById(bDTO.getId());
		
		if(bDTO.getPassword().equals(b.getPassword())){
			b.setPassword(bDTO.getNewPassword());
			
			b = bidderRepository.save(b);
			
			return new ResponseEntity<>(b, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
