package e2.isa.grupa5.model.users;


/**
 * {@link RestaurantManager} for use in communication (DTO).
 * 
 * @author Viktor
 *
 */
public class RestaurantManagerDTO extends UserDTO{
	
	private long restaurantId;
	
	public RestaurantManagerDTO() {
		super();
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	
}
