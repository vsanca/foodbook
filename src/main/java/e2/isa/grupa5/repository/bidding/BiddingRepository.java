package e2.isa.grupa5.repository.bidding;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.bidding.Bidding;

/**
 * 
 * @author Viktor
 *
 */
public interface BiddingRepository extends JpaRepository<Bidding, Long>{
	public Bidding findById(long id);
	public List<Bidding> findByBidder_Id(long id);
}
