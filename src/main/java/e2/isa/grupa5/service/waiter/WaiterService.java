package e2.isa.grupa5.service.waiter;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.model.users.Waiter;
import e2.isa.grupa5.model.users.WaiterDTO;
import e2.isa.grupa5.repository.waiter.WaiterRepository;
import e2.isa.grupa5.service.UserService;

/**
 * Funkcionalnost 2.4:
 * stavka: ažurira svoje lične podatke i da promeni lozinku
 * 
 * @author Boris
 *
 */
@Service
public class WaiterService {
	
	@Autowired
	WaiterRepository waiterRepository;
	
	@Autowired
	UserService userService;
	
	public Waiter updateData(WaiterDTO wDTO) {
		Waiter w = waiterRepository.findById(wDTO.getId());
		
		userService.setVariableAttributes(w, wDTO);
		
		try {
			w = waiterRepository.save(w);
			return w;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
