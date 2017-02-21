package e2.isa.grupa5.model.users;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import e2.isa.grupa5.model.restaurant.Restaurant;

/**
 * Chef is a type of {@link User} who cooks food in a {@link Restaurant}.
 * 
 * * {@link User} type 3.
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
	
	@Column(name="birth_date", nullable = false)
	private Date birthDate;
	
	@Column(name="dress_size", nullable = false)
	private int dressSize;
	
	@Column(name="shoe_size", nullable = false)
	private int shoeSize;
	
    public Chef() {
    	this.setRole(UserRoles.CHEF);
    }
    
    public Chef(String mail, String pass, String name, String surname, String address, Restaurant restaurant, Date birthDate, int dressSize, int shoeSize) {
    	super(mail,pass,name,surname,address, UserRoles.CHEF);
    	this.birthDate = birthDate;
    	this.dressSize = dressSize;
    	this.shoeSize = shoeSize;
    	this.restaurant = restaurant;
    }

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getDressSize() {
		return dressSize;
	}

	public void setDressSize(int dressSize) {
		this.dressSize = dressSize;
	}

	public int getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(int shoeSize) {
		this.shoeSize = shoeSize;
	}
	
	
	
}
