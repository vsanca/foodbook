package e2.isa.grupa5.service.bidding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.users.Bidder;
import e2.isa.grupa5.model.users.BidderDTO;
import e2.isa.grupa5.repository.bidding.BidderRepository;
import e2.isa.grupa5.service.UserService;


/**
 * Funkcionalnost 2.5:
 * - ažurira svoje lične podatke i da promeni lozinku
 * 
 * @author Viktor
 *
 */
@Service
public class BidderService {
	
	@Autowired
	BidderRepository bidderRepository;
	
	@Autowired
	UserService userService;
	
	public Bidder updateData(BidderDTO bDTO) {
		Bidder b = bidderRepository.findById(bDTO.getId());
		
		userService.setVariableAttributes(b, bDTO);
		b.setEmail(bDTO.getEmail());
		
		try {
			b = bidderRepository.save(b);
			return b;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
