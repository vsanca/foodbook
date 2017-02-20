package e2.isa.grupa5.repository.chef;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.model.users.UserRoles;


/**
 * 
 * @author Boris
 *
 */
public interface ChefRepository extends JpaRepository<Chef, Long> {
	
	public Chef findById(long id);
	public Chef findByEmail(String email);
	public Chef findByEmailAndPassword(String email, String password);
	public Chef findByRoleAndRestaurant(UserRoles role, Restaurant restaurant);
	
}
