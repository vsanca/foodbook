package e2.isa.grupa5.model.bidding;

import java.util.Date;

/**
 * {@link Bidding} for use in communication (DTO).
 * 
 * @author Viktor
 *
 */
public class BiddingDTO {
	
	private double price;
	private Date timestamp;
	private String status;
	private String currency;
	private long bidderId;
	private long groceriesId;
	
	public BiddingDTO() {
		
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public long getBidderId() {
		return bidderId;
	}

	public void setBidderId(long bidderId) {
		this.bidderId = bidderId;
	}

	public long getGroceriesId() {
		return groceriesId;
	}

	public void setGroceriesId(long groceriesId) {
		this.groceriesId = groceriesId;
	}
	
}
