package e2.isa.grupa5.repository.bidding;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.bidding.Bidding;


/**
 * 
 * @author Viktor
 *
 */
public interface BiddingItemRepository extends JpaRepository<Bidding, Long>{
	public Bidding findById(long id);
}
