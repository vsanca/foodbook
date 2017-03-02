package e2.isa.grupa5.model.restaurant;

import java.io.Serializable;

/**
 * {@link Menu} for use in communication (DTO).
 * 
 * Funkcionalnost 2.3:
 * - jelovnik, karta pića (u ovom slučaju meni obuhvata sve, razlikuju se tipovi stavki ({@link MenuItem})).
 * 
 * @author Viktor
 *
 */
public class MenuDTO implements Serializable {
	private long id;
	private long restaurantId;
	
	public MenuDTO() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	
}
