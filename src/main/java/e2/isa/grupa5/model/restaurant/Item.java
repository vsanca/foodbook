package e2.isa.grupa5.model.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Single item type describing {@link MenuItem} in {@link Menu}.
 * 
 * Funkcionalnost 2.3:
 * - jelovnik, karta pića, dodatni opis stavke nalazi se u ovoj klasi kako bi meni bio generički, a ne vezan za tip. Dodatno se definiše vrsta stavke {@link ItemType}.
 * 
 * @author Viktor
 *
 */
@Entity
@Table(name = "item")
public class Item {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_type_id", nullable = false)
    private ItemType itemType;
	
	public Item() {
		
	}
	
	public Item(String name, String description, ItemType type) {
		this.name = name;
		this.description = description;
		this.itemType = type;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
}
