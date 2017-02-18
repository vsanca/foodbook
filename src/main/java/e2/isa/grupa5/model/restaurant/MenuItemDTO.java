package e2.isa.grupa5.model.restaurant;


/**
 * {@link MenuItem} for use in communication (DTO).
 * 
 * @author Viktor
 *
 */
public class MenuItemDTO {
	
	private double price;
	private String itemName;
	private long itemTypeId;
	private String itemDescription;
	private long menuId;
	
	public MenuItemDTO() {
		
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public long getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	
}
