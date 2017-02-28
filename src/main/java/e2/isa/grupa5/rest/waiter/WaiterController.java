	package e2.isa.grupa5.rest.waiter;

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

import e2.isa.grupa5.model.restaurant.RestaurantTable;
import e2.isa.grupa5.model.shifts.ShiftWaiter;
import e2.isa.grupa5.model.shifts.ShiftWaiterDTO;
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.model.users.ChefDTO;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.model.users.RestaurantManagerDTO;
import e2.isa.grupa5.model.users.Waiter;
import e2.isa.grupa5.model.users.WaiterDTO;
import e2.isa.grupa5.repository.restaurant.RestaurantTableRepository;
import e2.isa.grupa5.repository.shifts.ShiftWaiterRepository;
import e2.isa.grupa5.repository.waiter.WaiterRepository;
import e2.isa.grupa5.service.waiter.WaiterService;

/**
 * 
 * @author Boris
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/user")
public class WaiterController {
	
	@Autowired
	WaiterRepository waiterRepository;
	
	@Autowired
	WaiterService waiterService;
	
	@Autowired
	ShiftWaiterRepository shiftWaiterRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder; 
	
	@Autowired
	RestaurantTableRepository restaurantTableRepository;
	
	@RequestMapping(value = "/waiter/create", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity create(@RequestBody WaiterDTO wDTO) {
		
		Waiter w = waiterService.create(wDTO);
		
		if(w != null) {
			return new ResponseEntity<>(w, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/waiter/update", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity update(@RequestBody WaiterDTO wtDTO) {
		
		Waiter wt = waiterService.updateData(wtDTO);
		
		if(wt != null) {
			return new ResponseEntity<>(wt, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/waiter/changePassword/", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity changePassword(@RequestBody WaiterDTO wtDTO) {
		
		Waiter wt = waiterRepository.findById(wtDTO.getId());
				
		if(wt != null) {
			
			try {
				if(passwordEncoder.matches(wtDTO.getOldPassword(), wt.getPassword()) && wtDTO.getNewPassword()!=null && wtDTO.getNewPassword()!="") {
					wt.setPassword(passwordEncoder.encode(wtDTO.getNewPassword()));
					wt.setPassword_set(true);
					
					wt = waiterRepository.save(wt);
					
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
	
	
	@RequestMapping(value = "/waiter/profile/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getById(@PathVariable long id) {
		
		Waiter w = waiterRepository.findById(id);
		
		if(w != null) {
			return new ResponseEntity<>(w, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/waiter/allMyShifts/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getAllMyShifts(@PathVariable long id) {
		
		
		List<ShiftWaiter> retval = shiftWaiterRepository.findByWaiter_Id(id);
		
		if(retval != null) {
			return new ResponseEntity<>(retval, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/waiter/forRestaurant/{rId}",  method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getWaitersForRestaurant(@PathVariable long rId) {
		Set<Waiter> waiters = new HashSet<>();
		
        for (Waiter w : waiterRepository.findAll())
            if (w.getRestaurant().getId() == rId)
                waiters.add(w);
        
        return new ResponseEntity<>(waiters, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/waiter/newShift", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
    public ResponseEntity newShift(@RequestBody ShiftWaiterDTO swDTO) {
		waiterService.createShift(swDTO);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/waiter/forRestaurantShifts/{rId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
    public ResponseEntity getAllShifts(@PathVariable long rId) {
        
		List<ShiftWaiter> retval = new ArrayList<>();
		
        for (ShiftWaiter sw : shiftWaiterRepository.findAll()){
            if (sw.getWaiter().getRestaurant().getId() == rId)
                retval.add(sw);
        }
        
        return new ResponseEntity<>(retval, HttpStatus.OK);
    }

    @RequestMapping(value = "/waiter/getShift/{id}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity getBartenderShift(@PathVariable long id) {
        return new ResponseEntity<>(shiftWaiterRepository.findByWaiter_Id(id), HttpStatus.OK);
    }
}
