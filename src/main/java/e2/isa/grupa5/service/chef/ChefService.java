package e2.isa.grupa5.service.chef;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.model.users.ChefDTO;
import e2.isa.grupa5.repository.chef.ChefRepository;
import e2.isa.grupa5.service.UserService;

/**
 * Funkcionalnost 2.4:
 * stavka: ažurira svoje lične podatke i da promeni lozinku
 * 
 * @author Boris
 *
 */
@Service
public class ChefService {
	
	@Autowired
	ChefRepository chefRepository;
	
	@Autowired
	UserService userService;
	
	public Chef updateData(ChefDTO chDTO) {
		Chef ch = chefRepository.findById(chDTO.getId());
		
		userService.setVariableAttributes(ch, chDTO);
		
		try {
			ch = chefRepository.save(ch);
			return ch;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
