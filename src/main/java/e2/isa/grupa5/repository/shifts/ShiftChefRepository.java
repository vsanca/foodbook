package e2.isa.grupa5.repository.shifts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.shifts.ShiftChef;

/**
 * {@link ShiftChef} JPA repository. 
 * 
 * @author Viktor
 *
 */
public interface ShiftChefRepository extends JpaRepository<ShiftChef, Long>{
	
	public ShiftChef findById(long id);
	public List<ShiftChef> findByChef_Id(long id);
}
