package e2.isa.grupa5.model.restaurant;

import java.io.Serializable;
import java.util.List;

import e2.isa.grupa5.model.grade.GradeDTO;

/**
 * {@link Restaurant} for use in communication (DTO).
 *
 * Funkcionalnost 2.3:
 * - Menadžer restorana može da uređuje profil restorana.
 * 
 * @author Viktor
 *
 */
public class RestaurantDTO implements Serializable{
	
	private long id;
	private String name;
	private String description;
	private String phone;
	private String address;
	private String email;
	private int gradeAtmosphere;
	private int gradeWaiter;
	private int gradeMeal;
	private List<GradeDTO> grades;
	
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

	public int getGradeAtmosphere() {
		return gradeAtmosphere;
	}

	public void setGradeAtmosphere(int gradeAtmosphere) {
		this.gradeAtmosphere = gradeAtmosphere;
	}

	public int getGradeWaiter() {
		return gradeWaiter;
	}

	public void setGradeWaiter(int gradeWaiter) {
		this.gradeWaiter = gradeWaiter;
	}

	public int getGradeMeal() {
		return gradeMeal;
	}

	public void setGradeMeal(int gradeMeal) {
		this.gradeMeal = gradeMeal;
	}

	public List<GradeDTO> getGrades() {
		return grades;
	}

	public void setGrades(List<GradeDTO> grades) {
		this.grades = grades;
	}
	
}
