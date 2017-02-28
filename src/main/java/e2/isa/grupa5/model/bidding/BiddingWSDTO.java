package e2.isa.grupa5.model.bidding;

public class BiddingWSDTO {
	
	private long bidderId;
	private long restaurantId;
	private long biddingId;
	private boolean status;
	
	public BiddingWSDTO() {
	}
	
	public long getBidderId() {
		return bidderId;
	}
	
	public void setBidderId(long bidderId) {
		this.bidderId = bidderId;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public long getBiddingId() {
		return biddingId;
	}

	public void setBiddingId(long biddingId) {
		this.biddingId = biddingId;
	}
	
}
