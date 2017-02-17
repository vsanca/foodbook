package e2.isa.grupa5.rest.guest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.friends.FriendshipRequest;
import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.UserRoles;
import e2.isa.grupa5.repository.guest.FriendshipRequestRepository;
import e2.isa.grupa5.repository.guest.GuestRepository;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private GuestRepository guestRepository;

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

		
		Guest gTest = new Guest();
		gTest.setActive(true);
		gTest.setAddress("Test ADresa 1");
		gTest.setEmail("email@email.com");
		gTest.setName("Name1");
		gTest.setSurname("Surname0");
		gTest.setPassword(passwordEncoder.encode("Password0"));
		gTest.setRole(UserRoles.GUEST);
		guestRepository.save(gTest);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
