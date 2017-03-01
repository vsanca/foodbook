package e2.isa.grupa5.repository.restaurant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.restaurant.Menu;
import e2.isa.grupa5.model.restaurant.MenuItem;

/**
 * 
 * @author Viktor
 *
 */
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
	
	public MenuItem findById(long id);
	List<MenuItem> findByMenu(Menu menu);
	
}
