package e2.isa.grupa5.repository.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.restaurant.Menu;

/**
 * 
 * @author Viktor
 *
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {

	public Menu findById(long id);
	
}
