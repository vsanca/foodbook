package e2.isa.grupa5.repository.shifts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.shifts.ShiftWaiter;

/**
 * {@link ShiftWaiter} JPA repository.
 * 
 * @author Viktor
 *
 */
public interface ShiftWaiterRepository extends JpaRepository<ShiftWaiter, Long>{
	
	public ShiftWaiter findById(long id);
	public List<ShiftWaiter> findByWaiter_Id(long id);
}
