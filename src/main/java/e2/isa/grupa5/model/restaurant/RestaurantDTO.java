package e2.isa.grupa5.model.restaurant;


/**
 * {@link Restaurant} for use in communication (DTO).
 * 
 * @author Viktor
 *
 */
public class RestaurantDTO {
	
	private long id;
	private String name;
	private String description;
	private String phone;
	private String address;
	private String email;
	
	public RestaurantDTO() {
		
	}
	
	public RestaurantDTO(Restaurant restaurant) {
		this.id = restaurant.getId();
		this.name = restaurant.getName();
		this.description = restaurant.getDescription();
		this.phone = restaurant.getPhone();
		this.address = restaurant.getAddress();
		this.email = restaurant.getEmail();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
