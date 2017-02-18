package e2.isa.grupa5.model.bidding;


/**
 * {@link BiddingItem} for use in communication (DTO).
 * 
 * @author Viktor
 *
 */
public class BiddingItemDTO {
	
	private String name;
	private double qty;
	private double price;
	private long groceryItemQtyId;
	private long groceryItemId;
	private long biddingId;
	
	public BiddingItemDTO() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getGroceryItemQtyId() {
		return groceryItemQtyId;
	}

	public void setGroceryItemQtyId(long groceryItemQtyId) {
		this.groceryItemQtyId = groceryItemQtyId;
	}

	public long getGroceryItemId() {
		return groceryItemId;
	}

	public void setGroceryItemId(long groceryItemId) {
		this.groceryItemId = groceryItemId;
	}

	public long getBiddingId() {
		return biddingId;
	}

	public void setBiddingId(long biddingId) {
		this.biddingId = biddingId;
	}
	
}
