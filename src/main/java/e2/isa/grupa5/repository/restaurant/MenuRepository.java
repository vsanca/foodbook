package e2.isa.grupa5.repository.restaurant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.restaurant.Menu;
import e2.isa.grupa5.model.restaurant.Restaurant;

/**
 * 
 * @author Viktor
 *
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {

	Menu findById(long id);
	List<Menu> findByRestaurant(Restaurant restaurant);
	
}
