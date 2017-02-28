package e2.isa.grupa5.service.guest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;

import e2.isa.grupa5.model.grade.Grade;
import e2.isa.grupa5.model.reservation.InvitedToReservation;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.reservation.ReservationRestaurantTable;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantTable;
import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.User;
import e2.isa.grupa5.model.users.UserRoles;
import e2.isa.grupa5.repository.grade.GradeRepository;
import e2.isa.grupa5.repository.guest.GuestRepository;
import e2.isa.grupa5.repository.guest.InvitedToReservationRepository;
import e2.isa.grupa5.repository.guest.ReservationRepository;
import e2.isa.grupa5.repository.guest.ReservationRestaurantTableRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantTableRepository;
import e2.isa.grupa5.rest.dto.guest.CreateNewReservationDTO;
import e2.isa.grupa5.rest.dto.guest.DistanceDTO;
import e2.isa.grupa5.rest.dto.guest.FriendsPageDTO;
import e2.isa.grupa5.rest.dto.guest.HomePageDTO;
import e2.isa.grupa5.rest.dto.guest.ProfilePageDTO;
import e2.isa.grupa5.rest.dto.guest.ReservationDTO;
import e2.isa.grupa5.rest.dto.guest.Reserve1PageDTO;
import e2.isa.grupa5.rest.dto.guest.RestaurantsPageDTO;
import e2.isa.grupa5.service.MailService;

@Service
@Transactional
public class GuestService {

	@Autowired
	private GuestRepository guestRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private InvitedToReservationRepository invitedToReservationRepository;
	
	@Autowired
	private GradeRepository gradeRepository;

	@Value("${jelena.google.key}")
	private String jelenaApiKey;
	
	private final Log logger = LogFactory.getLog(this.getClass());

