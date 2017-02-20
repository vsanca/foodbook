package e2.isa.grupa5.repository.bartender;



import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.Bartender;
import e2.isa.grupa5.model.users.UserRoles;


/**
 * 
 * @author Boris
 *
 */
public interface BartenderRepository extends JpaRepository<Bartender, Long> {
	
	public Bartender findById(long id);
	public Bartender findByEmail(String email);
	public Bartender findByEmailAndPassword(String email, String password);
	public Bartender findByRoleAndRestaurant(UserRoles role, Restaurant restaurant);
	
}
