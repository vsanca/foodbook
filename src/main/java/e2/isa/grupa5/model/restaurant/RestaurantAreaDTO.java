package e2.isa.grupa5.model.restaurant;


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
public class RestaurantAreaDTO {
	
	private long restaurantManagerId;
	private String name;
	
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

}
