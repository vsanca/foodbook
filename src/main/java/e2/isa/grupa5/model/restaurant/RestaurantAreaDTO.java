package e2.isa.grupa5.model.restaurant;

import java.io.Serializable;

/**
 * {@link RestaurantArea} for use in communication (DTO).
 * 
 * Funkcionalnost 2.3:
 * - Uređuje konfiguraciju sedenja u restoranu sa naznakom segmenata u restoranu
 * - Dodeljuje reon koji će svaki konobar služiti u smeni
 * 
 * @author Viktor
 *
 */
public class RestaurantAreaDTO implements Serializable{
	
	private long restaurantManagerId;
	private String name;
	private String color;
	
	public RestaurantAreaDTO() {
		
	}

	public long getRestaurantManagerId() {
		return restaurantManagerId;
	}

	public void setRestaurantManagerId(long restaurantManagerId) {
		this.restaurantManagerId = restaurantManagerId;
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
	
}
