package e2.isa.grupa5.rest.guest;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.grade.Grade;
import e2.isa.grupa5.model.grade.GradeDTO;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.User;
import e2.isa.grupa5.repository.grade.GradeRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.rest.dto.guest.CreateNewReservationDTO;
import e2.isa.grupa5.rest.dto.guest.FriendsPageDTO;
import e2.isa.grupa5.rest.dto.guest.HomePageDTO;
import e2.isa.grupa5.rest.dto.guest.ProfilePageDTO;
import e2.isa.grupa5.rest.dto.guest.ReservationDTO;
import e2.isa.grupa5.rest.dto.guest.ReservationDetailsDTO;
import e2.isa.grupa5.rest.dto.guest.Reserve1PageDTO;
import e2.isa.grupa5.rest.dto.guest.RestaurantsPageDTO;
import e2.isa.grupa5.rest.dto.guest.UpdateProfileDTO;
import e2.isa.grupa5.service.UserService;
import e2.isa.grupa5.service.grade.GradeService;
import e2.isa.grupa5.service.guest.GuestService;
import e2.isa.grupa5.service.restaurant.RestaurantTableService;

/**
 * Funkcionalnost 2.8 - ocenjivanje restorana
 * 
 * @author Jelena Jankovic RA139-2013
 * @author Boris
 */
@RequestMapping("/rest/guest")
@RestController
public class GuestController {

	@Autowired
	private GuestService guestService;

	@Autowired
	private UserService userService;

	@Autowired
	private GradeService gradeService;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	RestaurantTableService restaurantTableService;

	@RequestMapping(value = "/register-guest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		Guest guest = guestService.registerGuest(user);
		if (guest != null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/confirm-registration/{user-id}", method = RequestMethod.GET)
	public ResponseEntity<?> confirmGuestRegistration(@PathVariable(value = "user-id") long id) {

		boolean isValid = guestService.verifyGuest(id);

		if (isValid) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * Any authenticated user can access profile-page-info
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/get-profile-page-info/{guest-id}")
	@PreAuthorize("isAuthenticated()")
	public ProfilePageDTO getProfilePageInfo(@PathVariable(value = "guest-id") Long id) {
		return guestService.getProfilePageInfo(id);
	}

	/**
	 * Any authenticated user can access profile-page-info
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/get-home-page-info/{guest-id}")
	@PreAuthorize("isAuthenticated()")
	public List<HomePageDTO> getHomePageInfo(@PathVariable(value = "guest-id") Long id) {
		return guestService.getHomePageInfo(id);
	}

	/**
	 * Any authenticated user can access profile-page-info
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/get-friends-page-info/{guest-id}")
	@PreAuthorize("isAuthenticated()")
	public List<FriendsPageDTO> getFriendsPageInfo(@PathVariable(value = "guest-id") Long id) {
		return guestService.getFriendsPageInfo(id);
	}

	/**
	 * Any authenticated user can access profile-page-info
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/update-profile-info/{guest-id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> updateProfileInfo(@PathVariable(value = "guest-id") Long id,
			@RequestBody UpdateProfileDTO dto) {
		boolean isValid = guestService.updateProfileInfo(id, dto.getName(), dto.getSurname(), dto.getAddress());

		if (isValid) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * Any authenticated user can access profile-page-info
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/get-restaurants-page-info/{guest-id}")
	@PreAuthorize("isAuthenticated()")
	public List<RestaurantsPageDTO> getRestaurantsPageInfo(@PathVariable(value = "guest-id") Long id) {
		return guestService.getRestaurantsPageInfo(id);
	}

	/**
	 * Any authenticated user can access profile-page-info
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/get-reserve1-page-info/{restaurant-id}")
	@PreAuthorize("isAuthenticated()")
	public Reserve1PageDTO getReserve1PageInfo(@PathVariable(value = "restaurant-id") Long id) {
		return guestService.getReserve1PageInfo(id);
	}

	@RequestMapping(value = "/get-reserve2-page-info/{restaurant-id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getForRestaurant(@PathVariable(value = "restaurant-id") Long id) {

		return new ResponseEntity<>(restaurantTableService.findAllByRestaurantId(id), HttpStatus.OK);
	}

	/**
	 * Any authenticated user can access profile-page-info
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/get-guest-friends-info/{guest-id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getGuestFriendsInfo(@PathVariable(value = "guest-id") Long id) {
		List<FriendsPageDTO> friends = guestService.getGuestFriendsInfo(id);

		return new ResponseEntity<>(friends, HttpStatus.OK);

	}
	
	/**
	 * Any authenticated user can access profile-page-info
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/get-guest-reservations/{guest-id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getGuestReservations(@PathVariable(value = "guest-id") Long id) {
		List<ReservationDTO> reservations = guestService.getGuestReservations(id);

		return new ResponseEntity<>(reservations, HttpStatus.OK);

	}
	
	
	
	/**
	 * Any authenticated user can access profile-page-info
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/get-reservation-details/{guest-id}/{reservation-id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getGuestReservations(@PathVariable(value = "guest-id") Long guestId, @PathVariable(value = "reservation-id") Long reservationId) {
		ReservationDetailsDTO reservation = guestService.getReservationDetails(guestId, reservationId);

		return new ResponseEntity<>(reservation, HttpStatus.OK);

	}
	/**
	 * Rating reservation
	 * 
	 * @param reservation-id
	 * @return
	 * 
	 * @author Boris
	 */
	@RequestMapping(value = "/rate/{reservation-id}/{guest-id}", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity create(@PathVariable(value = "reservation-id") long rId,
			@PathVariable(value = "reservation-id") long gId, @RequestBody GradeDTO gDTO) {

		Grade g = gradeRepository.findByReservation_idAndGuest_id(rId, gId);
		gradeService.setVariableAttributes(g, gDTO);

		if (g != null) {

			g.setRated(true);
			gradeRepository.save(g);

			return new ResponseEntity<>(g, HttpStatus.OK);

		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/create-new-reservation", method = RequestMethod.POST)
	@Transactional
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity createNewReservation(@RequestBody CreateNewReservationDTO dto) {
		CreateNewReservationDTO responseDto = guestService.createNewReservation(dto);
		
		return new ResponseEntity<>(responseDto, HttpStatus.OK);


	}

}
