package e2.isa.grupa5.rest.restaurant;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.restaurant.ReportDTO;
import e2.isa.grupa5.model.restaurant.ReportObjectDTO;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.Waiter;
import e2.isa.grupa5.repository.restaurant.MenuItemRepository;
import e2.isa.grupa5.repository.restaurant.MenuRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.repository.waiter.WaiterRepository;

/**
 * Mock data generation and retrieval for generating the report.
 * 
 * Funkcionalnost 2.3:
 * - prikaz izveštaja o poslovanju restorana
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/data")
public class MockDataService {
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired 
	MenuItemRepository menuItemRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	WaiterRepository waiterRepository;
	
	
	@RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()") 
	public ResponseEntity getRestaurantGrade(@PathVariable long id) {
		
		// Implementacija metode koja bi dobavila ove podatke
		// na osnovu ocena iz svih rezervacija
		
		List<ReportObjectDTO> list = new ArrayList<>();
		
		Random rand = new Random();
		
		for(int i=0; i<5; i++) {
			rand = new Random();
			
			int val = rand.nextInt(50)+10;
			
			ReportObjectDTO tmp = new ReportObjectDTO();
			
			tmp.setGrade(i+1);
			tmp.setValue(val);
			
			list.add(tmp);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/meal/{rId}/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()") 
	public ResponseEntity getMealData(@PathVariable long id) {
		
		List<ReportObjectDTO> list = new ArrayList<>();
		
		Random rand = new Random();
		
		for(int i=0; i<5; i++) {
			rand = new Random();
			
			int val = rand.nextInt(50)+10;
			
			ReportObjectDTO tmp = new ReportObjectDTO();
			
			tmp.setGrade(i+1);
			tmp.setValue(val);
			
			list.add(tmp);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/waiter/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()") 
	public ResponseEntity getWaiterData(@PathVariable long id) {
		
		List<ReportObjectDTO> list = new ArrayList<>();
		
		Random rand = new Random();
		
		for(int i=0; i<5; i++) {
			rand = new Random();
			
			int val = rand.nextInt(50)+10;
			
			ReportObjectDTO tmp = new ReportObjectDTO();
			
			tmp.setGrade(i+1);
			tmp.setValue(val);
			
			list.add(tmp);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/visits/{id}", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()") 
	public ResponseEntity visitsInPeriod(@PathVariable long id, @RequestBody ReportDTO rDTO) {
		
		Date start = rDTO.getStart_interval();
		Date end = rDTO.getEnd_interval();
		
		List<ReportObjectDTO> list = new ArrayList<>();
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/revenue/{id}", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()") 
	public ResponseEntity revenue(@PathVariable long id, @RequestBody ReportDTO rDTO) {
		
		Date startDate = rDTO.getStart_interval();
		Date endDate = rDTO.getEnd_interval();
		
		LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		List<ReportObjectDTO> list = new ArrayList<>();
		
		for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
			Random rand = new Random();
			
			ReportObjectDTO tmp = new ReportObjectDTO();
			
			tmp.setValue(rand.nextInt(100000)+20000);
			tmp.setName(date.toString());		
			
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/revenueByWaiter/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()") 
	public ResponseEntity revenueByWaiter(@PathVariable long id) {
		
		List<Waiter> waiters = new ArrayList<>();	
		List<ReportObjectDTO> list = new ArrayList<>();
		
		for (Waiter w : waiterRepository.findAll())
            if (w.getRestaurant().getId() == id)
                waiters.add(w);
		
		
		Random rand = new Random();
		
		for(int i=0; i<waiters.size(); i++) {
			rand = new Random();
			
			int val = rand.nextInt(50000)+1000;
			
			ReportObjectDTO tmp = new ReportObjectDTO();
			
			tmp.setName(waiters.get(i).getName()+" "+waiters.get(i).getSurname());
			tmp.setValue(val);
			
			list.add(tmp);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	

}
