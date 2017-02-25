package e2.isa.grupa5.model.users;

import e2.isa.grupa5.model.restaurant.Restaurant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Restaurant manager is a type of {@link User} who manages a {@link Restaurant}.
 * 
 * Tip korisnika 2.
 * 
 * @author Viktor
 *
 */

@Entity
@Table(name = "restaurant_manager")
public class RestaurantManager extends User implements Serializable {
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;
	
    public RestaurantManager() {
    	this.setRole(UserRoles.MANAGER);
    }
    
    public RestaurantManager(String mail, String pass, String name, String surname, String address, Restaurant restaurant) {
    	super(mail,pass,name,surname,address, UserRoles.MANAGER);
    	this.restaurant = restaurant;
    }

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
    
}
