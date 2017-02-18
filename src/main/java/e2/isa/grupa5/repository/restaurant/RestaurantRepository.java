package e2.isa.grupa5.repository.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.restaurant.Restaurant;

/**
 * 
 * @author Viktor
 *
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	public Restaurant findById(long id);
	
}
