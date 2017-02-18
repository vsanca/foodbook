package e2.isa.grupa5.repository.groceries;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.groceries.Groceries;

/**
 * 
 * @author Viktor
 *
 */
public interface GroceriesRepository extends JpaRepository<Groceries, Long>{
	public Groceries findById(long id);
}
