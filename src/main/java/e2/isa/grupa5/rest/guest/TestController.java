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
import e2.isa.grupa5.model.reservation.ReservationRestaurantTable;
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
import e2.isa.grupa5.repository.reservation.ReservationRestaurantTableRepository;
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
	private ReservationRestaurantTableRepository reservationRestaurantTableRepository;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Value("${jelena.google.key}")
	private String jelenaApiKey;

	@RequestMapping(method = RequestMethod.GET, value = "/fill-database")
	public ResponseEntity<?> getProfilePageInfo() {
		
		Guest g = new Guest(); 
		g.setActive(true);
		g.setAddress("Narodnog fronta 35, Novi Sad 21102");
		g.setEmail("petar@petar.com");
		g.setName("Petar");
		g.setSurname("Petrovic");
		g.setPassword(passwordEncoder.encode("petar"));
		g.setRole(UserRoles.GUEST);
		guestRepository.save(g); 
		
		Guest g2 = new Guest(); 
		g2.setActive(true);
		g2.setAddress("Narodnog fronta 58, Novi Sad 21102");
		g2.setEmail("jelena.jankovic@uns.ac.rs");
		g2.setName("Jelena");
		g2.setSurname("Jelenkovic");
		g2.setPassword(passwordEncoder.encode("jelena"));
		g2.setRole(UserRoles.GUEST);
		guestRepository.save(g2); 
		
		Guest g3 = new Guest(); 
		g3.setActive(true);
		g3.setAddress("Narodnog fronta 58, Novi Sad 21102");
		g3.setEmail("mika@mika");
		g3.setName("Mika");
		g3.setSurname("Mikic");
		g3.setPassword(passwordEncoder.encode("mika"));
		g3.setRole(UserRoles.GUEST);
		guestRepository.save(g3);  
		
		g.getFriends().add(g3);
		g.getFriends().add(g2);
		guestRepository.save(g); 
		
		g2.getFriends().add(g); 
		guestRepository.save(g2); 
		
		Restaurant r = new Restaurant(); 
		r.setAddress("Sutjeska 2, Novi Sad 21000");
		r.setDescription("Plava frajla");
		r.setEmail("plavaFrajla@rplavaFrajla.com");
		r.setName("Plava frajla");
		r.setPhone("0214882420");
		restaurantRepository.save(r); 
		
		Restaurant r1 = new Restaurant(); 
		r1.setAddress("Vase Stajića 27, Novi Sad 21000");
		r1.setDescription("Dva štapića");
		r1.setEmail("dvaStapica@dvaStapica.com");
		r1.setName("Dva štapića");
		r1.setPhone("021459524");
		restaurantRepository.save(r1); 
		
		Restaurant r2 = new Restaurant(); 
		r2.setAddress("Trg mladenaca 4, Novi Sad 21000");
		r2.setDescription("Restoran Marina");
		r2.setEmail("restoranMarina@restoranMarina.com");
		r2.setName("Restoran Marina");
		r2.setPhone("0213015408");
		restaurantRepository.save(r2); 
		
		Restaurant r3 = new Restaurant(); 
		r3.setAddress("Zmaj Jovina 4, Novi Sad 21000");
		r3.setDescription("Kafe restoran Maša");
		r3.setEmail("masa@masa.com");
		r3.setName("Kafe restoran Maša");
		r3.setPhone("021451241");
		restaurantRepository.save(r3); 
		
		Restaurant r4 = new Restaurant(); 
		r4.setAddress("Nikole Pašića 27, Novi Sad 21000");
		r4.setDescription("Restoran Fontana");
		r4.setEmail("fontana@fontana.com");
		r4.setName("Restoran Fontana");
		r4.setPhone("0216621779");
		restaurantRepository.save(r4); 
		
		Restaurant r5 = new Restaurant(); 
		r5.setAddress("Pančevački put 163, Beograd 11000");
		r5.setDescription("Mali raj");
		r5.setEmail("maliRaj@maliRaj.com");
		r5.setName("Mali raj");
		r5.setPhone("0112748700");
		restaurantRepository.save(r5); 		
		
		
		Reservation res = new Reservation(); 
		res.setGuest(g);
		res.setRestaurant(r);
		Date od = new Date(1502352000000L);			//	08/10/2017 10:00
		Date doDate = new Date(1502359200000L);		//	08/10/2017 12:00
		res.setTerminOd(od);
		res.setTerminDo(doDate);
		
		reservationRepository.save(res); 
		
		Reservation res1 = new Reservation(); 
		res1.setGuest(g);
		res1.setRestaurant(r);
		od = new Date(1502791200000L);			//	08/15/2017 12:00
		doDate = new Date(1502794800000L);		//	08/15/2017 13:00
		res1.setTerminOd(od);
		res1.setTerminDo(doDate);
		reservationRepository.save(res1); 
		
		Reservation res2 = new Reservation(); 
		res2.setGuest(g);
		res2.setRestaurant(r2);
		od = new Date(1502816400000L);			//	08/15/2017 19:00
		doDate = new Date(1502820000000L);		//	08/15/2017 20:00
		res2.setTerminOd(od);
		res2.setTerminDo(doDate);
		reservationRepository.save(res2); 
		
		
		Reservation res3 = new Reservation(); 
		res3.setGuest(g2);
		res3.setRestaurant(r);
		od = new Date(1503228600000L);			//	08/20/2017 13:30
		doDate = new Date(1503234000000L);		//	08/20/2017 15:00
		res3.setTerminOd(od);
		res3.setTerminDo(doDate);
		reservationRepository.save(res3); 
		
		
		
		
		Reservation res4 = new Reservation(); 
		res4.setGuest(g2);
		res4.setRestaurant(r2);
		od = new Date(1503651600000L);			//	08/25/2017 11:00
		doDate = new Date(1503658800000L);		//	08/25/2017 13:00
		res4.setTerminOd(od);
		res4.setTerminDo(doDate);
		reservationRepository.save(res4); 
		
		
		g.getReservations().add(res); 
		g.getReservations().add(res1); 
		g.getReservations().add(res2); 
		g2.getReservations().add(res3); 
		g2.getReservations().add(res4); 
		guestRepository.save(g); 
		guestRepository.save(g2); 
		
		
		Grade gr1 = new Grade();
		gr1.setAtmosphereGrade(3);
		gr1.setEnvironmentGrade(1);
		gr1.setRated(true);
		gr1.setWaiterGrade(4);
		gr1.setMealGrade(5);
		gr1.setGuest(g);
		gr1.setReservation(res);
		gr1.setRestaurantGrade(4);
		gradeRepository.save(gr1);
		
		Grade gr2 = new Grade();
		gr2.setAtmosphereGrade(1);
		gr2.setEnvironmentGrade(2);
		gr2.setRated(true);
		gr2.setWaiterGrade(3);
		gr2.setMealGrade(3);
		gr2.setGuest(g);
		gr2.setReservation(res1);
		gr2.setRestaurantGrade(3);
		gradeRepository.save(gr2);
		
		Grade gr3 = new Grade();
		gr3.setAtmosphereGrade(4);
		gr3.setEnvironmentGrade(5);
		gr3.setRated(true);
		gr3.setWaiterGrade(4);
		gr3.setMealGrade(4);
		gr3.setGuest(g);
		gr3.setReservation(res2);
		gr3.setRestaurantGrade(5);
		gradeRepository.save(gr3);
		
		
		Grade gr4 = new Grade();
		gr4.setAtmosphereGrade(5);
		gr4.setEnvironmentGrade(5);
		gr4.setRated(true);
		gr4.setWaiterGrade(5);
		gr4.setMealGrade(4);
		gr4.setGuest(g2);
		gr4.setReservation(res3);
		gr4.setRestaurantGrade(5);
		gradeRepository.save(gr4);
		
		Grade gr5 = new Grade();
		gr5.setAtmosphereGrade(1);
		gr5.setEnvironmentGrade(1);
		gr5.setRated(true);
		gr5.setWaiterGrade(1);
		gr5.setMealGrade(4);
		gr5.setGuest(g2);
		gr5.setReservation(res4);
		gr5.setRestaurantGrade(1);
		gradeRepository.save(gr5);
		
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
		//Restaurant re1 = new Restaurant("Puno mesa","Stvarno dobar restoran sa mesom", "555-12345", "Bulevar Oslobodjenja 6 Novi Sad", "punomesa@restorani.com");
		//restaurantRepository.save(re1); 
		
		//Restaurant re2 = new Restaurant("Malo mesa","Ne bas sjajan restoran sa mesom", "555-12346", "Somborski Bulevar 56 Novi Sad", "malomesa@restorani.com"); 
		//restaurantRepository.save(re2); 
		
		//Restaurant re3 = new Restaurant("Bilje i zacini","Ako bas ne volite meso", "555-12489", "Jevrejska 20 Novi Sad", "biljeizacini@restorani.com"); 
		//restaurantRepository.save(re3); 
		
		// RESTAURANT AREAS:
		RestaurantArea ra1 = new RestaurantArea("nepusacka zona", r);
		restaurantAreaRepository.save(ra1);
		
		RestaurantArea ra2 = new RestaurantArea("pusacka zona", r);
		restaurantAreaRepository.save(ra2);
		
		RestaurantArea ra3 = new RestaurantArea("treca zona", r);
		restaurantAreaRepository.save(ra3);
		
		RestaurantArea ra4 = new RestaurantArea("nepusacka zona", r2);
		restaurantAreaRepository.save(ra4);
		
		RestaurantArea ra5 = new RestaurantArea("pusacka zona", r2);
		restaurantAreaRepository.save(ra5);
		
		RestaurantArea ra6 = new RestaurantArea("treca zona", r2);
		restaurantAreaRepository.save(ra6);
		
		
		// RESTAURANT TABLES:
		RestaurantTable rt1 = new RestaurantTable("sto 1", 2, ra1, "{\"type\":\"rect\",\"left\":10,\"top\":10,\"width\":50,\"height\":100,\"fill\":\"blue\"}");
		restaurantTableRepository.save(rt1);
		
		RestaurantTable rt2 = new RestaurantTable("sto 2", 2, ra2, "{\"type\":\"rect\",\"left\":30,\"top\":30,\"width\":40,\"height\":80,\"fill\":\"yellow\"}");
		restaurantTableRepository.save(rt2);
		
		RestaurantTable rt3 = new RestaurantTable("sto 3", 2, ra3, "{\"type\":\"rect\",\"left\":50,\"top\":50,\"width\":20,\"height\":60,\"fill\":\"green\"}");
		restaurantTableRepository.save(rt3);
		
		
		
		
		RestaurantTable rt4 = new RestaurantTable("sto 4", 2, ra4, "{\"type\":\"rect\",\"left\":10,\"top\":10,\"width\":50,\"height\":100,\"fill\":\"blue\"}");
		restaurantTableRepository.save(rt4);
		
		RestaurantTable rt5 = new RestaurantTable("sto 5", 2, ra5, "{\"type\":\"rect\",\"left\":30,\"top\":30,\"width\":40,\"height\":80,\"fill\":\"yellow\"}");
		restaurantTableRepository.save(rt5);
		
		RestaurantTable rt6 = new RestaurantTable("sto 6", 2, ra6, "{\"type\":\"rect\",\"left\":50,\"top\":50,\"width\":20,\"height\":60,\"fill\":\"green\"}");
		restaurantTableRepository.save(rt6);
		
		
		// RESTAURANT MANAGERS:
		RestaurantManager rm1 = new RestaurantManager("rm1@rm1.com", passwordEncoder.encode("rm1"), "Zika", "Menadzer", "Zikina Adresa", r);
		rm1.setActive(true);
		restaurantManagerRepository.save(rm1);
		
		RestaurantManager rm2 = new RestaurantManager("rm2@rm2.com", passwordEncoder.encode("rm2"), "Zika", "Menadzer", "Zikina Adresa", r2);
		rm1.setActive(true);
		restaurantManagerRepository.save(rm2);
		
		// RESTAURANT MENU:
		Menu mnu1 = new Menu(r);
		menuRepository.save(mnu1);
		
		Menu mnu2 = new Menu(r2);
		menuRepository.save(mnu2);
		
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
		
		
		MenuItem mi7 = new MenuItem(500, it1, mnu2);
		menuItemRepository.save(mi7);
		MenuItem mi8 = new MenuItem(800, it2, mnu2);
		menuItemRepository.save(mi8);
		MenuItem mi9 = new MenuItem(300, it3, mnu2);
		menuItemRepository.save(mi9);
		MenuItem mi10 = new MenuItem(150, it4, mnu2);
		menuItemRepository.save(mi10);
		MenuItem mi11 = new MenuItem(300, it5, mnu2);
		menuItemRepository.save(mi11);
		MenuItem mi12 = new MenuItem(300, it6, mnu2);
		menuItemRepository.save(mi12);
		
		// BIDDERS:
		Bidder bd1 = new Bidder("bd1@bd1.com", passwordEncoder.encode("bd1"), "Marko", "Markova adresa", "Ponudic");
		bd1.setActive(true);
		bidderRepository.save(bd1);
		
		// RESTAURANT WORKERS:
		Bartender bt1 = new Bartender("bt1@bt1.com", passwordEncoder.encode("bt1"), "Milan", "Pivic", "Milanova adresa", r, new Date(), 15, 42);
		bt1.setActive(true);
		bt1.setPassword_set(true);
		bartenderRepository.save(bt1);
		
		Bartender bt2 = new Bartender("bt2@bt2.com", passwordEncoder.encode("bt2"), "Jovan", "Jovic", "Jovina adresa", r, new Date(), 15, 42);
		bt2.setActive(true);
		bt2.setPassword_set(false);
		bartenderRepository.save(bt2);
		
		Chef ch1 = new Chef("ch1@ch1.com", passwordEncoder.encode("ch1"), "Slavko", "Mesic", "Slavkova adresa", r2, new Date(), 15, 42);
		ch1.setActive(true);
		ch1.setPassword_set(true);
		chefRepository.save(ch1);
		
		Chef ch2 = new Chef("ch2@ch2.com", passwordEncoder.encode("ch2"), "Strudla","Mesic", "Strudlina adresa", r, new Date(), 15, 42);
		ch1.setActive(true);
		ch1.setPassword_set(false);
		chefRepository.save(ch2);
		
		Waiter wt1 = new Waiter("wt1@wt1.com", passwordEncoder.encode("wt1"), "Rob", "Sluskic", "Adresa roba", r, new Date(), 15, 42);
		wt1.setActive(true);
		wt1.setPassword_set(true);
		waiterRepository.save(wt1);
		
		Waiter wt2 = new Waiter("wt2@wt2.com", passwordEncoder.encode("wt2"), "Slusko", "Sluzic", "Sluskova adresa", r2, new Date(), 15, 42);
		wt1.setActive(true);
		wt1.setPassword_set(false);
		waiterRepository.save(wt1);
		
		Waiter wa1 = new Waiter("wa1@wa1.com", passwordEncoder.encode("wa1"), "Pera", "Usluzic", "Perina adresa", r, new Date(), 15, 42);
		wa1.setActive(true);
		waiterRepository.save(wa1);
		
		// RESTAURANT WORKER SHIFTS:
		Shift sh1 = new Shift(new Date(), "08:00", "20:00", true, r);
		shiftRepository.save(sh1);
		
		ShiftChef sc1 = new ShiftChef(ch1, sh1);
		shiftChefRepository.save(sc1);
		
		ShiftBartender sBt1 = new ShiftBartender(bt1, sh1);
		shiftBartenderRepository.save(sBt1);
		
		ShiftWaiter sw1 = new ShiftWaiter(wt1, sh1);
		sw1.getAreas().add(ra1);
		sw1.getAreas().add(ra3);
		shiftWaiterRepository.save(sw1);
		
		
		InvitedToReservation i = new InvitedToReservation(); 
		i.setReservation(res);
		i.setGuest(g3);
		invitedToReservationRepository.save(i); 
		
		InvitedToReservation i1 = new InvitedToReservation(); 
		i1.setReservation(res);
		i1.setGuest(g2);
		invitedToReservationRepository.save(i1); 
		
		InvitedToReservation i2 = new InvitedToReservation(); 
		i2.setReservation(res1);
		i2.setGuest(g2);
		invitedToReservationRepository.save(i2); 
		
		InvitedToReservation i3 = new InvitedToReservation(); 
		i3.setReservation(res2);
		i3.setGuest(g3);
		invitedToReservationRepository.save(i3); 
		
		InvitedToReservation i4 = new InvitedToReservation(); 
		i4.setReservation(res3);
		i4.setGuest(g);
		invitedToReservationRepository.save(i4); 
		
		InvitedToReservation i5 = new InvitedToReservation(); 
		i5.setReservation(res3);
		i5.setGuest(g3);
		invitedToReservationRepository.save(i5); 
		
		InvitedToReservation i6 = new InvitedToReservation(); 
		i6.setReservation(res4);
		i6.setGuest(g);
		invitedToReservationRepository.save(i6);
		
		/////////////////////////////
		///////////////////////////
		
		GuestReservationOrder order1 = new GuestReservationOrder();
		order1.setReservation(res);
		order1.setItem(mi1);
		order1.setGuest(g);
		order1.setWaiter(wt1);
		order1.setChef(ch1);
		order1.setCreated(false);
		order1.setAccepted(false);
		guestReservationOrderRepository.save(order1);
		
		GuestReservationOrder order2 = new GuestReservationOrder();
		order2.setReservation(res1);
		order2.setItem(mi2);
		order2.setGuest(g);
		order2.setWaiter(wt1);
		order2.setChef(ch1);
		order2.setCreated(false);
		order2.setAccepted(false);
		guestReservationOrderRepository.save(order2);
		
		GuestReservationOrder order3 = new GuestReservationOrder();
		order3.setReservation(res2);
		order3.setItem(mi3);
		order3.setGuest(g);
		order3.setWaiter(wt1);
		order3.setChef(ch1);
		order3.setCreated(false);
		order3.setAccepted(true);
		guestReservationOrderRepository.save(order3);
		
		GuestReservationOrder order4 = new GuestReservationOrder();
		order3.setReservation(res3);
		order3.setItem(mi4);
		order3.setGuest(g);
		order3.setWaiter(wt1);
		order3.setChef(ch1);
		order3.setCreated(false);
		order3.setAccepted(true);
		guestReservationOrderRepository.save(order4);
		
		GuestReservationOrder oder5 = new GuestReservationOrder();
		oder5.setReservation(res4);
		oder5.setItem(mi5);
		oder5.setGuest(g);
		oder5.setWaiter(wt1);
		oder5.setChef(ch1);
		oder5.setCreated(false);
		oder5.setAccepted(true);
		guestReservationOrderRepository.save(oder5);	
		
		ReservationRestaurantTable rrt = new ReservationRestaurantTable(); 
		rrt.setReservation(res);
		rrt.setTable(rt1);
		rrt = reservationRestaurantTableRepository.save(rrt); 
		
		ReservationRestaurantTable rrt0 = new ReservationRestaurantTable(); 
		rrt0.setReservation(res);
		rrt0.setTable(rt2);
		reservationRestaurantTableRepository.save(rrt0); 
		
		
		ReservationRestaurantTable rrt1 = new ReservationRestaurantTable(); 
		rrt1.setReservation(res1);
		rrt1.setTable(rt2);
		reservationRestaurantTableRepository.save(rrt1); 
		
		ReservationRestaurantTable rrt2 = new ReservationRestaurantTable(); 
		rrt2.setReservation(res3);
		rrt2.setTable(rt3);
		reservationRestaurantTableRepository.save(rrt2); 
		
		ReservationRestaurantTable rrt3 = new ReservationRestaurantTable(); 
		rrt3.setReservation(res2);
		rrt3.setTable(rt4);
		reservationRestaurantTableRepository.save(rrt3); 
		
		ReservationRestaurantTable rrt4 = new ReservationRestaurantTable(); 
		rrt4.setReservation(res4);
		rrt4.setTable(rt5);
		reservationRestaurantTableRepository.save(rrt4); 
		
		ReservationRestaurantTable rrt5 = new ReservationRestaurantTable(); 
		rrt5.setReservation(res4);
		rrt5.setTable(rt6);
		reservationRestaurantTableRepository.save(rrt5);		
		
		System.out.println("DATABASE FILLED");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
