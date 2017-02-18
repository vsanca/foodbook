package e2.isa.grupa5.repository.groceries;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.groceries.GroceryItemQty;

/**
 * 
 * @author Viktor
 *
 */
public interface GroceryItemQuantityRepository extends JpaRepository<GroceryItemQty, Long> {
	public GroceryItemQty findById(long id);
}
