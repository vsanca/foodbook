package e2.isa.grupa5.model.users;


/**
 * {@link SystemManager} for use in communication (DTO).
 * 
 * * {@link User} type 4.
 * 
 * @author Boris
 *
 */
public class SystemManagerDTO extends UserDTO{
	
	private String oldPassword;
	private String newPassword;
	private String newPasswordRepeat;
	
	public SystemManagerDTO() {
		super();
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
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
	
}
