package e2.isa.grupa5.service.chef;



import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.shifts.Shift;
import e2.isa.grupa5.model.shifts.ShiftChef;
import e2.isa.grupa5.model.shifts.ShiftChefDTO;
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.model.users.ChefDTO;
import e2.isa.grupa5.repository.chef.ChefRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.repository.shifts.ShiftChefRepository;
import e2.isa.grupa5.repository.shifts.ShiftRepository;
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
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	ShiftRepository shiftRepository;
	
	@Autowired
	ShiftChefRepository shiftChefRepository;
	
	
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
	
	public void createShift(ShiftChefDTO scDTO) {
		
		Chef c = chefRepository.getOne(scDTO.getWorkerId());
		Restaurant r = restaurantRepository.findById(scDTO.getRestaurantId());
		
		Set<ShiftChef> cShifts = new HashSet<>();
		Set<Shift> shifts = new HashSet<>();
		
		if(scDTO.getStartDate().before(scDTO.getEndDate())) {
			
			Date startDate = scDTO.getStartDate();
			
			Shift shift;
			ShiftChef cShift;
			
			while(startDate.before(scDTO.getEndDate())) {
				
				shift = new Shift(startDate, scDTO.getStartTime(), scDTO.getEndTime(), true, r);
				shifts.add(shift);
				
				cShift = new ShiftChef(c, shift);
				cShifts.add(cShift);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				cal.add(Calendar.DATE, 1);
				
				startDate = cal.getTime();
			}
			
			shiftRepository.save(shifts);
			shiftChefRepository.save(cShifts);
		}
	}
}
