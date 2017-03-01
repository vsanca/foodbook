package e2.isa.grupa5.rest.chef;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.reservation.GuestReservationOrder;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.restaurant.MenuItem;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.shifts.ShiftChef;
import e2.isa.grupa5.model.shifts.ShiftChefDTO;
import e2.isa.grupa5.model.shifts.ShiftWaiter;
import e2.isa.grupa5.model.shifts.ShiftWaiterDTO;
import e2.isa.grupa5.model.users.Bartender;
import e2.isa.grupa5.model.users.BartenderDTO;
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.model.users.ChefDTO;
import e2.isa.grupa5.model.users.Waiter;
import e2.isa.grupa5.model.users.WaiterDTO;
import e2.isa.grupa5.repository.chef.ChefRepository;
import e2.isa.grupa5.repository.reservation.GuestReservationOrderRepository;
import e2.isa.grupa5.repository.reservation.ReservationRepository;
import e2.isa.grupa5.repository.restaurant.MenuItemRepository;
import e2.isa.grupa5.repository.shifts.ShiftChefRepository;
import e2.isa.grupa5.service.chef.ChefService;
import e2.isa.grupa5.service.reservation.GuestReservationOrderService;

/**
 * 
 * @author Boris
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/user")
public class ChefController {
	
	@Autowired
	ChefRepository chefRepository;
	
	@Autowired
	ChefService chefService;
	
	@Autowired
	ShiftChefRepository shiftChefRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	GuestReservationOrderRepository guestReservationOrderRepository;
	
	@Autowired
	GuestReservationOrderService guestReservationOrderService;
	
	@Autowired
	MenuItemRepository menuItemRepository;
	
	
	
	@RequestMapping(value = "/chef/profile/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getById(@PathVariable long id) {
		
		Chef ch = chefRepository.findById(id);
		
		if(ch != null) {
			return new ResponseEntity<>(ch, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@RequestMapping(value = "/chef/allMyShifts/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getAllMyChShifts(@PathVariable long id) {
		
		
		List<ShiftChef> retval = shiftChefRepository.findByChef_Id(id);
		
		if(retval != null) {
			return new ResponseEntity<>(retval, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/chef/create", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity create(@RequestBody ChefDTO cDTO) {
		
		Chef c = chefService.create(cDTO);
		
		if(c != null) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/chef/update", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity update(@RequestBody ChefDTO chDTO) {
		
		Chef ch = chefService.updateData(chDTO);
		
		if(ch != null) {
			return new ResponseEntity<>(ch, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/chef/changePassword/", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity changePassword(@RequestBody ChefDTO chDTO) {
		
		Chef ch = chefRepository.findById(chDTO.getId());
				
		if(ch != null) {
			
			try {
				if(passwordEncoder.matches(chDTO.getOldPassword(), ch.getPassword()) && chDTO.getNewPassword()!=null && chDTO.getNewPassword()!="") {
					ch.setPassword(passwordEncoder.encode(chDTO.getNewPassword()));
					ch.setPassword_set(true);
					
					ch = chefRepository.save(ch);
					
					return new ResponseEntity<>(HttpStatus.OK);
					
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(value="/chef/forRestaurant/{rId}",  method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getChefsForRestaurant(@PathVariable long rId) {
		Set<Chef> chefs = new HashSet<>();
		
        for (Chef c : chefRepository.findAll())
            if (c.getRestaurant().getId() == rId)
                chefs.add(c);
        
        return new ResponseEntity<>(chefs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chef/newShift", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
    public ResponseEntity newShift(@RequestBody ShiftChefDTO scDTO) {
		chefService.createShift(scDTO);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chef/forRestaurantShifts/{rId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
    public ResponseEntity getAllShifts(@PathVariable long rId) {
        
		List<ShiftChef> retval = new ArrayList<>();
		
        for (ShiftChef sc : shiftChefRepository.findAll()){
            if (sc.getChef().getRestaurant().getId() == rId)
                retval.add(sc);
        }
        
        return new ResponseEntity<>(retval, HttpStatus.OK);
    }

    @RequestMapping(value = "/chef/getShift/{id}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity getChefShift(@PathVariable long id) {
        return new ResponseEntity<>(shiftChefRepository.findByChef_Id(id), HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/chef/allUnfinishedOders/{cId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
    public ResponseEntity getAllUnfinishedOders(@PathVariable long cId) {
        
    	Chef ch = chefRepository.findById(cId);
		
       List<GuestReservationOrder> myOrders = guestReservationOrderService.forChefReturnAllUnfinishedOrders(ch);
        
       if(myOrders == null){
    	   System.out.println("Propo123");
    	   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	   
       }
       else{
    	   System.out.println("DOBARRR 123");
    	   return new ResponseEntity<>(myOrders, HttpStatus.OK);
       }
        
        
    }
    
    //test
    @RequestMapping(value = "/chef/allUnfinishedOdersTEST/{cId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
    public ResponseEntity getAllUnfinishedOdersTEST(@PathVariable long cId) {
        
    	System.out.println("USAOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		
       List<GuestReservationOrder> myOrders = guestReservationOrderRepository.findAll();
       if(myOrders == null){
    	   System.out.println("NULLLLLLLLLLLLLLLLLl");
       }
       for(GuestReservationOrder g : myOrders){
    	   System.out.println("-------------------------------------");
    	   System.out.println("ID: " +g.getId());
    	   System.out.println("-------------------------------------");
    	   System.out.println("-------------------------------------");
    	   System.out.println("ID: " +g.getItem().getPrice());
    	   System.out.println("-------------------------------------");
    	   System.out.println("-------------------------------------");
    	   System.out.println("ID: " +g.getItem().getItem().getName());
    	   System.out.println("-------------------------------------");
       }
       
       System.out.println("DOBARRR");
	   return new ResponseEntity<>(myOrders, HttpStatus.OK);
       
        
        
    }
	
	
}
