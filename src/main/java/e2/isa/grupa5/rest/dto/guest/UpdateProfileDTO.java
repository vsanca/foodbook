package e2.isa.grupa5.rest.dto.guest;

public class UpdateProfileDTO {
	private String name;
	private String surname; 
	private String address; 
	
	public UpdateProfileDTO() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
