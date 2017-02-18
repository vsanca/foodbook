package e2.isa.grupa5.model.users;

/**
 * {@link Bartender} for use in communication (DTO).
 * 
 * @author Boris
 *
 */
public class BartenderDTO extends UserDTO{

private long restaurantId;
	
	public BartenderDTO() {
		super();
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	
}
