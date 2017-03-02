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

import e2.isa.grupa5.model.friends.FriendshipRequest;
import e2.isa.grupa5.model.grade.Grade;
import e2.isa.grupa5.model.reservation.GuestReservationOrder;
import e2.isa.grupa5.model.reservation.InvitedToReservation;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.reservation.ReservationRestaurantTable;
import e2.isa.grupa5.model.restaurant.Menu;
import e2.isa.grupa5.model.restaurant.MenuItem;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantTable;
import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.User;
import e2.isa.grupa5.model.users.UserRoles;
import e2.isa.grupa5.repository.grade.GradeRepository;
import e2.isa.grupa5.repository.guest.FriendshipRequestRepository;
import e2.isa.grupa5.repository.guest.GuestRepository;
import e2.isa.grupa5.repository.reservation.GuestReservationOrderRepository;
import e2.isa.grupa5.repository.reservation.InvitedToReservationRepository;
import e2.isa.grupa5.repository.reservation.ReservationRepository;
import e2.isa.grupa5.repository.reservation.ReservationRestaurantTableRepository;
import e2.isa.grupa5.repository.restaurant.MenuItemRepository;
import e2.isa.grupa5.repository.restaurant.MenuRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantTableRepository;
import e2.isa.grupa5.rest.dto.guest.AcceptPeopleDTO;
import e2.isa.grupa5.rest.dto.guest.AddPeopleDTO;
import e2.isa.grupa5.rest.dto.guest.CreateNewReservationDTO;
import e2.isa.grupa5.rest.dto.guest.DistanceDTO;
import e2.isa.grupa5.rest.dto.guest.FriendsPageDTO;
import e2.isa.grupa5.rest.dto.guest.GuestOrderDTO;
import e2.isa.grupa5.rest.dto.guest.HomePageDTO;
import e2.isa.grupa5.rest.dto.guest.ProfilePageDTO;
import e2.isa.grupa5.rest.dto.guest.ReservationDTO;
import e2.isa.grupa5.rest.dto.guest.ReservationDetailsDTO;
import e2.isa.grupa5.rest.dto.guest.Reserve1PageDTO;
import e2.isa.grupa5.rest.dto.guest.RestaurantsPageDTO;
import e2.isa.grupa5.service.MailService;

