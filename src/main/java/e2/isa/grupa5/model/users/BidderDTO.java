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
	
	private String newPassword;
	
	public BidderDTO() {
		super();
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
