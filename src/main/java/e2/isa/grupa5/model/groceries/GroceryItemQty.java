package e2.isa.grupa5.model.groceries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * Quantity details for {@link GroceryItem}. Depending on the type of the item quantity can be expressed in different units.
 * 
 * Funkcionalnost 2.3:
 * - Objavljivanje ponude sa spiskom namirnica i pića koji su potrebni, kao i datumom od kad do kad je prikupljanje ponuda aktivno.
 * 
 * @author Viktor
 *
 */
@Entity
@Table(name = "grocery_item_quantity")
public class GroceryItemQty {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	public GroceryItemQty(){
		
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
