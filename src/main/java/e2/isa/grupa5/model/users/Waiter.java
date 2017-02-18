package e2.isa.grupa5.model.users;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import e2.isa.grupa5.model.restaurant.Restaurant;

/**
 * Waiter is a type of {@link User} who attends customers in a {@link Restaurant}.
 * 
 * @author Boris
 *
 */

@Entity
@Table(name = "waiter")
public class Waiter extends User implements Serializable{

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;
	
    public Waiter() {
    	this.setRole(UserRoles.WAITER);
    }
    
    public Waiter(String mail, String pass, String name, String surname, String address, Restaurant restaurant) {
    	super(mail,pass,name,surname,address, UserRoles.WAITER);
    }

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
}
