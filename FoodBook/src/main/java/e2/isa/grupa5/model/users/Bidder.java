package e2.isa.grupa5.model.users;

import e2.isa.grupa5.model.Restaurant;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Bidder represents a type of {@link User} who competes for providing groceries and 
 * services for the {@link Restaurant}.
 * 
 * @author Viktor
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Bidder extends User {
	
}
