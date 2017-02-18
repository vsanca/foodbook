package e2.isa.grupa5.service.bidding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.bidding.Bidding;
import e2.isa.grupa5.model.bidding.BiddingItem;
import e2.isa.grupa5.model.bidding.BiddingItemDTO;
import e2.isa.grupa5.model.groceries.GroceryItem;
import e2.isa.grupa5.model.groceries.GroceryItemQty;
import e2.isa.grupa5.repository.bidding.BiddingItemRepository;
import e2.isa.grupa5.repository.bidding.BiddingRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemQuantityRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemRepository;

/**
 * 
 * @author Viktor
 *
 */
@Service
public class BiddingItemService {
	
	@Autowired
	BiddingRepository biddingRepository;
	
	@Autowired
	BiddingItemRepository biddingItemRepository;
	
	@Autowired
	GroceryItemRepository groceryItemRepository;
	
	@Autowired
	GroceryItemQuantityRepository groceryItemQtyRepository;
	
	
	/**
	 * Helper method for setting variable attributes according to DTO.
	 */
	private void updateVariableAttributes(BiddingItem bi, BiddingItemDTO biDTO) {
		bi.setName(biDTO.getName());
		bi.setPrice(biDTO.getPrice());
		bi.setGroceryItemQty(groceryItemQtyRepository.findById(biDTO.getGroceryItemQtyId()));
		bi.setQuantity(biDTO.getQty());
	}
	
	public BiddingItem create(BiddingItemDTO biDTO) {
		BiddingItem bi = new BiddingItem();
		
		GroceryItem item = groceryItemRepository.findById(biDTO.getGroceryItemId());
		Bidding b = biddingRepository.findById(biDTO.getBiddingId());
		
		updateVariableAttributes(bi, biDTO);
		
		bi.setGroceryItem(item); 
		bi.setBidding(b);
		
		try {
			
			bi = biddingItemRepository.save(bi);
			return bi;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public BiddingItem update(BiddingItemDTO biDTO, long id) {
		BiddingItem bi = biddingItemRepository.findById(id);
		
		updateVariableAttributes(bi, biDTO);
		
		bi = biddingItemRepository.save(bi);
		
		return bi;
	}
}
