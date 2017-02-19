package e2.isa.grupa5.rest.guest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.reservation.InvitedToReservation;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.UserRoles;

import e2.isa.grupa5.repository.guest.FriendshipRequestRepository;
import e2.isa.grupa5.repository.guest.GuestRepository;
import e2.isa.grupa5.repository.guest.InvitedToReservationRepository;
import e2.isa.grupa5.repository.guest.ReservationRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private GuestRepository guestRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private InvitedToReservationRepository invitedToReservationRepository;
	
	@Autowired
	private FriendshipRequestRepository friendshipRequestRespository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.GET, value = "/fill-database")
	public ResponseEntity<?> getProfilePageInfo() {
/*
		List<Guest> guests = guestRepository.findAll();
		for (Guest guest : guests) {
			List<FriendshipRequest> receivedRequests = friendshipRequestRespository.findByTo(guest);
			List<FriendshipRequest> sentRequests = friendshipRequestRespository.findByFrom(guest);
			for (FriendshipRequest req : sentRequests) {
				friendshipRequestRespository.delete(req);
			}
			for (FriendshipRequest req : receivedRequests) {
				friendshipRequestRespository.delete(req);
			}
			// delete all friends from friendlist 
			List<Guest> friends = guest.getFriends();
			for(Guest friend: friends) {
				friend.setFriends(new ArrayList<Guest>());
				guestRepository.save(friend);
			}
			guest.setFriends(new ArrayList<Guest>());
			guestRepository.save(guest);
			guestRepository.delete(guest);
		}
*/
//		Guest g0 = new Guest();
//		g0.setActive(true);
//		g0.setAddress("Test ADresa 1");
//		g0.setEmail("g0@g0.com");
//		g0.setName("Name1");
//		g0.setSurname("Surname0");
//		g0.setPassword(passwordEncoder.encode("Password0"));
//
//		Guest g1 = new Guest();
//		g1.setActive(true);
//		g1.setAddress("Test ADresa 1");
//		g1.setEmail("g1@g1.com");
//		g1.setName("Name1");
//		g1.setSurname("Surname1");
//		g1.setPassword(passwordEncoder.encode("Password1"));
//
//		Guest g2 = new Guest();
//		g2.setActive(true);
//		g2.setAddress("Test ADresa 2");
//		g2.setEmail("g2@g2.com");
//		g2.setName("Name2");
//		g2.setSurname("Surname2");
//		g2.setPassword(passwordEncoder.encode("Password2"));
//
//		guestRepository.save(g1);
//		guestRepository.save(g2);
//		g1.getFriends().add(g2);
//		g2.getFriends().add(g1);

		
		
//		guestRepository.save(g1);
//		guestRepository.save(g2);
//		guestRepository.save(g0);

		
//		Guest gTest = new Guest();
//		gTest.setActive(true);
//		gTest.setAddress("Test ADresa 1");
//		gTest.setEmail("email@email.com");
//		gTest.setName("Name1");
//		gTest.setSurname("Surname0");
//		gTest.setPassword(passwordEncoder.encode("Password0"));
//		gTest.setRole(UserRoles.GUEST);
//		guestRepository.save(gTest);
//		
		Guest g = new Guest(); 
		g.setActive(true);
		g.setAddress("g adresa");
		g.setEmail("g@g.com");
		g.setName("g");
		g.setSurname("g");
		g.setPassword(passwordEncoder.encode("g"));
		g.setRole(UserRoles.GUEST);
		guestRepository.save(g); 
		
		Guest g1 = new Guest(); 
		g1.setActive(true);
		g1.setAddress("g1 adresa");
		g1.setEmail("g1@g1.com");
		g1.setName("g1");
		g1.setSurname("g1");
		g1.setPassword(passwordEncoder.encode("g1"));
		g1.setRole(UserRoles.GUEST);
		guestRepository.save(g1); 
		
		Guest g2 = new Guest(); 
		g2.setActive(true);
		g2.setAddress("g2 adresa");
		g2.setEmail("g2@g2.com");
		g2.setName("g2");
		g2.setSurname("g2");
		g2.setPassword(passwordEncoder.encode("g2"));
		g2.setRole(UserRoles.GUEST);
		guestRepository.save(g2); 
		
		g1.getFriends().add(g); 
		guestRepository.save(g1);
		
		g2.getFriends().add(g1); 
		guestRepository.save(g2); 
		
		
		g2.getFriends().add(g); 
		guestRepository.save(g2); 
		
		Restaurant re = new Restaurant(); 
		re.setName("Restoran 1");
		restaurantRepository.save(re); 
		
		Restaurant re1 = new Restaurant(); 
		re1.setName("Restoran 2");
		restaurantRepository.save(re1); 
		
		Restaurant re2 = new Restaurant(); 
		re2.setName("Restoran 3");
		restaurantRepository.save(re2); 
		
		Reservation r = new Reservation(); 
		r.setGuest(g);
		r.setTerminDo(new Date());
		r.setTerminOd(new Date());
		r.setRestaurant(re);
		reservationRepository.save(r);
		
		InvitedToReservation i = new InvitedToReservation(); 
		i.setReservation(r);
		i.setGuest(g1);
		invitedToReservationRepository.save(i); 
		
		InvitedToReservation i1 = new InvitedToReservation(); 
		i1.setReservation(r);
		i1.setGuest(g2);
		invitedToReservationRepository.save(i1); 
		
		
		Reservation r1 = new Reservation(); 
		r1.setGuest(g);
		r1.setTerminDo(new Date());
		r1.setTerminOd(new Date());
		r1.setRestaurant(re1);
		reservationRepository.save(r1);
		
		Reservation r2 = new Reservation(); 
		r2.setGuest(g2);
		r2.setTerminDo(new Date());
		r2.setTerminOd(new Date());
		r2.setRestaurant(re2);
		reservationRepository.save(r2);
		
		g.getReservations().add(r); 
		g.getReservations().add(r1); 
		
		guestRepository.save(g); 
		
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
