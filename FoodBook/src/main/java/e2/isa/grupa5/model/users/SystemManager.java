package e2.isa.grupa5.model.users;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Viktor on 12/21/2016.
 */

@Entity
@Table(name = "sys_manager")
public class SystemManager extends User implements Serializable {
    public SystemManager() {
    }
}
