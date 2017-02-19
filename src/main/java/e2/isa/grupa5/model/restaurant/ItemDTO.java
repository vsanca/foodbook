package e2.isa.grupa5.model.restaurant;


/**
 * {@link Item} for use in communication (DTO).
 * 
 * Funkcionalnost 2.3:
 * - jelovnik, karta pića, dodatni opis stavke nalazi se u ovoj klasi kako bi meni bio generički, a ne vezan za tip. Dodatno se definiše vrsta stavke {@link ItemType}.
 * 
 * @author Viktor
 *
 */
public class ItemDTO {
	
	private String name;
	private String description;
	private long itemTypeId;
	
	public ItemDTO() {
		
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

	public long getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	
}
