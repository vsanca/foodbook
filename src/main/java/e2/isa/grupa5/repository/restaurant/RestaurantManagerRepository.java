package e2.isa.grupa5.repository.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.users.RestaurantManager;

/**
 * 
 * @author Viktor
 *
 */
public interface RestaurantManagerRepository extends JpaRepository<RestaurantManager, Long> {

	public RestaurantManager findById(long id);
	public RestaurantManager findByEmail(String email);
	public RestaurantManager findByEmailAndPassword(String email, String password);
	
}
