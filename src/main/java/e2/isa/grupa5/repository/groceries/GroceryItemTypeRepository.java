package e2.isa.grupa5.repository.groceries;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.groceries.GroceryItemType;

/**
 * 
 * @author Viktor
 *
 */
public interface GroceryItemTypeRepository extends JpaRepository<GroceryItemType, Long> {
	public GroceryItemType findById(long id);
}
