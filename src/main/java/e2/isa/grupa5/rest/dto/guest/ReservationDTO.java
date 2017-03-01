package e2.isa.grupa5.rest.dto.guest;

import java.util.Date;

public class ReservationDTO {

	private long id; 
	private Date terminOd; 
	private Date terminDo; 
	private String restaurantName; 
	
	public ReservationDTO() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getTerminOd() {
		return terminOd;
	}

	public void setTerminOd(Date terminOd) {
		this.terminOd = terminOd;
	}

	public Date getTerminDo() {
		return terminDo;
	}

	public void setTerminDo(Date terminDo) {
		this.terminDo = terminDo;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
}
