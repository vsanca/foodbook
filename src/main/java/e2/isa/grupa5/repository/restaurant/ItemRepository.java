package e2.isa.grupa5.repository.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.restaurant.Item;

/**
 * 
 * @author Viktor
 *
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	public Item findById(long id);
	
}
