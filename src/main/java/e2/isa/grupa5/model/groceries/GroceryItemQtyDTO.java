package e2.isa.grupa5.model.groceries;


/**
 * {@link GroceryItemQty} for use in communication (DTO).
 * 
 * Funkcionalnost 2.3:
 * - Objavljivanje ponude sa spiskom namirnica i pića koji su potrebni, kao i datumom od kad do kad je prikupljanje ponuda aktivno.
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
