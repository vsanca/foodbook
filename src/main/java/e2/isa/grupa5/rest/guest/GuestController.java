package e2.isa.grupa5.rest.guest;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.grade.Grade;
import e2.isa.grupa5.model.grade.GradeDTO;
import e2.isa.grupa5.model.restaurant.MenuDTO;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantDTO;
import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.User;
import e2.isa.grupa5.repository.grade.GradeRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.rest.dto.guest.FriendsPageDTO;
import e2.isa.grupa5.rest.dto.guest.HomePageDTO;
import e2.isa.grupa5.rest.dto.guest.ProfilePageDTO;
import e2.isa.grupa5.rest.dto.guest.UpdateProfileDTO;
import e2.isa.grupa5.service.UserService;
import e2.isa.grupa5.service.grade.GradeService;
import e2.isa.grupa5.service.guest.GuestService;


/**
 * Funkcionalnost 2.8 - ocenjivanje restorana
 * 
 * @author Jelena Jankovic RA139-2013
 * @author Boris
 */
@RequestMapping("/rest/guest")
@RestController
public class GuestController {
	
    @Autowired
    private GuestService guestService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private GradeRepository gradeRepository;
    
    @Autowired
    private GradeService gradeService;
    


    @RequestMapping(value = "/register-guest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> registerUser(@RequestBody User user) {
    	Guest guest = guestService.registerGuest(user); 
    	if(guest != null) {
    		return new ResponseEntity<>(HttpStatus.CREATED); 
    	}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/confirm-registration/{user-id}", method = RequestMethod.GET)
    public ResponseEntity<?> confirmGuestRegistration( @PathVariable(value="user-id") long id){

    	boolean isValid = guestService.verifyGuest(id);
    	
        if(isValid){
    		return new ResponseEntity<>(HttpStatus.OK); 
    	}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Any authenticated user can access profile-page-info
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value= "/get-profile-page-info/{guest-id}")
    @PreAuthorize("isAuthenticated()")
	public ProfilePageDTO getProfilePageInfo(@PathVariable(value="guest-id") Long id) {
		return guestService.getProfilePageInfo(id);
	}
    
    /**
     * Any authenticated user can access profile-page-info
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value= "/get-home-page-info/{guest-id}")
    @PreAuthorize("isAuthenticated()")
	public List<HomePageDTO> getHomePageInfo(@PathVariable(value="guest-id") Long id) {
		return guestService.getHomePageInfo(id);
	}
    
    /**
     * Any authenticated user can access profile-page-info
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value= "/get-friends-page-info/{guest-id}")
    @PreAuthorize("isAuthenticated()")
	public List<FriendsPageDTO> getFriendsPageInfo(@PathVariable(value="guest-id") Long id) {
		return guestService.getFriendsPageInfo(id);
	}
    
    /**
     * Any authenticated user can access profile-page-info
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value= "/update-profile-info/{guest-id}")
    @PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> updateProfileInfo(@PathVariable(value="guest-id") Long id, @RequestBody UpdateProfileDTO dto ) {	
    	boolean isValid = guestService.updateProfileInfo(id, dto.getName(), dto.getSurname(), dto.getAddress());

        if(isValid){
    		return new ResponseEntity<>(HttpStatus.OK); 
    	}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }
    
    /**
     * Rating reservation
     * 
     * @param reservation-id
     * @return 
     * 
     * @author Boris
     */
    @RequestMapping(value = "/rate/{reservation-id}/{guest-id}", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
	public ResponseEntity create(@PathVariable(value="reservation-id") long rId,@PathVariable(value="reservation-id") long gId,@RequestBody GradeDTO gDTO) {
		
		Grade g = gradeRepository.findByReservation_idAndGuest_id(rId, gId);
		gradeService.setVariableAttributes(g, gDTO);
		
		if(g != null) {
			
			g.setRated(true);
			gradeRepository.save(g);
			
			return new ResponseEntity<>(g, HttpStatus.OK);
			
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
    
}
