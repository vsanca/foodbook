package e2.isa.grupa5.model.groceries;


/**
 * {@link GroceryItem} for use in communication (DTO).
 * 
 * @author Viktor
 *
 */
public class GroceryItemDTO {
	
	private String name;
	private long quantity;
	private long groceriesId;
	private long groceriesItemType;
	private long groceriesItemQty;
	
	public GroceryItemDTO() {
		
	}
	
	public GroceryItemDTO(String name, long quantity, long groceriesId, long groceriesItemType, long groceriesItemQty) {
		this.name = name;
		this.quantity = quantity;
		this.groceriesId = groceriesId;
		this.groceriesItemType = groceriesItemType;
		this.groceriesItemQty = groceriesItemQty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getGroceriesId() {
		return groceriesId;
	}

	public void setGroceriesId(long groceriesId) {
		this.groceriesId = groceriesId;
	}

	public long getGroceriesItemType() {
		return groceriesItemType;
	}

	public void setGroceriesItemType(long groceriesItemType) {
		this.groceriesItemType = groceriesItemType;
	}

	public long getGroceriesItemQty() {
		return groceriesItemQty;
	}

	public void setGroceriesItemQty(long groceriesItemQty) {
		this.groceriesItemQty = groceriesItemQty;
	}
	
}
