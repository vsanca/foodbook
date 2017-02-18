package e2.isa.grupa5.model.groceries;


/**
 * {@link GroceryItemType} for use in communication (DTO).
 * 
 * @author Viktor
 *
 */
public class GroceryItemTypeDTO {
	public String name;
	
    public GroceryItemTypeDTO() {
    	
    }
    
    public GroceryItemTypeDTO(String name) {
        this.name = name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
