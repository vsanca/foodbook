package e2.isa.grupa5.model.users;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import e2.isa.grupa5.model.restaurant.Restaurant;

/**
 * Bartender is a type of {@link User} who serves drinks in a {@link Restaurant} behind the bar.
 * 
 * @author Boris
 *
 */

@Entity
@Table(name = "bartender")
public class Bartender extends User implements Serializable{

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;
	
    public Bartender() {
    	this.setRole(UserRoles.BARTENDER);
    }
    
    public Bartender(String mail, String pass, String name, String surname, String address, Restaurant restaurant) {
    	super(mail,pass,name,surname,address, UserRoles.BARTENDER);
    }

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
}
