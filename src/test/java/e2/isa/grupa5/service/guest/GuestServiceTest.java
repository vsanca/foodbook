package e2.isa.grupa5.service.guest;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import e2.isa.grupa5.model.friends.FriendshipRequest;
import e2.isa.grupa5.model.reservation.InvitedToReservation;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.UserRoles;
import e2.isa.grupa5.repository.grade.GradeRepository;
import e2.isa.grupa5.repository.guest.FriendshipRequestRepository;
import e2.isa.grupa5.repository.guest.GuestRepository;
import e2.isa.grupa5.repository.reservation.InvitedToReservationRepository;
import e2.isa.grupa5.repository.reservation.ReservationRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.rest.dto.guest.AcceptPeopleDTO;
import e2.isa.grupa5.rest.dto.guest.ReservationDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestServiceTest {
	
	@Autowired
	GuestService guestService;
	
	@Autowired 
	GuestRepository guestRepository;
	
	@Autowired
	FriendshipRequestRepository friendshipRequestRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired ReservationRepository reservationRepository;
	
	@Autowired
	InvitedToReservationRepository invitedToReservationRepository;
	
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	private Guest forGuest;
	
    @Autowired
    private AuthenticationManager authenticationManager;

	
	@After
	public void tearDown() throws Exception {
		invitedToReservationRepository.deleteAll();
		reservationRepository.deleteAll();
		restaurantRepository.deleteAll();
		
		friendshipRequestRepository.deleteAll();
		guestRepository.deleteAll();
	}
	
	@Test
	public void testCreateRequest() {
		
		Guest g = new Guest(); 
		g.setActive(true);
		g.setAddress("Narodnog fronta 35, Novi Sad 21102");
	
		g.setEmail("g@g.com" +  Math.round(Math.random()%99999));
		g.setName("g");
		g.setSurname("g");
		g.setPassword("Passw0rd");
	
		g.setRole(UserRoles.GUEST);
		guestRepository.save(g); 
		
		Guest g1 = new Guest(); 
		g1.setActive(true);
		g1.setAddress("Narodnog fronta 2, Novi Sad 21102");
		g1.setEmail("g1@g1.com" +  Math.round(Math.random()%99999));
		g1.setName("g1");
		g1.setSurname("g1");
		g1.setRole(UserRoles.GUEST);
		g1.setPassword("Passw0rd");
		g1 = guestRepository.save(g1); 
		
		FriendshipRequest request = new FriendshipRequest();
		request.setFrom(g);
		request.setTo(g1);
		forGuest = g1;
		
		friendshipRequestRepository.save(request);
		
		List<AcceptPeopleDTO> requests = guestService.acceptPeople(forGuest.getId());
		assertEquals(requests.size(), 1);
		
		
	}
	
	@Test 
	public void testRejectRequest() {
		Guest g = new Guest(); 
		g.setActive(true);
		g.setAddress("Narodnog fronta 35, Novi Sad 21102");
	
		g.setEmail("g@g.com" +  Math.round(Math.random()%99999));
		g.setName("g");
		g.setSurname("g");
		g.setPassword("Passw0rd");
	
		g.setRole(UserRoles.GUEST);
		guestRepository.save(g); 
		
		Guest g1 = new Guest(); 
		g1.setActive(true);
		g1.setAddress("Narodnog fronta 2, Novi Sad 21102");
		g1.setEmail("g1@g1.com" +  Math.round(Math.random()%99999));
		g1.setName("g1");
		g1.setSurname("g1");
		g1.setRole(UserRoles.GUEST);
		g1.setPassword("Passw0rd");
		g1 = guestRepository.save(g1); 
		
		FriendshipRequest request = new FriendshipRequest();
		request.setFrom(g);
		request.setTo(g1);
		forGuest = g1;
		
		friendshipRequestRepository.save(request);
		
		guestService.rejectFriendshipRequest(g.getId(), g1.getId());
		List<AcceptPeopleDTO> requests = guestService.acceptPeople(forGuest.getId());
		assertEquals(requests.size(), 0);
		
	}
	
	@Test
	public void testAcceptRequest() {
		Guest g = new Guest(); 
		g.setActive(true);
		g.setAddress("Narodnog fronta 35, Novi Sad 21102");
	
		g.setEmail("g@g.com" +  Math.round(Math.random()%99999));
		g.setName("g");
		g.setSurname("g");
		g.setPassword("Passw0rd");
	
		g.setRole(UserRoles.GUEST);
		guestRepository.save(g); 
		
		Guest g1 = new Guest(); 
		g1.setActive(true);
		g1.setAddress("Narodnog fronta 2, Novi Sad 21102");
		g1.setEmail("g1@g1.com" +  Math.round(Math.random()%99999));
		g1.setName("g1");
		g1.setSurname("g1");
		g1.setRole(UserRoles.GUEST);
		g1.setPassword("Passw0rd");
		g1 = guestRepository.save(g1); 
		
		FriendshipRequest request = new FriendshipRequest();
		request.setFrom(g);
		request.setTo(g1);
		forGuest = g1;
		
		friendshipRequestRepository.save(request);
		
		guestService.acceptFriendshipRequest(g.getId(), g1.getId());
		List<AcceptPeopleDTO> requests = guestService.acceptPeople(forGuest.getId());
		assertEquals(requests.size(), 0);
	}
	
	@Test
	public void testCancelReservation() {
		Guest g = new Guest(); 
		g.setActive(true);
		g.setAddress("Narodnog fronta 35, Novi Sad 21102");
	
		g.setEmail("g@g.com" +  Math.round(Math.random()%99999));
		g.setName("g");
		g.setSurname("g");
		g.setPassword("Passw0rd");
	
		g.setRole(UserRoles.GUEST);
		guestRepository.save(g); 
		
		Guest g1 = new Guest(); 
		g1.setActive(true);
		g1.setAddress("Narodnog fronta 2, Novi Sad 21102");
		g1.setEmail("g1@g1.com" +  Math.round(Math.random()%99999));
		g1.setName("g1");
		g1.setSurname("g1");
		g1.setRole(UserRoles.GUEST);
		g1.setPassword("Passw0rd");
		g1 = guestRepository.save(g1); 
		
		FriendshipRequest request = new FriendshipRequest();
		request.setFrom(g);
		request.setTo(g1);
		forGuest = g1;
		
		friendshipRequestRepository.save(request);
		
		Restaurant r = new Restaurant(); 
		r.setAddress("Trg Dositeja Obradovića, Novi Sad 106314");
		r.setDescription("Restoran 0");
		r.setEmail("r@r.com");
		r.setName("r");
		r.setPhone("r");
		restaurantRepository.save(r);
		
		Reservation rez1 = new Reservation(); 
		rez1.setGuest(forGuest);
		rez1.setRestaurant(r);
		rez1.setTerminDo(new Date());
		rez1.setTerminOd(new Date());
		rez1 = reservationRepository.save(rez1); 
		
		
		
		InvitedToReservation i = new InvitedToReservation(); 
		i.setReservation(rez1);
		i.setGuest(g);
		invitedToReservationRepository.save(i); 
		
		guestService.cancelReservation(forGuest.getId(), rez1.getId());
		List<Reservation> reservations = reservationRepository.findByGuest(forGuest);
		assertEquals(reservations.size(), 0);
	}
	
	@Test
	public void testMyReservations() {
		Guest g = new Guest(); 
		g.setActive(true);
		g.setAddress("Narodnog fronta 35, Novi Sad 21102");
	
		g.setEmail("g@g.com" +  Math.round(Math.random()%99999));
		g.setName("g");
		g.setSurname("g");
		g.setPassword("Passw0rd");
	
		g.setRole(UserRoles.GUEST);
		guestRepository.save(g); 
		
		Guest g1 = new Guest(); 
		g1.setActive(true);
		g1.setAddress("Narodnog fronta 2, Novi Sad 21102");
		g1.setEmail("g1@g1.com" +  Math.round(Math.random()%99999));
		g1.setName("g1");
		g1.setSurname("g1");
		g1.setRole(UserRoles.GUEST);
		g1.setPassword("Passw0rd");
		g1 = guestRepository.save(g1); 
		
		FriendshipRequest request = new FriendshipRequest();
		request.setFrom(g);
		request.setTo(g1);
		forGuest = g1;
		
		friendshipRequestRepository.save(request);
		
		Restaurant r = new Restaurant(); 
		r.setAddress("Trg Dositeja Obradovića, Novi Sad 106314");
		r.setDescription("Restoran 0");
		r.setEmail("r@r.com");
		r.setName("r");
		r.setPhone("r");
		restaurantRepository.save(r);
		
		Reservation rez1 = new Reservation(); 
		rez1.setGuest(forGuest);
		rez1.setRestaurant(r);
		rez1.setTerminDo(new Date());
		rez1.setTerminOd(new Date());
		rez1 = reservationRepository.save(rez1); 
		
		
		
		InvitedToReservation i = new InvitedToReservation(); 
		i.setReservation(rez1);
		i.setGuest(g);
		invitedToReservationRepository.save(i); 
		
		// invited to reservation should
		List<ReservationDTO> dto = guestService.getGuestReservations(g.getId());
		
		assertEquals(dto.size(), 1);
	}
	
	@Test
	public void testPasswordEncoder() {
		Guest g = new Guest(); 
		g.setActive(true);
		g.setAddress("Narodnog fronta 35, Novi Sad 21102");
		

	
		g.setEmail("g@g.com" +  Math.round(Math.random()%99999));
		g.setName("g");
		g.setSurname("g");
		g.setPassword("Passw0rd");
		g.setPassword(passwordEncoder.encode(g.getPassword()));
	
		g.setRole(UserRoles.GUEST);
		guestRepository.save(g); 
		
	  	
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                       g.getEmail(),
                       "Passw0rd"
                )
        );

	}
	
	
	
	

}