@Service
@Transactional
public class GuestService {

	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private FriendshipRequestRepository friendshipRequestRepository; 


	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private InvitedToReservationRepository invitedToReservationRepository;
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private GuestReservationOrderRepository guestOrderRepository;

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
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Autowired
	private MenuRepository menuRepository;

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
			friendDTO.setId(friend.getId());
			friendDTO.setName(friend.getName());
			friendDTO.setSurname(friend.getSurname());
			friendsDTO.add(friendDTO);

		}
		dto.setFriends(friendsDTO);
		return dto;
	}

	public boolean verifyGuest(long id) {
		Guest guest = guestRepository.findOne(id);
		System.out.println("********************************");
		System.out.println(id);
		System.out.println("********************************");
		
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

		List<Reservation> reservations = reservationRepository.findByGuest(guest);
		for (Reservation reservation : reservations) {
			HomePageDTO dto = new HomePageDTO();
			String restauranName = reservation.getRestaurant().getName();
			dto.setRestaurantName(restauranName);
			
			// Get the date today using Calendar object.
			Date date = reservation.getTerminOd();
			// Using DateFormat format method we can create a string
			// representation of a date with the defined format.
			if(date != null) {
				String reportDate = df.format(date);
				dto.setDate(reportDate);
			}
			

			
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
			dto.setId(friend.getId());
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
			dto.setRating((int) Math.round(Math.random()%5) +  1);
			getRestaurantFriendsRating(dto, restaurant, guest);
			dto.setFriendsRating((int) Math.round(Math.random()%5) + 1);
			restaurantPageData.add(dto);
		}

		return restaurantPageData;
	}

	private int getReservationRatingSum(Reservation reservation, Guest guest) {
		List<Grade> grades = gradeRepository.findByReservation_id(reservation.getId());
		double totalGrade = 0;
		int gradedReservationsCount = 0;
		for(Grade grade : grades) {
			if(guest == null ||  grade.getGuest().getId() == guest.getId()) {
				 gradedReservationsCount ++;
				totalGrade += grade.getRestaurantGrade();	
			}
		}
		return (int) Math.round(totalGrade/ gradedReservationsCount);
		
	}
	
	private void getRestaurantFriendsRating(RestaurantsPageDTO dto, Restaurant restaurant, Guest guest) {
		double rating = 0;
		List<Reservation> allReservations = reservationRepository.findByRestaurant(restaurant);
		int gradedReservationsCount = 0;
		for(Reservation reservation : allReservations) {
			for(Guest friend: guest.getFriends()) {
				int rRating = getReservationRatingSum(reservation, friend);	
				if(rRating > 0) {
					gradedReservationsCount++;
					rating += rRating;
				}
			}
		}
		rating = Math.round(rating/gradedReservationsCount);
		dto.setFriendsRating((int) rating);
		
	}

	private void getRestaurantRating(RestaurantsPageDTO dto, Restaurant restaurant) {
		double rating = 0;
		List<Reservation> allReservations = reservationRepository.findByRestaurant(restaurant);
		int gradedReservationsCount = 0;
		for(Reservation reservation : allReservations) {
			
			 int rRating = getReservationRatingSum(reservation, null);
			if(rRating > 0) {
				gradedReservationsCount++;
				rating += rRating;
			}
		}
		rating = Math.round(rating/gradedReservationsCount);
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
				dto.setDistance(distanceDTO);
				return;
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
			if(r.getRestaurant() != null && r.getRestaurant().getName() != null) {
				dto.setRestaurantName(r.getRestaurant().getName());
			}
			
			dto.setTerminDo(r.getTerminOd());
			dto.setTerminOd(r.getTerminOd());
			reservationsDTO.add(dto); 
		}
		
		return reservationsDTO;
	}
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	public ReservationDetailsDTO getReservationDetails(Long guestId, Long reservationId) {
		ReservationDetailsDTO responseDTO = new ReservationDetailsDTO();
		Reservation reservation = reservationRepository.findOne(reservationId); 
		Guest guest = guestRepository.findOne(guestId); 
		// is our guest the owner of the reservation
		// or INVITED to a reservation?
		if(guest.getId() != reservation.getGuest().getId()) {
			// invited
			List<InvitedToReservation> invites = invitedToReservationRepository.findByReservationAndGuest(reservation, guest);
			if(invites == null || invites.size() == 0) {
				logger.error("FAILED TO ACQUIRE INVITATION ...getReservation details failed!!!");
		    	return responseDTO;
			}
			InvitedToReservation invitation	 = invites.get(0);
			responseDTO.setConfirmed(invitation.isConfirmed());
		}
		// get guest orders for this Reservation
		List<GuestOrderDTO> guestOrdersDto = new ArrayList<>();
		List<GuestReservationOrder> guestOrders = guestOrderRepository.findByReservationAndGuest(reservation, guest);
		for(GuestReservationOrder guestOrder : guestOrders) {
			convertToGuestOrderDTO(guestOrdersDto, guestOrder);
		}
		
		// get all available orders 
	    Restaurant restaurant = reservation.getRestaurant();
	    // find menu
	    List<Menu> allMenues = menuRepository.findByRestaurant(restaurant);
	    if(allMenues ==null || allMenues.size() == 0) {
	    	logger.error("FAILED TO ACQUIRE MENU...getReservation details failed!!!");
	    	return responseDTO;
	    }
	    List<MenuItem> menuItems = menuItemRepository.findByMenu(allMenues.get(0));
	    
		List<GuestOrderDTO> allOrdersDto = new ArrayList<>();
		// prepare menu items for frontend
		for(MenuItem menuItem : menuItems ) {
			convertToGuestOrderDTO(allOrdersDto, menuItem);
		}
		
		responseDTO.setAllOrders(allOrdersDto);
		responseDTO.setGuestOrders(guestOrdersDto);
		responseDTO.setTerminOd(reservation.getTerminOd());
		responseDTO.setGuestId(reservation.getGuest().getId());
		responseDTO.setSuccess(true);
		
		return responseDTO;
	}

	
	private void convertToGuestOrderDTO(List<GuestOrderDTO> allOrdersDto, MenuItem menuItem) {
		GuestOrderDTO orderDto = new GuestOrderDTO();
		orderDto.setMenuItemName(menuItem.getItem().getName());
		orderDto.setMenuItemId(menuItem.getItem().getId());
		orderDto.setBePrepared(orderDto.isBePrepared());
		allOrdersDto.add(orderDto);
	}

	private void convertToGuestOrderDTO(List<GuestOrderDTO> guestOrdersDto, GuestReservationOrder guestOrder) {
		GuestOrderDTO orderDto = new GuestOrderDTO();
		orderDto.setMenuItemName(guestOrder.getItem().getItem().getName());
		orderDto.setMenuItemId(guestOrder.getItem().getId());
		orderDto.setBePrepared(guestOrder.isBePrepared());
		guestOrdersDto.add(orderDto);
	}

	public ReservationDetailsDTO cancelAttendance(Long guestId, Long reservationId) {
		ReservationDetailsDTO response = new ReservationDetailsDTO();
		List<InvitedToReservation> invitations = invitedToReservationRepository.findByReservationAndGuest(reservationRepository.findOne(reservationId), guestRepository.findOne(guestId));
		if(invitations == null || invitations.size() != 1) {
			logger.error("failed to fetch invitation for guestId: reservationId:" + guestId + ", " + reservationId);
			return response;
		}
		InvitedToReservation invitation = invitations.get(0);
		invitation.setConfirmed(false);
		invitedToReservationRepository.save(invitation);
		response.setSuccess(true);
		return response;
	}

	public ReservationDetailsDTO confirmAttendance(Long guestId, Long reservationId) {
		ReservationDetailsDTO response = new ReservationDetailsDTO();
		List<InvitedToReservation> invitations = invitedToReservationRepository.findByReservationAndGuest(reservationRepository.findOne(reservationId), guestRepository.findOne(guestId));
		if(invitations == null || invitations.size() != 1) {
			logger.error("failed to fetch invitation for guestId: reservationId:" + guestId + ", " + reservationId);
			return response;
		}
		InvitedToReservation invitation = invitations.get(0);
		invitation.setConfirmed(true);
		invitedToReservationRepository.save(invitation);
		response.setSuccess(true);
		return response;
	}

	public ReservationDetailsDTO cancelReservation(Long guestId, Long reservationId) {
		ReservationDetailsDTO responseDTO = new ReservationDetailsDTO();
		Reservation reservation = reservationRepository.findOne(reservationId);
		List<InvitedToReservation> invitations = invitedToReservationRepository.findByReservation(reservation);
		for(InvitedToReservation invited : invitations) {
			invitedToReservationRepository.delete(invited);
		}
		
		List<GuestReservationOrder> orders = guestOrderRepository.findByReservation(reservation);
		for (GuestReservationOrder order : orders) {
			guestOrderRepository.delete(order);
		}
		
		reservationRepository.delete(reservation);
		responseDTO.setSuccess(true);
		
		return responseDTO; 
	}

	public ReservationDetailsDTO updateReservationOrders(Long guestId, Long reservationId, List<GuestOrderDTO> dto) {
		ReservationDetailsDTO responseDTO = new ReservationDetailsDTO();
		Reservation reservation = reservationRepository.findOne(reservationId);
		// DELETE OLD ORDERS AND SET NEW
		List<GuestReservationOrder> orders = guestOrderRepository.findByReservationAndGuest(reservation, guestRepository.findOne(guestId));
		for (GuestReservationOrder order : orders) {
			guestOrderRepository.delete(order);
		}
		for(GuestOrderDTO orderDTO : dto) {
			MenuItem item = menuItemRepository.findById(orderDTO.getMenuItemId());
			GuestReservationOrder order = new GuestReservationOrder();
			order.setReservation(reservation);
			order.setGuest(guestRepository.findOne(guestId));
			order.setItem(item);
			order.setBePrepared(orderDTO.isBePrepared());
			guestOrderRepository.save(order);
		}
		
		responseDTO.setSuccess(true);
		return responseDTO;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	
		public List<AddPeopleDTO> addPeople(Long id) {
		Guest guest = guestRepository.findOne(id); 
		List<Guest> people = guestRepository.findAll(); 
		List<AddPeopleDTO> addPeopleDto = new ArrayList<>(); 
		
		for(Guest p : people) {	
				if(guest.getId() != p.getId() && !isFriend(guest.getFriends(), p)) {
					AddPeopleDTO dto = new AddPeopleDTO(); 
					dto.setId(p.getId());
					dto.setName(p.getName());
					dto.setSurname(p.getSurname());
					dto.setFriend(false);
					addPeopleDto.add(dto); 
				}
			}	
		return addPeopleDto ;
	}
	
	private boolean isFriend(List<Guest> friends, Guest p) {
		for(Guest friend : friends) {
			if(friend.getId() == p.getId()) {
				return true;
			}
		}
		return false;
	}

	public void sendFriendshipRequest(Long friendId, Long guestId) {
		FriendshipRequest request = new FriendshipRequest();
		Guest guest = guestRepository.findOne(guestId); 
		Guest friend = guestRepository.findOne(friendId); 
		request.setFrom(guest);
		request.setTo(friend);
		friendshipRequestRepository.save(request); 
		
		// get-all-friends metoda za drugu  tableu u kojoj se ispisuju svi friendship requests koji su za 
		// datog korisnika, ALI NISU CONFIRMED!!!! !isConfirned()
	}

	public List<AcceptPeopleDTO> acceptPeople(Long id) {
		Guest guest = guestRepository.findOne(id); 
		List<FriendshipRequest> requests = friendshipRequestRepository.findByToGuest(guest); 
		List<AcceptPeopleDTO> acceptPeopleDTO = new ArrayList<>(); 
		for(FriendshipRequest request : requests) {
			if(!request.isConfirmed()) {
				AcceptPeopleDTO dto = new AcceptPeopleDTO();
				dto.setId(request.getFrom().getId());
				dto.setConfirmed(false);
				dto.setName(request.getFrom().getName());
				dto.setSurname(request.getFrom().getSurname());
				if(!contains(acceptPeopleDTO, dto)) {
					acceptPeopleDTO.add(dto);
				}
				 
			}
		}
		return acceptPeopleDTO;
	}

	private boolean contains(List<AcceptPeopleDTO> acceptPeopleDTO, AcceptPeopleDTO dto) {
		for(AcceptPeopleDTO accept : acceptPeopleDTO) {
			if(accept.getId() == dto.getId()) {
				return true; 
			}
		}
		return false;
	}

	public void acceptFriendshipRequest(Long friendId, Long guestId) {
		Guest guest = guestRepository.findOne(guestId); 
		Guest friend = guestRepository.findOne(friendId); 
		List<FriendshipRequest> invited = friendshipRequestRepository.findByFromGuest(friend); 
		
		for(FriendshipRequest i : invited) {
			if(i.getTo().getId() == guest.getId()) {
				i.setConfirmed(true);
				friendshipRequestRepository.save(i); 
				guest.getFriends().add(friend); 
				guestRepository.save(friend); 
				friend.getFriends().add(guest); 
				guestRepository.save(guest); 
			}
		}		
	}

	public void rejectFriendshipRequest(Long friendId, Long guestId) {
		Guest guest = guestRepository.findOne(guestId); 
		Guest friend = guestRepository.findOne(friendId); 
		List<FriendshipRequest> invited = friendshipRequestRepository.findByFromGuest(friend); 
		
		for(FriendshipRequest i : invited) {
			if(i.getTo().getId() == guest.getId()) {
				i.setConfirmed(false);
				friendshipRequestRepository.delete(i); 
			}
		}		
	}

	public void deleteFriend(Long friendId, Long guestId) {
		Guest guest = guestRepository.findOne(guestId); 
		List<Guest> friends = guest.getFriends(); 
		for(int i = 0; i < friends.size(); i++) {
			if(friends.get(i).getId() == friendId) {
				
				for(int j = 0; j < friends.get(i).getFriends().size(); j++) {
					if(friends.get(i).getFriends().get(j).getId() == guest.getId()) {
						friends.get(i).getFriends().remove(j);
						guestRepository.save(friends.get(i)); 
					}
				}
				friends.remove(i); 
			}
		}
		guest.setFriends(friends);
		guestRepository.save(guest); 
	}
}
