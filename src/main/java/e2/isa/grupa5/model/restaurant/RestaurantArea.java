package e2.isa.grupa5.model.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * Defined area in a {@link Restaurant}.
 * 
 * Funkcionalnost 2.3:
 * - Uređuje konfiguraciju sedenja u restoranu sa naznakom segmenata u restoranu.
 * - Dodeljuje reon koji će svaki konobar služiti u smeni
 * 
 * @author Viktor
 *
 */
@Entity
@Table(name = "restaurant_area")
public class RestaurantArea {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "color", nullable = false)
    private String color;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;
	
	@PrePersist
	private void setColorIfNull() {
		if(color == null)
			color = "blue";
	}
	
	public RestaurantArea() {
		
	}
	
	public RestaurantArea(String name, Restaurant restaurant) {
		this.name = name;
		this.restaurant = restaurant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
}
