package e2.isa.grupa5.model.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Possible type of {@link Item} in {@link MenuItem} that can be found in a {@link Menu}.
 * 
 * Funkcionalnost 2.3:
 * - jelovnik, karta pića. Kako bi se ograničili na trenutni opseg sistema, tip hrane će biti diktiran konstantama u {@link ItemTypeConstants}.
 * 
 * @author Viktor
 *
 */
@Entity
@Table(name = "item_type")
public class ItemType {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	public ItemType() {
		
	}
	
	public ItemType(String name) {
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
