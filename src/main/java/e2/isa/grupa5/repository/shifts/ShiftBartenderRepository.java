package e2.isa.grupa5.repository.shifts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.shifts.ShiftBartender;

/**
 * {@link ShiftBartender} JPA repository.
 * 
 * @author Viktor
 *
 */
public interface ShiftBartenderRepository extends JpaRepository<ShiftBartender, Long>{
	
	public ShiftBartender findById(long id);
	public List<ShiftBartender> findByBartender_Id(long id);
}
