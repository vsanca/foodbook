package e2.isa.grupa5.model.restaurant;


/**
 * {@link Menu} for use in communication (DTO).
 * 
 * @author Viktor
 *
 */
public class MenuDTO {
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
