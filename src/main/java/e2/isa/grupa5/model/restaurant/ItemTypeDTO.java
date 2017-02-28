package e2.isa.grupa5.model.restaurant;


/**
 * {@link ItemType} for use in communication (DTO).
 * 
 * Funkcionalnost 2.3:
 * - jelovnik, karta pića. Kako bi se ograničili na trenutni opseg sistema, tip hrane će biti "hrana", a tip pića "pice".
 * 
 * @author Viktor
 *
 */
public class ItemTypeDTO {
	
	private long id;
	private String name;
	private String display_name;
	
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

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	
}
