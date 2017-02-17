package e2.isa.grupa5.model.bidding;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Bidding item is one item on a BiddingList.
 *
 * Created by Viktor on 12/21/2016.
 */

@Entity
@Table(name = "bidding_item")
public class BiddingItem implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Version
    private int version;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "deliveryTime", nullable = false)
    private Date deliveryTime;

    @Column(name = "guarantee")
    private String guarantee;

    public BiddingItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }
}
