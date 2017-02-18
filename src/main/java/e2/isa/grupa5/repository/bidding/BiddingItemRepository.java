package e2.isa.grupa5.repository.bidding;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.bidding.BiddingItem;


/**
 * 
 * @author Viktor
 *
 */
public interface BiddingItemRepository extends JpaRepository<BiddingItem, Long>{
	public BiddingItem findById(long id);
}
