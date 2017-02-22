package e2.isa.grupa5.repository.waiter;




import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.restaurant.Restaurant;

import e2.isa.grupa5.model.users.UserRoles;
import e2.isa.grupa5.model.users.Waiter;


/**
 * 
 * @author Boris
 *
 */
public interface WaiterRepository extends JpaRepository<Waiter, Long> {
	
	public Waiter findById(long id);
	public Waiter findByEmail(String email);
	public Waiter findByEmailAndPassword(String email, String password);
	public Waiter findByRoleAndRestaurant(UserRoles role, Restaurant restaurant);
	
}
