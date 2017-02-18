package e2.isa.grupa5.model.users;

/**
 * {@link Chef} for use in communication (DTO).
 * 
 * @author Boris
 *
 */
public class ChefDTO extends UserDTO{

private long restaurantId;
	
	public ChefDTO() {
		super();
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	
}
