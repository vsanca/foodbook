package e2.isa.grupa5.service.waiter;





import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantArea;
import e2.isa.grupa5.model.shifts.Shift;
import e2.isa.grupa5.model.shifts.ShiftWaiter;
import e2.isa.grupa5.model.shifts.ShiftWaiterDTO;
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.model.users.ChefDTO;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.model.users.RestaurantManagerDTO;
import e2.isa.grupa5.model.users.Waiter;
import e2.isa.grupa5.model.users.WaiterDTO;
import e2.isa.grupa5.repository.restaurant.RestaurantAreaRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.repository.shifts.ShiftRepository;
import e2.isa.grupa5.repository.shifts.ShiftWaiterRepository;
import e2.isa.grupa5.repository.waiter.WaiterRepository;
import e2.isa.grupa5.service.UserService;

/**
 * Funkcionalnost 2.3:
 * - definisanje smene i segmenta restorana
 * 
 * Funkcionalnost 2.4:
 * stavka: ažurira svoje lične podatke i da promeni lozinku
 * 
 * @author Boris
 * @author Viktor
 */
@Service
public class WaiterService {
	
	@Autowired
	WaiterRepository waiterRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	RestaurantAreaRepository restaurantAreaRepository;
	
	@Autowired
	ShiftRepository shiftRepository;
	
	@Autowired
	ShiftWaiterRepository shiftWaiterRepository;
	
	/**
	 * Pomocna metoda
	 * 
	 * @param wt
	 * @param wtDTO
	 */
	private void helpUpdateData(Waiter wt, WaiterDTO wtDTO){
		
		wt.setPassword(wtDTO.getPassword());
		wt.setEmail(wtDTO.getEmail());
		wt.setName(wtDTO.getName());
		wt.setSurname(wtDTO.getSurname());
		wt.setAddress(wtDTO.getAddress());
		wt.setDressSize(wtDTO.getDressSize());
		wt.setShoeSize(wtDTO.getShoeSize());
	}
	
	public Waiter updateData(WaiterDTO wDTO) {
		Waiter w = waiterRepository.findById(wDTO.getId());
		
		helpUpdateData(w, wDTO);
		
		try {
			w = waiterRepository.save(w);
			return w;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Waiter create(WaiterDTO wDTO) {
		
		Waiter w = new Waiter();
		userService.copyData(w, wDTO);
		w.setShoeSize(wDTO.getShoeSize());
		w.setDressSize(wDTO.getDressSize());
		w.setRestaurant(restaurantRepository.findById(wDTO.getRestaurantId()));
		w.setBirthDate(wDTO.getBirthDate());
		w.setPassword_set(false);
		
		try {
			w = waiterRepository.save(w);
			return w;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void createShift(ShiftWaiterDTO swDTO) {
		
		Waiter w = waiterRepository.getOne(swDTO.getWorkerId());
		Restaurant r = restaurantRepository.findById(swDTO.getRestaurantId());
		
		Set<ShiftWaiter> wShifts = new HashSet<>();
		Set<Shift> shifts = new HashSet<>();
		Set<RestaurantArea> areas = new HashSet<>();
		
		for(int a : swDTO.getAreas()) {
			areas.add(restaurantAreaRepository.findById(a));
		}
		
		if(swDTO.getStartDate().before(swDTO.getEndDate())) {
			
			Date startDate = swDTO.getStartDate();
			Shift shift;
			ShiftWaiter wShift;
			
			while(startDate.before(swDTO.getEndDate())) {
				
				shift = new Shift(startDate, swDTO.getStartTime(), swDTO.getEndTime(), true, r);
				wShift = new ShiftWaiter(w, shift);
				wShift.setAreas(areas);
				
				shifts.add(shift);
				wShifts.add(wShift);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				cal.add(Calendar.DATE, 1);
				
				startDate = cal.getTime();
			}
			
			shiftRepository.save(shifts);
			shiftWaiterRepository.save(wShifts);
			
		}
	}
	
	
}
