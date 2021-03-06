package e2.isa.grupa5.rest.bartender;

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
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.shifts.ShiftBartender;
import e2.isa.grupa5.model.shifts.ShiftBartenderDTO;
import e2.isa.grupa5.model.users.Bartender;
import e2.isa.grupa5.model.users.BartenderDTO;
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.repository.bartender.BartenderRepository;
import e2.isa.grupa5.repository.reservation.GuestReservationOrderRepository;
import e2.isa.grupa5.repository.shifts.ShiftBartenderRepository;
import e2.isa.grupa5.rest.dto.guest.GuestReservationOrderDTO;
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
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	GuestReservationOrderRepository guestReservationOrderRepository;
	
	
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
	
	@RequestMapping(value = "/bartender/allMyShifts/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getAllMyChShifts(@PathVariable long id) {
		
		
		List<ShiftBartender> retval = shiftBartenderRepository.findByBartender_Id(id);
		
		if(retval != null) {
			return new ResponseEntity<>(retval, HttpStatus.OK);
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
	
	@RequestMapping(value = "/bartender/update", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity update(@RequestBody BartenderDTO btDTO) {
		
		Bartender bt = bartenderService.updateData(btDTO);
		
		if(bt != null) {
			return new ResponseEntity<>(bt, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/bartender/changePassword/", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity changePassword(@RequestBody BartenderDTO btDTO) {
		
		Bartender bt = bartenderRepository.findById(btDTO.getId());
				
		if(bt != null) {
			
			try {
				if(passwordEncoder.matches(btDTO.getOldPassword(), bt.getPassword()) && btDTO.getNewPassword()!=null && btDTO.getNewPassword()!="") {
					bt.setPassword(passwordEncoder.encode(btDTO.getNewPassword()));
					bt.setPassword_set(true);
					
					bt = bartenderRepository.save(bt);
					
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
	
    
  //test
    @RequestMapping(value = "/bartender/allUnfinishedOdersTEST/{cId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
    public ResponseEntity getAllUnfinishedOdersTEST123(@PathVariable long cId) {
        
    	
		
		List<GuestReservationOrder> allReservations = guestReservationOrderRepository.findAll();
		
		List<GuestReservationOrderDTO> allReservationsDTO = new ArrayList<GuestReservationOrderDTO>();
		for(GuestReservationOrder g : allReservations){
			GuestReservationOrderDTO gDTO = new GuestReservationOrderDTO(g);
			allReservationsDTO.add(gDTO);
		}
    	if(allReservationsDTO.isEmpty()){
    		return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    	}
    	else{
    		return new ResponseEntity<>(allReservationsDTO, HttpStatus.OK);
    	}
		
		
    }
    
   
    @RequestMapping(value = "/bartender/acceptedOrder/{cId}/{dId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
    public ResponseEntity setAcceptedOrder123(@PathVariable Long cId, @PathVariable long dId) {
       
    	
    	
       GuestReservationOrder r = guestReservationOrderRepository.findById(cId);
       Bartender ch = bartenderRepository.findById(dId);
       r.setBartender(ch);
       r.setAccepted(true);
       guestReservationOrderRepository.save(r);
       
       List<GuestReservationOrder> allReservations = guestReservationOrderRepository.findAll();
		
		List<GuestReservationOrderDTO> allReservationsDTO = new ArrayList<GuestReservationOrderDTO>();
		for(GuestReservationOrder g : allReservations){
			GuestReservationOrderDTO gDTO = new GuestReservationOrderDTO(g);
			allReservationsDTO.add(gDTO);
		}
	   	if(allReservationsDTO.isEmpty()){
	   		return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	   	}
	   	else{
	   		return new ResponseEntity<>(allReservationsDTO, HttpStatus.OK);
	   	}
      
    }
    
    
    
  
    @RequestMapping(value = "/bartender/createdOrder/{cId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
    public ResponseEntity setCreatedOrder123(@PathVariable long cId) {
       
       GuestReservationOrder r = guestReservationOrderRepository.findById(cId);
       
       r.setCreated(true);
       
       guestReservationOrderRepository.save(r);  
       
       List<GuestReservationOrder> allReservations = guestReservationOrderRepository.findAll();
		
		List<GuestReservationOrderDTO> allReservationsDTO = new ArrayList<GuestReservationOrderDTO>();
		for(GuestReservationOrder g : allReservations){
			GuestReservationOrderDTO gDTO = new GuestReservationOrderDTO(g);
			allReservationsDTO.add(gDTO);
		}
	   	if(allReservationsDTO.isEmpty()){
	   		return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	   	}
	   	else{
	   		return new ResponseEntity<>(allReservationsDTO, HttpStatus.OK);
	   	}
    
    }
	
}
