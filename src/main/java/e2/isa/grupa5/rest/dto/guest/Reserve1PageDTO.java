package e2.isa.grupa5.rest.dto.guest;

import java.util.Date;

public class Reserve1PageDTO {
	private String restaurantName;
	private Date date;
	private int duration;
	
	public Reserve1PageDTO() {
		
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
