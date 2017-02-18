package e2.isa.grupa5.model.users;


/**
 * {@link Waiter} for use in communication (DTO).
 * 
 * @author Boris
 *
 */
public class WaiterDTO extends UserDTO{
	
	private long restaurantId;
	
	public WaiterDTO() {
		super();
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	
}
