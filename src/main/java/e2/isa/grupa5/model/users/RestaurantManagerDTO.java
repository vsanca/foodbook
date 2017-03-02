package e2.isa.grupa5.model.users;


/**
 * {@link RestaurantManager} for use in communication (DTO).
 * 
 * Tip korisnika 2.
 * 
 * @author Viktor
 *
 */
public class RestaurantManagerDTO extends UserDTO{
	
	private long restaurantId;
	
	private String oldPassword;
	private String newPassword;
	private String newPasswordRepeat;
	
	public RestaurantManagerDTO() {
		super();
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordRepeat() {
		return newPasswordRepeat;
	}

	public void setNewPasswordRepeat(String newPasswordRepeat) {
		this.newPasswordRepeat = newPasswordRepeat;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
}
