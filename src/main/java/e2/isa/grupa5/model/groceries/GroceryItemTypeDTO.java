package e2.isa.grupa5.model.groceries;


/**
 * {@link GroceryItemType} for use in communication (DTO).
 * 
 * Funkcionalnost 2.3:
 * - Objavljivanje ponude sa spiskom namirnica i pića koji su potrebni, kao i datumom od kad do kad je prikupljanje ponuda aktivno.
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
