package e2.isa.grupa5.model.users;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import e2.isa.grupa5.model.restaurant.Restaurant;

/**
 * Chef is a type of {@link User} who cooks food in a {@link Restaurant}.
 * 
 * @author Boris
 *
 */

@Entity
@Table(name = "chef")
public class Chef extends User implements Serializable{

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;
	
    public Chef() {
    	this.setRole(UserRoles.CHEF);
    }
    
    public Chef(String mail, String pass, String name, String surname, String address, Restaurant restaurant) {
    	super(mail,pass,name,surname,address, UserRoles.CHEF);
    }

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
}
