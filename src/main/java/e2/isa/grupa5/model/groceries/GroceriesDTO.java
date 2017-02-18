package e2.isa.grupa5.model.groceries;

import java.util.Date;

/**
 * {@link Groceries} for use in communication (DTO).
 * 
 * @author Viktor
 *
 */
public class GroceriesDTO {
	
	private Date from_date;
	private Date to_date;
	private String status;
	private long restaurantId;
	
	public GroceriesDTO() {
		
	}
	
	public GroceriesDTO(Date from_date, Date to_date, String status, long restaurantId) {
		this.from_date = from_date;
		this.to_date = to_date;
		this.status = status;
		this.restaurantId = restaurantId;
	}

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getTo_date() {
		return to_date;
	}

	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	
}
