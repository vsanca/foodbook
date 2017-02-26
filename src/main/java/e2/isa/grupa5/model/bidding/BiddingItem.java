package e2.isa.grupa5.model.bidding;

import javax.persistence.*;

import e2.isa.grupa5.model.groceries.GroceryItem;
import e2.isa.grupa5.model.groceries.GroceryItemQty;


/**
 * Bidding item is one item on a BiddingList. 
 * 
 * Funkcionalnost 2.5:
 * - deo porudžbine (jedna stavka)
 *
 * Created by Viktor on 12/21/2016.
 */

@Entity
@Table(name = "bidding_item")
public class BiddingItem {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private double quantity;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bidding_id", nullable = false)
    private Bidding bidding;

    @Column(name = "price", nullable = false)
    private double price;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grocery_item_unit_id", nullable = false)
    private GroceryItemQty groceryItemQty;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grocery_item_id", nullable = false)
    private GroceryItem groceryItem;

    public BiddingItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Bidding getBidding() {
		return bidding;
	}

	public void setBidding(Bidding bidding) {
		this.bidding = bidding;
	}

	public GroceryItemQty getGroceryItemQty() {
		return groceryItemQty;
	}

	public void setGroceryItemQty(GroceryItemQty groceryItemQty) {
		this.groceryItemQty = groceryItemQty;
	}

	public GroceryItem getGroceryItem() {
		return groceryItem;
	}

	public void setGroceryItem(GroceryItem groceryItem) {
		this.groceryItem = groceryItem;
	}
    
}
