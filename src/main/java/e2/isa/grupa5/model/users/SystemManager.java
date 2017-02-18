package e2.isa.grupa5.model.users;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * SystemManager is a type of {@link User} who manages the entire system. (ADMIN)
 * 
 * @author Boris
 *
 */

@Entity
@Table(name = "sys_manager")
public class SystemManager extends User implements Serializable {
    
	public SystemManager() {
		
    }
}
