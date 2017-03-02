package e2.isa.grupa5.model.restaurant;

import java.io.Serializable;

/**
 * {@link MenuItem} for use in communication (DTO).
 * 
 * Funkcionalnost 2.3:
 * - jelovnik, karta pića, u ovom slučaju konkretna stavka menija sa cenom. Dodatni opis stavke nalazi se u klasi {@link Item} kako bi meni bio generički, a ne vezan za tip.
 * 
 * @author Viktor
 *
 */
public class MenuItemDTO implements Serializable{
	
	private double price;
	private String itemName;
	private long itemTypeId;
	private String itemDescription;
	private long menuId;
	private String image_link;
	
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

	public String getImage_link() {
		return image_link;
	}

	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}
	
}
