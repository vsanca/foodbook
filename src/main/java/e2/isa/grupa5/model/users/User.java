package e2.isa.grupa5.model.users;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_fb")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id; 
	
	@Column(name="user_password", nullable = false)
	private String password;
	
	@Column(name="user_email", nullable = false, unique=true)
	private String email; 
	
	@Column(name="user_name", nullable = false)
	private String name; 
	
	@Column(name="user_surname", nullable = false)
	private String surname;
	
	@Column(name="user_address")
	private String address; 
	
	@Column(name="user_active")
	private boolean active;
	
	@Column(name="user_role")
	@Enumerated(EnumType.STRING)
	private UserRoles role;
	
	@Column(name="password_set")
	private boolean password_set;
	
	public User() {
		
	}

	public User(User user){
		this.id = user.id;
		this.password = user.password;
		this.email = user.email;
		this.name = user.name;
		this.surname = user.surname;
		this.address = user.address;
		this.role = user.role;
	}
	
	public User(String mail, String pass, String name, String surname, String address, UserRoles role) {
		this.email = mail;
		this.password = pass;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UserRoles getRole() {
		return role;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}

	public boolean isPassword_set() {
		return password_set;
	}

	public void setPassword_set(boolean password_set) {
		this.password_set = password_set;
	}
	
}
