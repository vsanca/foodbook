package e2.isa.grupa5.model.bidding;

import java.util.Date;

import javax.persistence.*;

import e2.isa.grupa5.model.groceries.Groceries;
import e2.isa.grupa5.model.users.Bidder;

/**
 * BiddingList consists of BiddingItems. It is used by Bidders for sending their offers to open
 * calls for groceries by Restaurants, specifically for calls made by Restaurant Managers.
 *
 * Created by Viktor on 12/21/2016.
 */

@Entity
@Table(name = "bidding")
public class Bidding {

    @Id
    @GeneratedValue
    private long id;
    
    @Column(name = "price")
    private double price;
    
    @Column(name = "currency")
    private String currency;
    
    @Column(name = "timestamp")
    private Date timestamp;
    
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bidder", nullable = false)
    private Bidder bidder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groceries_id", nullable = false)
    private Groceries groceries;
    
    public Bidding() {
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Bidder getBidder() {
		return bidder;
	}

	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}

	public Groceries getGroceries() {
		return groceries;
	}

	public void setGroceries(Groceries groceries) {
		this.groceries = groceries;
	}

}