	/**
	 * Request BCrypt2 encoder
	 * 
	 * @return
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MailService mailManager;
	
	@Autowired 
	private ReservationRestaurantTableRepository reservationRestaurantTableRepository;
	
	@Autowired
	private RestaurantTableRepository restaurantTableRepository;

	public List<Guest> findAll() {
		return guestRepository.findAll();
	}

	public Guest findOne(Long id) {
		return guestRepository.findOne(id);
	}

	private Guest save(Guest guest) {
		return guestRepository.save(guest);
	}

	public Guest delete(Long id) {
		Guest guest = guestRepository.findOne(id);

		if (guest != null) {
			guestRepository.delete(guest);
		}

		return guest;
	}

	public ProfilePageDTO getProfilePageInfo(Long id) {
		ProfilePageDTO dto = new ProfilePageDTO();

		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return null;
		}
		dto.setName(guest.getName());
		dto.setSurname(guest.getSurname());
		dto.setAddress(guest.getAddress());
		long numberOfVisits = 0;
		numberOfVisits += reservationRepository.countByGuest(guest);
		numberOfVisits += invitedToReservationRepository.countByGuest(guest);
		dto.setNumberOfVisits(numberOfVisits);
		List<Guest> friends = guest.getFriends();
		List<ProfilePageDTO> friendsDTO = new ArrayList<>();
		for (Guest friend : friends) {
			ProfilePageDTO friendDTO = new ProfilePageDTO();
			friendDTO.setName(friend.getName());
			friendDTO.setSurname(friend.getSurname());
			friendsDTO.add(friendDTO);

		}
		dto.setFriends(friendsDTO);
		return dto;
	}

	public boolean verifyGuest(long id) {
		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return false;
		}
		guest.setActive(true);
		guestRepository.save(guest);
		return true;
	}

	public Guest registerGuest(User user) {
		user.setRole(UserRoles.GUEST);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Guest guest = new Guest(user);
		guest.setActive(false);

		Guest saved = save(guest);

		System.out.println((User) saved);

		if (saved != null) {
			System.out.println("MAIL SENT TO " + guest.getEmail());
			mailManager.sendMail(guest);

			return saved;
		}
		return null;
	}
	
	public Guest registerAndOrLoginGuestWithSocialProvider(User user) {
		user.setRole(UserRoles.GUEST);
		Guest guest = new Guest(user);
		guest.setPassword(passwordEncoder.encode(user.getPassword()));
		guest.setActive(true);
		
		// if user is already registered, return from DB
		Guest exists = guestRepository.findByEmail(user.getEmail());
		if(exists != null) {
			return exists;
		}
		
		Guest saved = save(guest);
		logger.info("User registered via social provider!");
		System.out.println((User) saved);
		return saved;
	}

	public List<HomePageDTO> getHomePageInfo(Long id) {
		List<HomePageDTO> homePageData = new ArrayList<HomePageDTO>();

		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return null;
		}

		// Create an instance of SimpleDateFormat used for formatting
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");

		List<Reservation> reservations = guest.getReservations();
		for (Reservation reservation : reservations) {
			HomePageDTO dto = new HomePageDTO();
			String restauranName = reservation.getRestaurant().getName();
			dto.setRestaurantName(restauranName);
			
			// Get the date today using Calendar object.
			Date date = reservation.getTerminOd();
			// Using DateFormat format method we can create a string
			// representation of a date with the defined format.
			String reportDate = df.format(date);

			dto.setDate(reportDate);
			List<InvitedToReservation> friends = invitedToReservationRepository.findByReservation(reservation);

			StringBuilder builder = new StringBuilder();

			for (InvitedToReservation friend : friends) {
				builder.append(friend.getGuest().getName()).append(" ");
			}

			dto.setFriends(builder.toString());
			homePageData.add(dto);
		}

		return homePageData;
	}

	public List<FriendsPageDTO> getFriendsPageInfo(Long id) {
		List<FriendsPageDTO> friendPageData = new ArrayList<FriendsPageDTO>();

		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return null;
		}

		List<Guest> friends = guest.getFriends();
		for (Guest friend : friends) {
			FriendsPageDTO dto = new FriendsPageDTO();
			String friendName = friend.getName();

			StringBuilder builder = new StringBuilder();
			builder.append(friend.getName()).append(" ").append(friend.getSurname());

			dto.setNameAndSurname(builder.toString());

			long numberOfVisits = 0;
			numberOfVisits += reservationRepository.countByGuest(friend);
			numberOfVisits += invitedToReservationRepository.countByGuest(friend);
			dto.setNumberOfVisits(numberOfVisits);

			friendPageData.add(dto);
		}

		return friendPageData;
	}

	public boolean updateProfileInfo(Long id, String name, String surname, String address) {
		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return false;
		}

		guest.setName(name);
		guest.setSurname(surname);
		guest.setAddress(address);
		guestRepository.save(guest);
		return true;
	}

	public List<RestaurantsPageDTO> getRestaurantsPageInfo(Long id) {

		List<RestaurantsPageDTO> restaurantPageData = new ArrayList<RestaurantsPageDTO>();

		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return restaurantPageData;
		}

		List<Restaurant> restaurants = restaurantRepository.findAll();
		for (Restaurant restaurant : restaurants) {
			RestaurantsPageDTO dto = new RestaurantsPageDTO();
			dto.setRestaurantId(restaurant.getId());
			dto.setName(restaurant.getName());
			getRestaurantDistance(dto, restaurant, guest);
			getRestaurantRating(dto, restaurant);
			getRestaurantFriendsRating(dto, restaurant, guest);
			restaurantPageData.add(dto);
		}

		return restaurantPageData;
	}

	private int getReservationRatingSum(Reservation reservation, Guest guest) {
		List<Grade> grades = gradeRepository.findByReservation_id(reservation.getId());
		double totalGrade = 0;
		for(Grade grade : grades) {
			if(guest == null ||  grade.getGuest().getId() == guest.getId()) {
				totalGrade += grade.getRestaurantGrade();	
			}
		}
		return (int) Math.round(totalGrade/grades.size());
		
	}
	
	private void getRestaurantFriendsRating(RestaurantsPageDTO dto, Restaurant restaurant, Guest guest) {
		double rating = 0;
		List<Reservation> allReservations = reservationRepository.findAll();
		for(Reservation reservation : allReservations) {
			for(Guest friend: guest.getFriends()) {
				rating += getReservationRatingSum(reservation, friend);	
			}
		}
		rating = Math.round(rating/allReservations.size());
		dto.setFriendsRating((int) rating);
		
	}

	private void getRestaurantRating(RestaurantsPageDTO dto, Restaurant restaurant) {
		double rating = 0;
		List<Reservation> allReservations = reservationRepository.findAll();
		for(Reservation reservation : allReservations) {
			rating += getReservationRatingSum(reservation, null);
		}
		rating = Math.round(rating/allReservations.size());
		dto.setRating((int) rating);
		

	}

	private void getRestaurantDistance(RestaurantsPageDTO dto, Restaurant restaurant, Guest guest) {
		DistanceDTO distanceDTO = new DistanceDTO();
		GeoApiContext context = new GeoApiContext().setApiKey(jelenaApiKey);
		try {
			String[] guestAddress = new String[] { guest.getAddress() };
			String[] restaurantAddress = new String[] { restaurant.getAddress() };
			DistanceMatrix distanceMatrix = DistanceMatrixApi
					.getDistanceMatrix(context, guestAddress, restaurantAddress).await();
			if (distanceMatrix == null || distanceMatrix.rows == null) {
				distanceDTO.setDistanceKm(-1);
				distanceDTO.setDistanceM(-1);
				return;
			}
			for (DistanceMatrixRow row : distanceMatrix.rows) {
				if (row.elements == null || row.elements.length == 0) {
					continue; // skip empty distance matrix rows if any
				}
				// find 0th elements and use distance
				System.out.println("DISTANCEE:" + row.elements[0].distance);
				distanceDTO.setDistanceKm(row.elements[0].distance.inMeters / 1000.0);
				distanceDTO.setDistanceM(row.elements[0].distance.inMeters);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Reserve1PageDTO getReserve1PageInfo(Long id) {
		Reserve1PageDTO reserve1 = new Reserve1PageDTO(); 
		Restaurant restaurant = restaurantRepository.findById(id);
		reserve1.setRestaurantName(restaurant.getName());
		return reserve1;
	}

	public List<FriendsPageDTO> getGuestFriendsInfo(Long id) {
		Guest guest = guestRepository.findOne(id); 
		List<FriendsPageDTO> friends = new ArrayList<>(); 
		
		for(Guest friend : guest.getFriends()) {
			FriendsPageDTO f = new FriendsPageDTO(); 
			f.setId(friend.getId());
			f.setNameAndSurname(friend.getName()+" "+friend.getSurname());
			friends.add(f); 
		}
		
		
		return friends;
	}

	public CreateNewReservationDTO createNewReservation(CreateNewReservationDTO dto) {
		CreateNewReservationDTO responseDTO = new CreateNewReservationDTO();
		Guest guest = guestRepository.findOne(dto.getGuestId());
		Restaurant restaurant = restaurantRepository.findOne(dto.getRestaurantId());
		Reservation created = new Reservation();
		created.setRestaurant(restaurant);
		created.setGuest(guest);
		Date from = new Date(dto.getDate().getTime());
		from.setHours(dto.getArrival().getHours());
		from.setMinutes(dto.getArrival().getMinutes());
		
		Date to = new Date(from.getTime());
		
		to.setTime(to.getTime() + dto.getDuration()*3600*1000);
		created.setTerminOd(from);
		created.setTerminDo(to);
		
		// check if each table is still available...
		
		// check all reservations 
		if(!tablesAvailable(from, to, restaurant, dto.getTables())) {
			responseDTO.setErrorInfo("Tables were reserved by some other guest, choose a different table/tables and try again.");
			return responseDTO;
		}
		
		created = reservationRepository.save(created);
		
		List<ReservationRestaurantTable> reservedTables = new ArrayList<>();
		for(Long tableId : dto.getTables()) {
			ReservationRestaurantTable reservedTable = new ReservationRestaurantTable();
			reservedTable.setReservation(created);
			RestaurantTable table = restaurantTableRepository.findOne(tableId);
			reservedTable.setTable(table);
			reservedTable = reservationRestaurantTableRepository.save(reservedTable);
			reservedTables.add(reservedTable);
		}
		
	
		for(Long invitedId : dto.getInvitedFriends()) {
			InvitedToReservation invited = new InvitedToReservation();
			invited.setReservation(created);
			Guest invitedFriend = guestRepository.findOne(invitedId);
			invited.setGuest(invitedFriend);
			invitedToReservationRepository.save(invited);
			mailManager.sendReservationInvitationMail(invitedFriend, created);
		}
		
		responseDTO.setSuccess(true);
		
		return responseDTO;
	}

	private boolean tablesAvailable(Date from, Date to, Restaurant restaurant, List<Long> tables) {
		List<Reservation> reservations = reservationRepository.findByRestaurant(restaurant);
		for(Reservation reservation : reservations) {
			if(reservation.getTerminOd().getTime() >= from.getTime() && reservation.getTerminDo().getTime() > from.getTime()) {
				List<ReservationRestaurantTable> reservedTables = reservationRestaurantTableRepository.findByReservation(reservation);
				for(ReservationRestaurantTable reservedTable : reservedTables) {
					
					// check if any of currently reserved tables
					// match the newly reserved tables
					for(Long tableId : tables) {
						if(tableId.equals(reservedTable.getId())) {
							return false; // cannot reserve
						}
					}
					
				}
				
			}
			
		}
		return true;
		
	}

	public List<ReservationDTO> getGuestReservations(Long id) {
		Guest guest = guestRepository.findOne(id); 
		List<Reservation> reservations = reservationRepository.findByGuest(guest); 
		List<InvitedToReservation> invitedReservations = invitedToReservationRepository.findByGuest(guest); 
		for(InvitedToReservation i : invitedReservations) {
			reservations.add(i.getReservation()); 
		}
		
		List<ReservationDTO> reservationsDTO = new ArrayList<>(); 
		
		for(Reservation r : reservations) {
			ReservationDTO dto = new ReservationDTO(); 
			dto.setId(r.getId());
			dto.setRestaurantName(r.getRestaurant().getName());
			dto.setTerminDo(r.getTerminOd());
			dto.setTerminOd(r.getTerminOd());
			reservationsDTO.add(dto); 
		}
		
		return reservationsDTO;
	}

	public ReservationDTO getReservationDetails(Long guestId, Long reservationId) {
		Reservation reservation = reservationRepository.findOne(reservationId); 
		Guest guest = guestRepository.findOne(guestId); 
		// is our guest the owner of the reservation
		// or INVITED to a reservation?
		if(guest.getId() != reservation.getGuest().getId()) {
			// invited
			//InvitedToReservation invitation = invitati
		}
		
		return null;
	}
}
