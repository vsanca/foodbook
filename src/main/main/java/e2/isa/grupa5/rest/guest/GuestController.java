package e2.isa.grupa5.rest.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.User;
import e2.isa.grupa5.model.users.UserRoles;
import e2.isa.grupa5.rest.dto.guest.ProfilePageDTO;
import e2.isa.grupa5.service.MailService;
import e2.isa.grupa5.service.UserService;
import e2.isa.grupa5.service.guest.GuestService;

/**
 * 
 * @author Jelena Jankovic RA139-2013
 *
 */
@RequestMapping("/rest/guest")
@RestController
public class GuestController {
	
    @Autowired
    private GuestService guestService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private MailService mailManager;
    
    /** 
     * Request BCrypt2 encoder
     * @return
     */
   @Autowired 
   private PasswordEncoder passwordEncoder;



    @RequestMapping(value = "/register-guest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setRole(UserRoles.GUEST);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Guest guest = new Guest(user);
        guest.setActive(false);

        Guest saved = guestService.save(guest);

        System.out.println((User)saved);

        if(saved!=null) {
            System.out.println("MAIL SENT TO "+guest.getEmail());
            mailManager.sendMail(guest);

            return new ResponseEntity<User>(guest, HttpStatus.CREATED);

        } else return new ResponseEntity<User>(guest, HttpStatus.IM_USED);
    }

    @RequestMapping(value = "/confirm-registration", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> confirmRegistration(@RequestBody User user){

        User registered = userService.findByEmail(user.getEmail());

        if(registered instanceof Guest){
            ((Guest) registered).setActive(true);
            Guest saved = guestService.save((Guest)registered);
            return new ResponseEntity<User>(saved, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
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

	
}
