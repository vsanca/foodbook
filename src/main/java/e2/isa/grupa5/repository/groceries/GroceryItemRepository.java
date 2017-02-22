package e2.isa.grupa5.repository.groceries;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.groceries.GroceryItem;

/**
 * 
 * @author Viktor
 *
 */
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
	GroceryItem findById(long id);
}
