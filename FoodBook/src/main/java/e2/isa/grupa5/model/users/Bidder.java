package e2.isa.grupa5.model.users;

import e2.isa.grupa5.model.restaurant.Restaurant;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Bidder represents a type of {@link User} who competes for providing groceries and 
 * services for the {@link Restaurant}.
 * 
 * @author Viktor
 *
 */

@Entity
@Table(name = "bidder")
public class Bidder extends User implements Serializable {
    public Bidder() {
    }
}
