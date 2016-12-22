package e2.isa.grupa5.controller;

import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.User;
import e2.isa.grupa5.model.users.UserRoles;
import e2.isa.grupa5.service.jpa.GuestService;
import e2.isa.grupa5.service.jpa.MailService;
import e2.isa.grupa5.service.jpa.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Viktor on 12/21/2016.
 */


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GuestService guestService;

    @Autowired
    private MailService mailManager;


    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> login(@RequestBody User tempUser){
        User user = userService.findByEmail(tempUser.getEmail());

        if(user.getRole() == UserRoles.GUEST){
            if(user.isActive())
                return new ResponseEntity<User>(user, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else
            return new ResponseEntity<User>(user, HttpStatus.OK);

    }

    @RequestMapping(value = "/registerGuest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setRole(UserRoles.GUEST);

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

    @RequestMapping(value = "/confirmRegistration", method = RequestMethod.POST, consumes = "application/json")
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
}
