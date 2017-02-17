package e2.isa.grupa5.rest.dto.guest;

import java.util.List;

public class ProfilePageDTO {
	private String name;
	private String surname;
	private String address;
	private long numberOfVisits;
	private long id;
	
	List<ProfilePageDTO> friends;

	public ProfilePageDTO() {
		
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

	public long getNumberOfVisits() {
		return numberOfVisits;
	}

	public void setNumberOfVisits(long numberOfVisits2) {
		this.numberOfVisits = numberOfVisits2;
	}

	public List<ProfilePageDTO> getFriends() {
		return friends;
	}

	public void setFriends(List<ProfilePageDTO> friends) {
		this.friends = friends;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

}
