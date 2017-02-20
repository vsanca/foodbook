package e2.isa.grupa5.repository.shifts;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.shifts.Shift;

/**
 * {@link Shift} JPA Repository.
 * 
 * @author Viktor
 *
 */
public interface ShiftRepository  extends JpaRepository<Shift, Long>{
	
	public Shift findById(long id);
}
