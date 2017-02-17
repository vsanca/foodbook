package e2.isa.grupa5.model.restaurant;


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
 * Single item on a menu (food).
 * 
 * @author Viktor
 *
 */

@Entity
@Table(name = "menu_item")
public class MenuItem {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;
	
	public MenuItem() {
		
	}
	
	public MenuItem(String name, String description, double price, Menu menu) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.menu = menu;
	}
	
}
