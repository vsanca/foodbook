package e2.isa.grupa5.repository.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.restaurant.ItemType;

/**
 * 
 * @author Viktor
 *
 */
public interface ItemTypeRepository extends JpaRepository<ItemType, Long> {
	
	ItemType findById(long id);
	
}
