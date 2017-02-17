package e2.isa.grupa5.model.restaurant;

import javax.persistence.*;

@Entity
@Table(name = "food_menu")
public class FoodMenu {

    @Id
    @GeneratedValue
    @Column(name = "fm_id")
    private Long id;

    @Version
    private  int version;

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
}
