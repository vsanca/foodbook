package e2.isa.grupa5.model.restaurant;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Describes a restaurant instance.
 * 
 * Funkcionalnost 2.3:
 * - Menadžer restorana može da uređuje profil restorana.
 * 
 * @author Viktor
 *
 */

@Entity
@Table(name="restaurant")
public class Restaurant implements Serializable{

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
	
//	@Version
    private int version;
	
	public Restaurant() {
		
	}
	
	public Restaurant(String name, String desc, String phone, String address, String mail) {
		this.name = name;
		this.description = desc;
		this.phone = phone;
		this.address = address;
		this.email = mail;
	}
	
	
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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
