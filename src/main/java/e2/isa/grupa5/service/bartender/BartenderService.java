package e2.isa.grupa5.service.bartender;







import static org.mockito.Matchers.endsWith;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.shifts.Shift;
import e2.isa.grupa5.model.shifts.ShiftBartender;
import e2.isa.grupa5.model.shifts.ShiftBartenderDTO;
import e2.isa.grupa5.model.users.Bartender;
import e2.isa.grupa5.model.users.BartenderDTO;
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.model.users.ChefDTO;
import e2.isa.grupa5.model.users.WaiterDTO;
import e2.isa.grupa5.repository.bartender.BartenderRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.repository.shifts.ShiftBartenderRepository;
import e2.isa.grupa5.repository.shifts.ShiftRepository;
import e2.isa.grupa5.service.UserService;

/**
 * Funkcionalnost 2.4:
 * stavka: ažurira svoje lične podatke i da promeni lozinku
 * 
 * Funkcionalnost 2.3:
 * - kreira smene
 * 
 * @author Boris
 * @author Viktor
 *
 */
@Service
public class BartenderService {
	
	@Autowired
	BartenderRepository bartenderRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	ShiftBartenderRepository shiftBartenderRepository;
	
	@Autowired
	ShiftRepository shiftRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	/**
	 * Pomocna metoda
	 * 
	 * @param bt
	 * @param btDTO
	 */
	private void helpUpdateData(Bartender bt, BartenderDTO btDTO){
		
		bt.setPassword(passwordEncoder.encode(btDTO.getPassword()));
		bt.setEmail(btDTO.getEmail());
		bt.setName(btDTO.getName());
		bt.setSurname(btDTO.getSurname());
		bt.setAddress(btDTO.getAddress());
		bt.setDressSize(btDTO.getDressSize());
		bt.setShoeSize(btDTO.getShoeSize());
	}
	
	/**
	 * Pomocna metoda
	 * 
	 * @param bt
	 * @param btDTO
	 */
	private void helpUpdateDataChangedPw(Bartender bt, BartenderDTO btDTO){
		System.out.println("-------------------------------------------");
		System.out.println(btDTO.getTestNewPw() + " enkriptovana: " + passwordEncoder.encode(btDTO.getTestNewPw()));
		System.out.println("-------------------------------------------");
		bt.setPassword(passwordEncoder.encode(btDTO.getTestNewPw()));
		bt.setEmail(btDTO.getEmail());
		bt.setName(btDTO.getName());
		bt.setSurname(btDTO.getSurname());
		bt.setAddress(btDTO.getAddress());
		bt.setDressSize(btDTO.getDressSize());
		bt.setShoeSize(btDTO.getShoeSize());
	}
	
	public boolean isNewPwSameAsOld(Bartender bt, BartenderDTO btDTO){
		
		if(bt.getPassword().equals(passwordEncoder.encode(btDTO.getTestOldPw()))){
			System.out.println("-------------------------------------------");
			System.out.println("Stara sifra je potvrdjena !");
			System.out.println("-------------------------------------------");
			return true;
		}
		return false;
	}
	
	
	
	
	
	public Bartender updateData(BartenderDTO bDTO) {
		Bartender b = bartenderRepository.findById(bDTO.getId());
		
		helpUpdateData(b, bDTO);
		
		try {
			b = bartenderRepository.save(b);
			return b;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Bartender updateChangedPw(BartenderDTO bDTO) {
		Bartender b = bartenderRepository.findById(bDTO.getId());
		System.out.println("-------------------------------------------");
		System.out.println("Bartender prosao");
		System.out.println("-------------------------------------------");
		
		
		helpUpdateDataChangedPw(b, bDTO);
		System.out.println("-------------------------------------------");
		System.out.println("HelpUpdateDataChangedPw PROSLAAA");
		System.out.println("-------------------------------------------");
		
		try {
			b = bartenderRepository.save(b);
			return b;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Bartender create(BartenderDTO bDTO) {
		
		Bartender b = new Bartender();
		userService.copyData(b, bDTO);
		b.setShoeSize(bDTO.getShoeSize());
		b.setDressSize(bDTO.getDressSize());
		b.setRestaurant(restaurantRepository.findById(bDTO.getRestaurantId()));
		b.setBirthDate(bDTO.getBirthDate());
		b.setPassword_set(false);
		
		try {
			b = bartenderRepository.save(b);
			return b;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void createShift(ShiftBartenderDTO sbDTO) {
		
		Bartender b = bartenderRepository.getOne(sbDTO.getWorkerId());
		Restaurant r = restaurantRepository.findById(sbDTO.getRestaurantId());
		
		Set<ShiftBartender> bShifts = new HashSet<>();
		Set<Shift> shifts = new HashSet<>();
		
		if(sbDTO.getStartDate().before(sbDTO.getEndDate())) {
			
			Date startDate = sbDTO.getStartDate();
			
			Shift shift;
			ShiftBartender bShift;
			
			while(startDate.before(sbDTO.getEndDate())) {
				
				shift = new Shift(startDate, sbDTO.getStartTime(), sbDTO.getEndTime(), true, r);
				shifts.add(shift);
				
				bShift = new ShiftBartender(b, shift);
				bShifts.add(bShift);
				
				Calendar c = Calendar.getInstance();
				c.setTime(startDate);
				c.add(Calendar.DATE, 1);
				
				startDate = c.getTime();
			}
			
			shiftRepository.save(shifts);
			shiftBartenderRepository.save(bShifts);
		}
		
	}
}