package e2.isa.grupa5.model.groceries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * There can be different types of {@link GroceryItem}(s), (drink, food...), so this is generalized as a possibility to add other types of groceries as well.
 * 
 * Funkcionalnost 2.3:
 * - Objavljivanje ponude sa spiskom namirnica i pića koji su potrebni, kao i datumom od kad do kad je prikupljanje ponuda aktivno.
 * 
 * @author Viktor
 *
 */
@Entity
@Table(name = "grocery_item_type")
public class GroceryItemType {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	public GroceryItemType() {
		
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
