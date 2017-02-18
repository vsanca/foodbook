package e2.isa.grupa5.model.restaurant;


/**
 * {@link ItemType} for use in communication (DTO).
 * 
 * @author Viktor
 *
 */
public class ItemTypeDTO {
	
	private long id;
	private String name;
	
	public ItemTypeDTO() {
		
	}

	public ItemTypeDTO(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
