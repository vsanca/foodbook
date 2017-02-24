package e2.isa.grupa5.model.users;


/**
 * {@link Bidder} for use in communication (DTO).
 * 
 * Tip korisnika 5.
 * 
 * @author Viktor
 *
 */
public class BidderDTO extends UserDTO{
	
	private String oldPassword;
	private String newPassword;
	private String newPasswordRepeat;
	
	public BidderDTO() {
		super();
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
