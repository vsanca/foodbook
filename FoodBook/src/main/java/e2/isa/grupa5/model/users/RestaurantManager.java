package e2.isa.grupa5.model.users;

import e2.isa.grupa5.model.Restaurant;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Restaurant manager is a type of {@link User} who manages a {@link Restaurant}.
 * 
 * @author Viktor
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class RestaurantManager extends User {
	
}
