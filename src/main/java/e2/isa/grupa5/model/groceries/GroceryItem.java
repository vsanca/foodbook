package e2.isa.grupa5.model.groceries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grocery_item")
public class GroceryItem {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "quantity", nullable = false)
	private long quantity;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grocery_item_type_id", nullable = false)
    private GroceryItemType groceryItemType;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grocery_item_qty_id", nullable = false)
    private GroceryItemQty groceryItemQty;
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groceries_id", nullable = false)
    private Groceries groceries;
	
	public GroceryItem() {
		
	}
	
	public GroceryItem(String name, long qty, Groceries groceries, GroceryItemType type, GroceryItemQty qty_unit) {
		this.name = name;
		this.quantity = qty;
		this.groceries = groceries;
		this.groceryItemType = type;
		this.groceryItemQty = qty_unit;
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

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public GroceryItemType getGroceryItemType() {
		return groceryItemType;
	}

	public void setGroceryItemType(GroceryItemType groceryItemType) {
		this.groceryItemType = groceryItemType;
	}

	public GroceryItemQty getGroceryItemQty() {
		return groceryItemQty;
	}

	public void setGroceryItemQty(GroceryItemQty groceryItemQty) {
		this.groceryItemQty = groceryItemQty;
	}

	public Groceries getGroceries() {
		return groceries;
	}

	public void setGroceries(Groceries groceries) {
		this.groceries = groceries;
	}
	
}
