package e2.isa.grupa5.model.users;

import e2.isa.grupa5.model.restaurant.Restaurant;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Restaurant manager is a type of {@link User} who manages a {@link Restaurant}.
 * 
 * @author Viktor
 *
 */

@Entity
@Table(name = "restaurant_manager")
public class RestaurantManager extends User implements Serializable {

    public RestaurantManager() {
    }
}
