package e2.isa.grupa5.rest.guest;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Value;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.GeocodingResult;
import e2.isa.grupa5.rest.dto.guest.DistanceDTO;


import e2.isa.grupa5.model.reservation.InvitedToReservation;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.restaurant.Item;
import e2.isa.grupa5.model.restaurant.ItemType;
import e2.isa.grupa5.model.restaurant.ItemTypeConstants;
import e2.isa.grupa5.model.restaurant.Menu;
import e2.isa.grupa5.model.restaurant.MenuItem;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantArea;
import e2.isa.grupa5.model.restaurant.RestaurantTable;
import e2.isa.grupa5.model.shifts.Shift;
import e2.isa.grupa5.model.shifts.ShiftChef;
import e2.isa.grupa5.model.users.Bartender;
import e2.isa.grupa5.model.users.Bidder;
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.model.users.SystemManager;
import e2.isa.grupa5.model.users.UserRoles;
import e2.isa.grupa5.model.users.Waiter;
import e2.isa.grupa5.repository.bartender.BartenderRepository;
import e2.isa.grupa5.repository.bidding.BidderRepository;
import e2.isa.grupa5.repository.chef.ChefRepository;
import e2.isa.grupa5.repository.guest.FriendshipRequestRepository;
import e2.isa.grupa5.repository.guest.GuestRepository;
import e2.isa.grupa5.repository.guest.InvitedToReservationRepository;
import e2.isa.grupa5.repository.guest.ReservationRepository;
import e2.isa.grupa5.repository.restaurant.ItemRepository;
import e2.isa.grupa5.repository.restaurant.ItemTypeRepository;
import e2.isa.grupa5.repository.restaurant.MenuItemRepository;
import e2.isa.grupa5.repository.restaurant.MenuRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantAreaRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantTableRepository;
import e2.isa.grupa5.repository.shifts.ShiftChefRepository;
import e2.isa.grupa5.repository.shifts.ShiftRepository;
import e2.isa.grupa5.repository.sysmanager.SystemManagerRepository;
import e2.isa.grupa5.repository.waiter.WaiterRepository;
import e2.isa.grupa5.service.chef.ChefService;

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
	private SystemManagerRepository systemManagerRepository;
	
	@Autowired
	private RestaurantManagerRepository restaurantManagerRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Autowired
	private ItemTypeRepository itemTypeRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private BartenderRepository bartenderRepository;
	
	@Autowired
	private ChefRepository chefRepository;
	
	@Autowired
	private WaiterRepository waiterRepository;
	
	@Autowired
	private RestaurantAreaRepository restaurantAreaRepository;
	
	@Autowired
	private RestaurantTableRepository restaurantTableRepository;
	
	@Autowired
	private BidderRepository bidderRepository;
	
	@Autowired
	private ShiftRepository shiftRepository;
	
	@Autowired
	private ShiftChefRepository shiftChefRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
		@Value("${jelena.google.key}")
	private String jelenaApiKey;
	
	@RequestMapping(method = RequestMethod.GET, value = "/test-geo-api") 
	public DistanceDTO getAddress() {
		DistanceDTO dto = new DistanceDTO();
		
		GeoApiContext context = new GeoApiContext().setApiKey(jelenaApiKey);
		GeocodingResult[] origins = null;
		GeocodingResult[] destinations = null;
		try {
//			origins = GeocodingApi.geocode(context,
//			    "Narodnog fronta 23, Novi Sad 21102").await();
//			destinations = GeocodingApi.geocode(context, "Trg Dositeja Obradovića, Novi Sad 106314").await();
//			System.out.println(origins[0].formattedAddress);
//			System.out.println(destinations[0].formattedAddress);
			String[] guestAddress = new String[] {  "Narodnog fronta 33, Novi Sad 21102" };
			String[] restaurantAddress = new String[] {"Trg Dositeja Obradovića, Novi Sad 106314"};
			DistanceMatrix udaljenost = DistanceMatrixApi.getDistanceMatrix(context,guestAddress, restaurantAddress).await();
			for(DistanceMatrixRow row : udaljenost.rows) {
				for(int i = 0; i < row.elements.length; i++) {
					System.out.println("DISTANCEE:" + row.elements[i].distance);
					dto.setDistanceKm(row.elements[i].distance.inMeters/1000.0);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return dto;
		
		
	}


	@RequestMapping(method = RequestMethod.GET, value = "/fill-database")
	public ResponseEntity<?> getProfilePageInfo() {
		
		
		//restaurantRepository.deleteAll();
		
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
		
		
		// SYSTEM MANAGERS:
		SystemManager sm1 = new SystemManager();
		sm1.setActive(true);
		sm1.setEmail("sm1@sm1.com");
		sm1.setPassword(passwordEncoder.encode("sm1"));
		sm1.setName("Admin");
		sm1.setSurname("Admin");
		sm1.setAddress("Secret admin address");
		systemManagerRepository.save(sm1);
		
		// RESTAURANTS:
		// Napravio sam parametrizovani konstruktor kako se ne bi propustilo obavezno polje (biće database error sa integritetima).
		Restaurant re1 = new Restaurant("Puno mesa","Stvarno dobar restoran sa mesom", "555-12345", "Bulevar Oslobodjenja 6 Novi Sad", "punomesa@restorani.com");
		restaurantRepository.save(re1); 
		
		Restaurant re2 = new Restaurant("Malo mesa","Ne bas sjajan restoran sa mesom", "555-12346", "Somborski Bulevar 56 Novi Sad", "malomesa@restorani.com"); 
		restaurantRepository.save(re2); 
		
		Restaurant re3 = new Restaurant("Bilje i zacini","Ako bas ne volite meso", "555-12489", "Jevrejska 20 Novi Sad", "biljeizacini@restorani.com"); 
		restaurantRepository.save(re3); 
		
		// RESTAURANT AREAS:
		RestaurantArea ra1 = new RestaurantArea("nepusacka zona", re1);
		restaurantAreaRepository.save(ra1);
		
		// RESTAURANT TABLES:
		RestaurantTable rt1 = new RestaurantTable("sto 1", 2, ra1);
		restaurantTableRepository.save(rt1);
		
		// RESTAURANT MANAGERS:
		RestaurantManager rm1 = new RestaurantManager("rm1@rm1.com", passwordEncoder.encode("rm1"), "Zika", "Menadzer", "Zikina Adresa", re1);
		rm1.setActive(true);
		restaurantManagerRepository.save(rm1);
		
		// RESTAURANT MENU:
		Menu mnu1 = new Menu(re1);
		menuRepository.save(mnu1);
		
		// RESTAURANT ITEM TYPES;
		ItemType food = new ItemType(ItemTypeConstants.FOOD);
		itemTypeRepository.save(food);
		ItemType drink = new ItemType(ItemTypeConstants.DRINK);
		itemTypeRepository.save(drink);
		
		// RESTAURANT ITEMS:
		Item it1 = new Item("Burger", "Bas dobar mesni burger", food);
		itemRepository.save(it1);
		Item it2 = new Item("Pica", "Vrlo mesnata pica", food);
		itemRepository.save(it2);
		Item it3 = new Item("Pomfrit", "U masti pravljen krompir", food);
		itemRepository.save(it3);
		Item it4 = new Item("Sok", "Ima tragova mesa", drink);
		itemRepository.save(it4);
		Item it5 = new Item("Pivo", "Najbolje uz meso", drink);
		itemRepository.save(it5);
		Item it6 = new Item("Vino", "Ide dobro uz sve", drink);
		itemRepository.save(it6);
		
		// RESTAURAN MENU ITEMS:
		MenuItem mi1 = new MenuItem(500, it1, mnu1);
		menuItemRepository.save(mi1);
		MenuItem mi2 = new MenuItem(800, it2, mnu1);
		menuItemRepository.save(mi2);
		MenuItem mi3 = new MenuItem(300, it3, mnu1);
		menuItemRepository.save(mi3);
		MenuItem mi4 = new MenuItem(150, it4, mnu1);
		menuItemRepository.save(mi4);
		MenuItem mi5 = new MenuItem(300, it5, mnu1);
		menuItemRepository.save(mi5);
		MenuItem mi6 = new MenuItem(300, it6, mnu1);
		menuItemRepository.save(mi6);
		
		// BIDDERS:
		Bidder bd1 = new Bidder("bd1@bd1.com", passwordEncoder.encode("bd1"), "Marko", "Markova adresa", "Ponudic");
		bd1.setActive(true);
		bidderRepository.save(bd1);
		
		// RESTAURANT WORKERS:
		Bartender bt1 = new Bartender("bt1@bt1.com", passwordEncoder.encode("bt1"), "Milan", "Pivic", "Milanova adresa", re1, new Date(), 15, 42);
		bt1.setActive(true);
		bt1.setPassword_set(true);
		bartenderRepository.save(bt1);
		
		Bartender bt2 = new Bartender("bt2@bt2.com", passwordEncoder.encode("bt2"), "Jovan", "Jovic", "Jovina adresa", re1, new Date(), 15, 42);
		bt2.setActive(true);
		bt2.setPassword_set(false);
		bartenderRepository.save(bt2);
		
		Chef ch1 = new Chef("ch1@ch1.com", passwordEncoder.encode("ch1"), "Slavko", "Mesic", "Slavkova adresa", re1, new Date(), 15, 42);
		ch1.setActive(true);
		ch1.setPassword_set(true);
		chefRepository.save(ch1);
		
		Chef ch2 = new Chef("ch2@ch2.com", passwordEncoder.encode("ch2"), "Strudla","Mesic", "Strudlina adresa", re1, new Date(), 15, 42);
		ch1.setActive(true);
		ch1.setPassword_set(false);
		chefRepository.save(ch2);
		
		Waiter wt1 = new Waiter("wt1@wt1.com", passwordEncoder.encode("wt1"), "Rob", "Sluskic", "Adresa roba", re1, new Date(), 15, 42);
		wt1.setActive(true);
		wt1.setPassword_set(true);
		waiterRepository.save(wt1);
		
		Waiter wt2 = new Waiter("wt2@wt2.com", passwordEncoder.encode("wt2"), "Slusko", "Sluzic", "Sluskova adresa", re1, new Date(), 15, 42);
		wt1.setActive(true);
		wt1.setPassword_set(false);
		waiterRepository.save(wt1);
		
		Waiter wa1 = new Waiter("wa1@wa1.com", passwordEncoder.encode("wa1"), "Pera", "Usluzic", "Perina adresa", re1, new Date(), 15, 42);
		wa1.setActive(true);
		waiterRepository.save(wa1);
		
		// RESTAURANT WORKER SHIFTS:
		Shift sh1 = new Shift(new Date(), "08:00", "20:00", true, re1);
		shiftRepository.save(sh1);
		
		ShiftChef sc1 = new ShiftChef(ch1, sh1);
		shiftChefRepository.save(sc1);
		
		
		Reservation r = new Reservation(); 
		r.setGuest(g);
		r.setTerminDo(new Date());
		r.setTerminOd(new Date());
		r.setRestaurant(re1);
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
