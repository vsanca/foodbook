package e2.isa.grupa5.model.bidding;

import javax.persistence.*;

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
    @Column(name = "id")
    private Long id;

    public Bidding() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
