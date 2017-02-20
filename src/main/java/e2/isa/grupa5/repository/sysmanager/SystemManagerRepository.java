package e2.isa.grupa5.repository.sysmanager;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.users.SystemManager;

/**
 * {@link SystemManager} JPA repository.
 * 
 * @author Boris
 * @author Viktor
 *
 */
public interface SystemManagerRepository extends JpaRepository<SystemManager, Long>{
	
	public SystemManager findById(long id);
	public SystemManager findByEmail(String email);
	public SystemManager findByEmailAndPassword(String email, String password);
	
}
