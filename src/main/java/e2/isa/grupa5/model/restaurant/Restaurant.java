package e2.isa.grupa5.model.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Describes a restaurant instance.
 * 
 * @author Viktor
 *
 */

@Entity
@Table(name="restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id; 
	
	@Column(name="name", nullable = false)
	private String name; 
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "phone", nullable = false)
	private String phone;
	
	@Column(name= "address", nullable = false)
	private String address;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	public Restaurant() {
		
	}
	
	public Restaurant(String name, String desc, String phone, String address, String mail) {
		this.name = name;
		this.description = desc;
		this.phone = phone;
		this.address = address;
		this.email = mail;
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
