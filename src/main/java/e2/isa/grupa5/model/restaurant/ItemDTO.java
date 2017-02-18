package e2.isa.grupa5.model.restaurant;


/**
 * {@link Item} for use in communication (DTO).
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
