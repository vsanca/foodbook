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
import e2.isa.grupa5.model.grade.Grade;
import e2.isa.grupa5.model.reservation.GuestReservationOrder;
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
import e2.isa.grupa5.model.shifts.ShiftBartender;
import e2.isa.grupa5.model.shifts.ShiftChef;
import e2.isa.grupa5.model.shifts.ShiftWaiter;
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
import e2.isa.grupa5.repository.grade.GradeRepository;
import e2.isa.grupa5.repository.guest.FriendshipRequestRepository;
import e2.isa.grupa5.repository.guest.GuestRepository;
import e2.isa.grupa5.repository.reservation.GuestReservationOrderRepository;
import e2.isa.grupa5.repository.reservation.InvitedToReservationRepository;
import e2.isa.grupa5.repository.reservation.ReservationRepository;
import e2.isa.grupa5.repository.restaurant.ItemRepository;
import e2.isa.grupa5.repository.restaurant.ItemTypeRepository;
import e2.isa.grupa5.repository.restaurant.MenuItemRepository;
import e2.isa.grupa5.repository.restaurant.MenuRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantAreaRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantTableRepository;
import e2.isa.grupa5.repository.shifts.ShiftBartenderRepository;
import e2.isa.grupa5.repository.shifts.ShiftChefRepository;
import e2.isa.grupa5.repository.shifts.ShiftRepository;
import e2.isa.grupa5.repository.shifts.ShiftWaiterRepository;
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
	private ShiftWaiterRepository shiftWaiterRepository;
	
	@Autowired
	private ShiftBartenderRepository shiftBartenderRepository;
	
	@Autowired
	private GuestReservationOrderRepository guestReservationOrderRepository;
	
	@Autowired
	private GradeRepository gradeRepository;
	
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
		g.setAddress("Narodnog fronta 35, Novi Sad 21102");
		g.setEmail("g@g.com");
		g.setName("g");
		g.setSurname("g");
		g.setPassword(passwordEncoder.encode("g"));
		g.setRole(UserRoles.GUEST);
		guestRepository.save(g); 
		
		Guest g1 = new Guest(); 
		g1.setActive(true);
		g1.setAddress("Narodnog fronta 2, Novi Sad 21102");
		g1.setEmail("g1@g1.com");
		g1.setName("g1");
		g1.setSurname("g1");
		g1.setPassword(passwordEncoder.encode("g1"));
		g1.setRole(UserRoles.GUEST);
		guestRepository.save(g1); 
		
		Guest g2 = new Guest(); 
		g2.setActive(true);
		g2.setAddress("Narodnog fronta 58, Novi Sad 21102");
		g2.setEmail("g2@g2.com");
		g2.setName("g2");
		g2.setSurname("g2");
		g2.setPassword(passwordEncoder.encode("g2"));
		g2.setRole(UserRoles.GUEST);
		guestRepository.save(g2); 
		
		Guest g3 = new Guest(); 
		g3.setActive(true);
		g3.setAddress("Narodnog fronta 32, Novi Sad 21102");
		g3.setEmail("g3@g3.com");
		g3.setName("g3");
		g3.setSurname("g3");
		g3.setPassword(passwordEncoder.encode("g3"));
		g3.setRole(UserRoles.GUEST);
		guestRepository.save(g3); 
		
		Guest g4 = new Guest(); 
		g4.setActive(true);
		g4.setAddress("Narodnog fronta 2, Novi Sad 21102");
		g4.setEmail("g4@g4.com");
		g4.setName("g4");
		g4.setSurname("g4");
		g4.setPassword(passwordEncoder.encode("g4"));
		g4.setRole(UserRoles.GUEST);
		guestRepository.save(g4); 
		
		Guest g5 = new Guest(); 
		g5.setActive(true);
		g5.setAddress("Narodnog fronta 23, Novi Sad 21102");
		g5.setEmail("g5@g5.com");
		g5.setName("g5");
		g5.setSurname("g5");
		g5.setPassword(passwordEncoder.encode("g5"));
		g5.setRole(UserRoles.GUEST);
		guestRepository.save(g5); 
		
		Guest g6 = new Guest(); 
		g6.setActive(true);
		g6.setAddress("Narodnog fronta 24, Novi Sad 21102");
		g6.setEmail("g6@g6.com");
		g6.setName("g6");
		g6.setSurname("g6");
		g6.setPassword(passwordEncoder.encode("g6"));
		g6.setRole(UserRoles.GUEST);
		guestRepository.save(g6); 
		
		Guest g7 = new Guest(); 
		g7.setActive(true);
		g7.setAddress("Narodnog fronta 31, Novi Sad 21102");
		g7.setEmail("g7@g7.com");
		g7.setName("g7");
		g7.setSurname("g7");
		g7.setPassword(passwordEncoder.encode("g7"));
		g7.setRole(UserRoles.GUEST);
		guestRepository.save(g7); 
		
		Guest g8 = new Guest(); 
		g8.setActive(true);
		g8.setAddress("Narodnog fronta 23, Novi Sad 21102");
		g8.setEmail("g8@g8.com");
		g8.setName("g8");
		g8.setSurname("g8");
		g8.setPassword(passwordEncoder.encode("g8"));
		g8.setRole(UserRoles.GUEST);
		guestRepository.save(g8); 
		
		Guest g9 = new Guest(); 
		g9.setActive(true);
		g9.setAddress("Narodnog fronta 33, Novi Sad 21102");
		g9.setEmail("g9@g9.com");
		g9.setName("g9");
		g9.setSurname("g9");
		g9.setPassword(passwordEncoder.encode("g9"));
		g9.setRole(UserRoles.GUEST);
		guestRepository.save(g9); 
		
		Guest g10 = new Guest(); 
		g10.setActive(true);
		g10.setAddress("Narodnog fronta 33, Novi Sad 21102");
		g10.setEmail("g10@g10.com");
		g10.setName("g10");
		g10.setSurname("g10");
		g10.setPassword(passwordEncoder.encode("g10"));
		g10.setRole(UserRoles.GUEST);
		guestRepository.save(g10); 
		
		g.getFriends().add(g1); 
		g.getFriends().add(g2); 
		g.getFriends().add(g3); 
		g.getFriends().add(g4);
		guestRepository.save(g); 
		
		g1.getFriends().add(g); 
		g1.getFriends().add(g4); 
		g1.getFriends().add(g5); 
		g1.getFriends().add(g6); 
		guestRepository.save(g1);
		
		g2.getFriends().add(g1); 
		g2.getFriends().add(g); 
		g2.getFriends().add(g7); 
		g2.getFriends().add(g8); 
		g2.getFriends().add(g9); 
		g2.getFriends().add(g10); 
		guestRepository.save(g2); 
		
		
		g3.getFriends().add(g2); 
		guestRepository.save(g3); 
		
		Restaurant r = new Restaurant(); 
		r.setAddress("Trg Dositeja Obradovića, Novi Sad 106314");
		r.setDescription("Restoran 0");
		r.setEmail("r@r.com");
		r.setName("r");
		r.setPhone("r");
		restaurantRepository.save(r); 
		
		Restaurant r1 = new Restaurant(); 
		r1.setAddress("Trg Dositeja Obradovića, Novi Sad 106314");
		r1.setDescription("Restoran 1");
		r1.setEmail("r1@r1.com");
		r1.setName("r1");
		r1.setPhone("r1");
		restaurantRepository.save(r1); 
		
		Restaurant r2 = new Restaurant(); 
		r2.setAddress("Trg Dositeja Obradovića, Novi Sad 106314");
		r2.setDescription("Restoran 2");
		r2.setEmail("r2@r2.com");
		r2.setName("r2");
		r2.setPhone("r2");
		restaurantRepository.save(r2); 
		
		Restaurant r3 = new Restaurant(); 
		r3.setAddress("Trg Dositeja Obradovića, Novi Sad 106314");
		r3.setDescription("Restoran 3");
		r3.setEmail("r3@r3.com");
		r3.setName("r3");
		r3.setPhone("r3");
		restaurantRepository.save(r3); 
		
		Restaurant r4 = new Restaurant(); 
		r4.setAddress("Trg Dositeja Obradovića, Novi Sad 106314");
		r4.setDescription("Restoran 4");
		r4.setEmail("r4@r4.com");
		r4.setName("r4");
		r4.setPhone("r4");
		restaurantRepository.save(r4); 
		
		Restaurant r5 = new Restaurant(); 
		r5.setAddress("Trg Dositeja Obradovića, Novi Sad 106314");
		r5.setDescription("Restoran 5");
		r5.setEmail("r5@r5.com");
		r5.setName("r5");
		r5.setPhone("r5");
		restaurantRepository.save(r5); 
		
		Reservation res = new Reservation(); 
		res.setGuest(g);
		res.setRestaurant(r);
		Date od = new Date();
		Date doDate = new Date(od.getTime() + 3600*1000);
		res.setTerminOd(od);
		res.setTerminDo(doDate);
		
		reservationRepository.save(res); 
		
		Reservation res1 = new Reservation(); 
		res1.setGuest(g);
		res1.setRestaurant(r1);
		res.setTerminOd(od);
		res.setTerminDo(doDate);
		reservationRepository.save(res1); 
		
		Reservation res2 = new Reservation(); 
		res2.setGuest(g);
		res2.setRestaurant(r);
		res.setTerminOd(od);
		res.setTerminDo(doDate);
		reservationRepository.save(res2); 
		
		
		Reservation res3 = new Reservation(); 
		res3.setGuest(g);
		res3.setRestaurant(r1);
		res3.setTerminDo(null);
		res3.setTerminOd(null);
		reservationRepository.save(res3); 
		
		
		
		Reservation res4 = new Reservation(); 
		res4.setGuest(g);
		res4.setRestaurant(r2);
		res4.setTerminDo(null);
		res4.setTerminOd(null);
		reservationRepository.save(res4); 
		
		
		g.getReservations().add(res); 
		guestRepository.save(g); 
		
		g.getReservations().add(res1); 
		g.getReservations().add(res2); 
		g.getReservations().add(res3); 
		g.getReservations().add(res4); 
		guestRepository.save(g); 
		
		
		
		
		
		
		
		
		
		
		
		
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
		
		RestaurantArea ra2 = new RestaurantArea("pusacka zona", re1);
		restaurantAreaRepository.save(ra2);
		
		RestaurantArea ra3 = new RestaurantArea("treca zona", re1);
		restaurantAreaRepository.save(ra3);
		
		
		// RESTAURANT TABLES:
		RestaurantTable rt1 = new RestaurantTable("sto 1", 2, ra1, "{\"type\":\"rect\",\"left\":10,\"top\":10,\"width\":50,\"height\":100,\"fill\":\"blue\"}");
		restaurantTableRepository.save(rt1);
		
		RestaurantTable rt2 = new RestaurantTable("sto 2", 2, ra2, "{\"type\":\"rect\",\"left\":30,\"top\":30,\"width\":40,\"height\":80,\"fill\":\"yellow\"}");
		restaurantTableRepository.save(rt2);
		
		RestaurantTable rt3 = new RestaurantTable("sto 3", 2, ra3, "{\"type\":\"rect\",\"left\":50,\"top\":50,\"width\":20,\"height\":60,\"fill\":\"green\"}");
		restaurantTableRepository.save(rt3);
		
		// RESTAURANT MANAGERS:
		RestaurantManager rm1 = new RestaurantManager("rm1@rm1.com", passwordEncoder.encode("rm1"), "Zika", "Menadzer", "Zikina Adresa", re1);
		rm1.setActive(true);
		restaurantManagerRepository.save(rm1);
		
		// RESTAURANT MENU:
		Menu mnu1 = new Menu(re1);
		menuRepository.save(mnu1);
		
		// RESTAURANT ITEM TYPES;
		ItemType food = new ItemType(ItemTypeConstants.FOOD);
		food.setDisplay_name("Hrana");
		itemTypeRepository.save(food);
		ItemType drink = new ItemType(ItemTypeConstants.DRINK);
		drink.setDisplay_name("Piće");
		itemTypeRepository.save(drink);
		
		// RESTAURANT ITEMS:
		Item it1 = new Item("Burger", "Bas dobar mesni burger", food);
		it1.setImage_link("https://g.foolcdn.com/editorial/images/126529/rrb-image_large.jpg");
		itemRepository.save(it1);
		Item it2 = new Item("Pica", "Vrlo mesnata pica", food);
		it2.setImage_link("http://sammyspizzatacoma.com/Portals/4/Meat-Lover-Pizza.jpg");
		itemRepository.save(it2);
		Item it3 = new Item("Pomfrit", "U masti pravljen krompir", food);
		it3.setImage_link("https://i.ytimg.com/vi/ETTyVQrUZt8/maxresdefault.jpg");
		itemRepository.save(it3);
		Item it4 = new Item("Sok", "Ima tragova mesa", drink);
		it4.setImage_link("http://healthyrise.com/wp-content/uploads/2016/09/Juice-8.jpg");
		itemRepository.save(it4);
		Item it5 = new Item("Pivo", "Najbolje uz meso", drink);
		it5.setImage_link("http://dreamicus.com/data/beer/beer-01.jpg");
		itemRepository.save(it5);
		Item it6 = new Item("Vino", "Ide dobro uz sve", drink);
		it6.setImage_link("http://media.salon.com/2013/12/wine_clash.jpg");
		itemRepository.save(it6);
		
		// RESTAURANT MENU ITEMS:
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
		
		ShiftBartender sBt1 = new ShiftBartender(bt1, sh1);
		shiftBartenderRepository.save(sBt1);
		
		ShiftWaiter sw1 = new ShiftWaiter(wt1, sh1);
		sw1.getAreas().add(ra1);
		sw1.getAreas().add(ra3);
		shiftWaiterRepository.save(sw1);
		
		
		
		Reservation rez1 = new Reservation(); 
		res4.setGuest(g);
		res4.setRestaurant(re1);
		res4.setTerminDo(new Date());
		res4.setTerminOd(new Date());
		reservationRepository.save(rez1); 
		
		
		
		InvitedToReservation i = new InvitedToReservation(); 
		i.setReservation(rez1);
		i.setGuest(g1);
		invitedToReservationRepository.save(i); 
		
		InvitedToReservation i1 = new InvitedToReservation(); 
		i1.setReservation(rez1);
		i1.setGuest(g2);
		invitedToReservationRepository.save(i1); 
		/////////////////////////////
		///////////////////////////
		Grade gradeG1 = new Grade();
		int grade = 5;
		gradeG1.setAtmosphereGrade(grade);
		gradeG1.setEnvironmentGrade(grade);
		gradeG1.setRated(true);
		gradeG1.setWaiterGrade(grade);
		gradeG1.setMealGrade(grade);
		gradeG1.setGuest(g);
		gradeG1.setReservation(rez1);
		gradeG1.setRestaurantGrade(5);
		gradeRepository.save(gradeG1);

		
		guestRepository.save(g); 
		
		
		
		Reservation rez2 = new Reservation(); 
		res4.setGuest(g);
		res4.setRestaurant(re1);
		res4.setTerminDo(new Date());
		res4.setTerminOd(new Date());
		reservationRepository.save(rez2); 
		
		/////////////////////////////
		///////////////////////////
		gradeG1 = new Grade();
		 grade = 3;
		gradeG1.setAtmosphereGrade(grade);
		gradeG1.setEnvironmentGrade(grade);
		gradeG1.setRated(true);
		gradeG1.setWaiterGrade(grade);
		gradeG1.setMealGrade(grade);
		gradeG1.setGuest(g1);
		gradeG1.setReservation(rez2);
		gradeG1.setRestaurantGrade(5);
		gradeRepository.save(gradeG1);
		
		GuestReservationOrder order1 = new GuestReservationOrder();
		order1.setReservation(rez1);
		order1.setItem(mi1);
		order1.setGuest(g);
		order1.setWaiter(wt1);
		order1.setChef(ch1);
		order1.setCreated(false);
		order1.setAccepted(false);
		guestReservationOrderRepository.save(order1);
		
		GuestReservationOrder order2 = new GuestReservationOrder();
		order2.setReservation(rez1);
		order2.setItem(mi2);
		order2.setGuest(g);
		order2.setWaiter(wt1);
		order2.setChef(ch1);
		order2.setCreated(false);
		order2.setAccepted(false);
		guestReservationOrderRepository.save(order2);
		
		GuestReservationOrder order3 = new GuestReservationOrder();
		order3.setReservation(rez1);
		order3.setItem(mi3);
		order3.setGuest(g);
		order3.setWaiter(wt1);
		order3.setChef(ch1);
		order3.setCreated(false);
		order3.setAccepted(true);
		guestReservationOrderRepository.save(order3);
		
		GuestReservationOrder order4 = new GuestReservationOrder();
		order3.setReservation(rez1);
		order3.setItem(mi4);
		order3.setGuest(g);
		order3.setWaiter(wt1);
		order3.setChef(ch1);
		order3.setCreated(false);
		order3.setAccepted(true);
		guestReservationOrderRepository.save(order4);
		
		GuestReservationOrder oder5 = new GuestReservationOrder();
		oder5.setReservation(rez1);
		oder5.setItem(mi5);
		oder5.setGuest(g);
		oder5.setWaiter(wt1);
		oder5.setChef(ch1);
		oder5.setCreated(false);
		oder5.setAccepted(true);
		guestReservationOrderRepository.save(oder5);
		
		GuestReservationOrder oder6 = new GuestReservationOrder();
		oder6.setReservation(rez1);
		oder6.setItem(mi6);
		oder6.setGuest(g);
		oder6.setWaiter(wt1);
		oder6.setChef(ch1);
		oder6.setCreated(false);
		oder6.setAccepted(true);
		guestReservationOrderRepository.save(oder6);
		
	
		
	
		System.out.println("DATABASE FILLED");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
