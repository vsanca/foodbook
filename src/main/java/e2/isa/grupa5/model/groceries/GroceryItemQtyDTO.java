package e2.isa.grupa5.model.groceries;


/**
 * {@link GroceryItemQty} for use in communication (DTO).
 * 
 * @author Viktor
 *
 */
public class GroceryItemQtyDTO {
	private String name;
	
	public GroceryItemQtyDTO() {
		
	}
	
	public GroceryItemQtyDTO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
