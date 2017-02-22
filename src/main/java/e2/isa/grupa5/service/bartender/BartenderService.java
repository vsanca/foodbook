package e2.isa.grupa5.service.bartender;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import e2.isa.grupa5.model.users.Bartender;
import e2.isa.grupa5.model.users.WaiterDTO;
import e2.isa.grupa5.repository.bartender.BartenderRepository;
import e2.isa.grupa5.service.UserService;

/**
 * Funkcionalnost 2.4:
 * stavka: ažurira svoje lične podatke i da promeni lozinku
 * 
 * @author Boris
 *
 */
@Service
public class BartenderService {
	
	@Autowired
	BartenderRepository bartenderRepository;
	
	@Autowired
	UserService userService;
	
	public Bartender updateData(WaiterDTO bDTO) {
		Bartender b = bartenderRepository.findById(bDTO.getId());
		
		userService.setVariableAttributes(b, bDTO);
		
		try {
			b = bartenderRepository.save(b);
			return b;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}