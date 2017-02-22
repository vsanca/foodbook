package e2.isa.grupa5.repository.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.restaurant.MenuItem;

/**
 * 
 * @author Viktor
 *
 */
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
	
	public MenuItem findById(long id);
	
}
