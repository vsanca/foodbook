package e2.isa.grupa5.rest.bartender;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.shifts.ShiftBartender;
import e2.isa.grupa5.model.shifts.ShiftBartenderDTO;
import e2.isa.grupa5.model.shifts.ShiftWaiter;
import e2.isa.grupa5.model.shifts.ShiftWaiterDTO;
import e2.isa.grupa5.model.users.Bartender;
import e2.isa.grupa5.model.users.BartenderDTO;
import e2.isa.grupa5.model.users.Waiter;
import e2.isa.grupa5.model.users.WaiterDTO;
import e2.isa.grupa5.repository.bartender.BartenderRepository;
import e2.isa.grupa5.repository.shifts.ShiftBartenderRepository;
import e2.isa.grupa5.service.bartender.BartenderService;



/**
 * 
 * @author Boris
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/user")
public class BartenderController {
	
	@Autowired
	BartenderRepository bartenderRepository;
	
	@Autowired
	BartenderService bartenderService;
	
	@Autowired
	ShiftBartenderRepository shiftBartenderRepository;
	
	
	@RequestMapping(value = "/bartender/profile/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getById(@PathVariable long id) {
		
		Bartender b = bartenderRepository.findById(id);
		
		if(b != null) {
			return new ResponseEntity<>(b, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/bartender/create", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity create(@RequestBody BartenderDTO bDTO) {
		
		Bartender b = bartenderService.create(bDTO);
		
		if(b != null) {
			return new ResponseEntity<>(b, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/bartender/forRestaurant/{rId}",  method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getBartendersForRestaurant(@PathVariable long rId) {
		Set<Bartender> bartenders = new HashSet<>();
		
        for (Bartender b : bartenderRepository.findAll())
            if (b.getRestaurant().getId() == rId)
                bartenders.add(b);
        
        return new ResponseEntity<>(bartenders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bartender/newShift", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
    public ResponseEntity newShift(@RequestBody ShiftBartenderDTO sbDTO) {
		bartenderService.createShift(sbDTO);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bartender/forRestaurantShifts/{rId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
    public ResponseEntity getAllShifts(@PathVariable long rId) {
        
		List<ShiftBartender> retval = new ArrayList<>();
		
        for (ShiftBartender sb : shiftBartenderRepository.findAll()){
            if (sb.getBartender().getRestaurant().getId() == rId)
                retval.add(sb);
        }
        
        return new ResponseEntity<>(retval, HttpStatus.OK);
    }

    @RequestMapping(value = "/bartender/getShift/{id}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity getBartenderShift(@PathVariable long id) {
        return new ResponseEntity<>(shiftBartenderRepository.findByBartender_Id(id), HttpStatus.OK);
    }
	
}
