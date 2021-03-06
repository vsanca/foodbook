package e2.isa.grupa5.model.restaurant;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Single {@link Item} on a menu (items of different types can be provided).
 * 
 * Funkcionalnost 2.3:
 * - jelovnik, karta pića, u ovom slučaju konkretna stavka menija sa cenom. Dodatni opis stavke nalazi se u klasi {@link Item} kako bi meni bio generički, a ne vezan za tip.
 * 
 * @author Viktor
 *
 */
@Entity
@Table(name = "menu_item")
public class MenuItem {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "price", nullable = false)
	private double price;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;
	
	public MenuItem() {
		
	}
	
	public MenuItem(double price, Item item, Menu menu) {
		this.price = price;
		this.item = item;
		this.menu = menu;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
