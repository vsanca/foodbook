package e2.isa.grupa5.service.guest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import e2.isa.grupa5.model.reservation.InvitedToReservation;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.User;
import e2.isa.grupa5.model.users.UserRoles;
import e2.isa.grupa5.repository.guest.GuestRepository;
import e2.isa.grupa5.repository.guest.InvitedToReservationRepository;
import e2.isa.grupa5.repository.guest.ReservationRepository;
import e2.isa.grupa5.rest.dto.guest.FriendsPageDTO;
import e2.isa.grupa5.rest.dto.guest.HomePageDTO;
import e2.isa.grupa5.rest.dto.guest.ProfilePageDTO;

import e2.isa.grupa5.service.MailService;

@Service
@Transactional
public class GuestService {

	@Autowired
	private GuestRepository guestRepository;

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private InvitedToReservationRepository invitedToReservationRepository;

	/**
	 * Request BCrypt2 encoder
	 * 
	 * @return
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MailService mailManager;

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
		for(Reservation reservation : reservations) {
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
			
			for(InvitedToReservation friend : friends) {
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
		for(Guest friend : friends) {
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
}