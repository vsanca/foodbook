package e2.isa.grupa5.rest.guest;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import e2.isa.grupa5.model.friends.FriendshipRequest;
import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.UserRoles;
import e2.isa.grupa5.repository.guest.FriendshipRequestRepository;
import e2.isa.grupa5.repository.guest.GuestRepository;
import e2.isa.grupa5.rest.dto.guest.AcceptPeopleDTO;
import e2.isa.grupa5.rest.dto.guest.AddPeopleDTO;

/**
 * 
 * @author Korisnik
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GuestControllerTest {

		@Autowired
		private TestRestTemplate restTemplate;
		
		@Autowired
		FriendshipRequestRepository friendshipRequestRepository;
		
		@Autowired
		private GuestRepository guestRepository;

		private Guest forGuest;
		
		@Before
		public void setUp() throws Exception {

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
		}
		
		@After
		public void tearDown() throws Exception {
			friendshipRequestRepository.deleteAll();
			guestRepository.deleteAll();
		}
		
		@Test
		public void getFriendshipRequestsGuest1() {
			
			String url = "/rest/guest/add-people/" + forGuest.getId();
			ResponseEntity<AddPeopleDTO[]> response = restTemplate.getForEntity(url, AddPeopleDTO[].class);
			AddPeopleDTO[] forNow = response.getBody();
		  
		   
			
			assertTrue(forNow.length==1);
			
		}
		
		@Test
		public void getFriendshipRequestsGuest2() {
			
			String url = "/rest/guest/add-people/2";
			ResponseEntity<AddPeopleDTO[]> response = restTemplate.getForEntity(url, AddPeopleDTO[].class);
			AddPeopleDTO[] forNow = response.getBody();
		  
		   
			
			assertTrue(forNow.length==0);
			
		}
		
	
}
