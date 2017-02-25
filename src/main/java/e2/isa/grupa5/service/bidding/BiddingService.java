package e2.isa.grupa5.service.bidding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import e2.isa.grupa5.model.bidding.Bidding;
import e2.isa.grupa5.model.bidding.BiddingDTO;
import e2.isa.grupa5.model.groceries.Groceries;
import e2.isa.grupa5.model.groceries.GroceriesConstants;
import e2.isa.grupa5.model.users.Bidder;
import e2.isa.grupa5.repository.bidding.BidderRepository;
import e2.isa.grupa5.repository.bidding.BiddingRepository;
import e2.isa.grupa5.repository.groceries.GroceriesRepository;

/**
 * Funkcionalnost 2.5:
 * - kreiranje i promena ponude.
 * 
 * @author Viktor
 *
 */
@Service
public class BiddingService {
	
	@Autowired
	BidderRepository bidderRepository;
	
	@Autowired
	BiddingRepository biddingRepository;
	
	@Autowired
	GroceriesRepository groceriesRepository;
	
	/**
	 * Helper method for setting possible variable attributes.
	 * 
	 * @param b
	 * @param bDTO
	 */
	private void setVariableAttributes(Bidding b, BiddingDTO bDTO) {
		b.setTimestamp(bDTO.getTimestamp());
		b.setCurrency(bDTO.getCurrency());
		b.setPrice(bDTO.getPrice());
	}
	
	@Transactional
	public Bidding create(BiddingDTO bDTO) {
		Bidding bidding = new Bidding();
		
		Groceries g = groceriesRepository.findById(bDTO.getGroceriesId());
		Bidder b = bidderRepository.findById(bDTO.getBidderId());
		
		try {
			
			if(g.getStatus().equals(GroceriesConstants.OPEN)) {
				bidding.setStatus(bDTO.getStatus());
				
				setVariableAttributes(bidding, bDTO);
				
				bidding.setGroceries(g);
				bidding.setBidder(b);
			
				bidding = biddingRepository.save(bidding);
				return bidding;
			} else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public Bidding update(BiddingDTO bDTO, long id) {
		Bidding b = biddingRepository.findById(id);
		
		try {
			Groceries g = groceriesRepository.findById(b.getGroceries().getId());
			
			if(g.getStatus().equals(GroceriesConstants.OPEN))
				setVariableAttributes(b, bDTO);
			
			b = biddingRepository.save(b);
			return b;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
