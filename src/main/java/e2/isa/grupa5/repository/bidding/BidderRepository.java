package e2.isa.grupa5.repository.bidding;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.users.Bidder;

/**
 * 
 * @author Viktor
 *
 */
public interface BidderRepository extends JpaRepository<Bidder, Long> {
	
	public Bidder findById(long id);
	public Bidder findByEmail(String email);
	public Bidder findByEmailAndPassword(String email, String password);
	
}
