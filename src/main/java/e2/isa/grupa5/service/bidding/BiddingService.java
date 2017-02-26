package e2.isa.grupa5.service.bidding;

import java.util.List;

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
	public synchronized Bidding create(BiddingDTO bDTO) {
		
		System.out.println("CREATE BIDDING");
		System.out.println("G ID: "+bDTO.getGroceriesId());
		
		Bidding bidding = new Bidding();
		
		Groceries g = groceriesRepository.findById(bDTO.getGroceriesId());
		Bidder b = bidderRepository.findById(bDTO.getBidderId());
		
		// PROVERA I OBRADA AKO VEC POSTOJi
		long exists = alreadyExists(bDTO.getGroceriesId(), bDTO.getBidderId());
		if(exists!=-1){
			return update(bDTO, exists);
		}
		
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

	private long alreadyExists(long groceriesId, long bidderId) {
		List<Bidding> biddings = biddingRepository.findAll();
		
		for(Bidding b : biddings) {
			if(b.getBidder().getId() == bidderId && b.getGroceries().getId() == groceriesId) {
				return b.getId();
			}
		}
		
		return -1;
	}
	
	@Transactional
	public synchronized Bidding update(BiddingDTO bDTO, long id) {
		
		System.out.println("UPDATE BIDDING");
		System.out.println("G ID: "+bDTO.getGroceriesId());
		System.out.println("B ID: "+id);
		
		Bidding b = biddingRepository.findById(id);
		
		try {
			Groceries g = groceriesRepository.findById(b.getGroceries().getId());
			
			if(g.getStatus().equals(GroceriesConstants.OPEN)) {
				setVariableAttributes(b, bDTO);
				b = biddingRepository.save(b);
			}
			
			return b;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
