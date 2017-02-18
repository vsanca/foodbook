package e2.isa.grupa5.model.users;

import java.sql.Date;

/**
 * {@link Bartender} for use in communication (DTO).
 * 
 * @author Boris
 *
 */
public class BartenderDTO extends UserDTO{

	private long restaurantId;
	private Date birthDate;
	private int dressSize;
	private int shoeSize;
	
	public BartenderDTO() {
		super();
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getDressSize() {
		return dressSize;
	}

	public void setDressSize(int dressSize) {
		this.dressSize = dressSize;
	}

	public int getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(int shoeSize) {
		this.shoeSize = shoeSize;
	}
	
	
	
}
